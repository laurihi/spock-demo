package fi.ambientia.spock.demo

import fi.ambientia.spock.demo.controller.DemoController
import fi.ambientia.spock.demo.service.DemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextSpec extends Specification {

    @Autowired
    DemoController demoController

    @Autowired
    DemoService demoService

    def "context loads without exceptions"() {
        expect: "beans are correctly autowired"
        demoController
        demoService
        demoController.demoService == demoService   // the same instance
    }
}