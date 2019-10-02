package fi.ambientia.spock.demo

import fi.ambientia.spock.demo.controller.DemoController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired
    DemoController demoController

    def "context loads without exceptions"() {
        expect: "democontroller is added to spring context"
        demoController
    }
}