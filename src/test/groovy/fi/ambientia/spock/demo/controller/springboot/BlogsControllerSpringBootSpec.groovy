package fi.ambientia.spock.demo.controller.springboot


import fi.ambientia.spock.demo.model.Post
import fi.ambientia.spock.demo.model.PostList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
@ActiveProfiles("test")
class BlogsControllerSpringBootSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    RestTemplate mockRestTemplate

    @Value('${post.api.base}')
    String postApiBase

    def setup(){
        this.postApiBase = postApiBase.replaceAll('/$', '')
    }

    def "Request made to posts calls post api'"() {
        given: "api response from blog api contains one blog post"
            List<Post> resultPosts = [new Post()]
            ResponseEntity<List< Post>> apiResponse = new ResponseEntity<>(resultPosts, HttpStatus.OK)

        when: "get all posts endpoint on controller called"
            def postList = restTemplate.getForEntity('/posts', PostList)

        then: "blog post api endpoint /posts is called'"
            1* mockRestTemplate.exchange(postApiBase+"/posts", HttpMethod.GET, null, _) >> apiResponse

    }
}
