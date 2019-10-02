package fi.ambientia.spock.demo.service

import fi.ambientia.spock.demo.external.BlogApiClient
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

}
