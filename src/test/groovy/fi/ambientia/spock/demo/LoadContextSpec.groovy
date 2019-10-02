package fi.ambientia.spock.demo

import fi.ambientia.spock.demo.controller.BlogsController
import fi.ambientia.spock.demo.service.BlogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextSpec extends Specification {

    @Autowired
    BlogsController demoController

    @Autowired
    BlogService blogService

    def "context loads without exceptions"() {
        expect: "beans are correctly autowired"
        demoController
        blogService
        demoController.blogService == blogService   // the same instance
    }
}