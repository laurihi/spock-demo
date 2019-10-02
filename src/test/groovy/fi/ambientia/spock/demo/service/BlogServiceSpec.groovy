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

}