package fi.ambientia.spock.demo.controller.springboot


import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import spock.mock.DetachedMockFactory

class IntegrationTestConfiguration {

    private final detachedMockFactory = new DetachedMockFactory()

    @Bean
    RestTemplate restTemplate() {
        detachedMockFactory.Mock(RestTemplate)
    }
}