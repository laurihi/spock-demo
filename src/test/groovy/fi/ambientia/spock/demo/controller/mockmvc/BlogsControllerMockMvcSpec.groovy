package fi.ambientia.spock.demo.controller.mockmvc

import fi.ambientia.spock.demo.controller.BlogsController
import fi.ambientia.spock.demo.external.BlogApiClient
import fi.ambientia.spock.demo.model.Post
import fi.ambientia.spock.demo.service.BlogService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest(controllers = [BlogsController])
class BlogsControllerMockMvcSpec extends Specification{

    def postApiBase = "http://url.to.service"
    def restTemplate = Mock(RestTemplate)

    def setup(){
    }

    @SpringBean
    BlogApiClient blogApiClient = new BlogApiClient(postApiBase, restTemplate)

    // Needs to be defined for autowire to work
    @SpringBean
    BlogService blogService = new BlogService(blogApiClient)


    @Autowired
    MockMvc mvc

    def "Request made to posts calls post api'"() {

        given: "api response from blog api contains one blog post"
            List<Post> resultPosts = [new Post()]
            ResponseEntity<List< Post>> apiResponse = new ResponseEntity<>(resultPosts, HttpStatus.OK)

        when: "get all posts endpoint on controller called"
            mvc.perform( get("/posts") )

        then: "blog post api endpoint /posts is called'"
            1 * restTemplate.exchange(postApiBase+"/posts",
                HttpMethod.GET, null, _) >> apiResponse

    }

    def "Response from controller contains posts'"() {
        given: "api response from blog api contains one blog post"

            List<Post> resultPosts = [new Post()]
            ResponseEntity<List< Post>> apiResponse = new ResponseEntity<>(resultPosts, HttpStatus.OK)
            restTemplate.exchange(postApiBase+"/posts",
                HttpMethod.GET, null, _) >> apiResponse

        when: "get all posts endpoint on controller called"
            def result = mvc.perform(get("/posts"))

        then: "blog post api endpoint /posts is called'"
            result.andExpect(status().isOk())
                .andReturn()
                .response.getContentAsString().contains("posts")
    }
}
