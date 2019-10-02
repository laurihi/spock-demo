package fi.ambientia.spock.demo

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    def "context loads without exceptions"() {
        expect: "the context is loaded"

    }
}