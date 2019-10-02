package fi.ambientia.spock.demo.service

import fi.ambientia.spock.demo.external.BlogApiClient
import fi.ambientia.spock.demo.model.Post
import fi.ambientia.spock.demo.model.PostList
import spock.lang.Specification
import spock.lang.Subject

class BlogServiceSpec extends Specification {

    def blogApiClient = Mock(BlogApiClient)

    @Subject
    def blogService = new BlogService(blogApiClient)

    def "get posts delegates to blogApiClient"() {
        when: "blogService is queried for posts"
            blogService.getPosts()
        then: "action is delegated to blogApiClient"
            1 * blogApiClient.getPosts()
    }

    def "when getting all posts, amount of posts is not filtered"(){
        given: "blogApiClient returns 2 posts"
            blogApiClient.getPosts() >> [new Post(), new Post()]
        when: "blog service is called for post listing"
            PostList postList = blogService.getPosts();
        then: "post list content length equals to two"
            postList.posts.size() == 2
    }

    def "when getting all posts, blogApiClient returns null"(){
        given: "blogApiClient returns null"
           blogApiClient.getPosts() >> null
        when: "blog service is called for post listing"
            blogService.getPosts();
        then: "Illegal argument exception is thrown"
            thrown(IllegalArgumentException)
    }

    def "when getting all posts, zero posts does not cause a problem"(){
        given: "blogApiClient returns 0 posts"
            blogApiClient.getPosts() >> []
        when: "blog service is called for post listing"
            PostList postList = blogService.getPosts();
        then: "post list content length equals to zero"
            postList.posts.size() == 0
    }

    def "when getting all posts for a user, user id is given to blog api client"(){
        given: "posts by user id wanted"
            def userId = 10l
        when: "blog service is called for post listing with the user id"
            blogService.getPostsByUser(userId);
        then: "blog api client is called with the same user id"
            1 * blogApiClient.getPostsByUser(10l) >> []
    }

    def "blog service filters out faulty results from blog api client"(){
        given: "blogApiClient returns one post which is not by user id"
            def userId = 10l
            def correctPost = new Post()
            def incorrectPost = new Post()
            correctPost.userId = userId
            incorrectPost.userId = userId+1
            blogApiClient.getPostsByUser(userId) >> [incorrectPost, correctPost]
        when: "blog service is called for post listing with the user id"
            def result = blogService.getPostsByUser(userId);
        then: "blog api client is called with the same user id"
            result.posts.size() == 1
    }

}
