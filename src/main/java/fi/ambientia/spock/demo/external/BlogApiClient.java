package fi.ambientia.spock.demo.external;

import fi.ambientia.spock.demo.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BlogApiClient {

    private final RestTemplate restTemplate;
    private String postApiBase;

    private final String API_ENDPOINT_POSTS = "/posts";

    public BlogApiClient(@Value("${post.api.base}") String postApiBase){

        this.postApiBase = postApiBase.replaceAll("/$", "");
        this.restTemplate = new RestTemplate();
    }

    public List<Post> getPosts(){

        ResponseEntity<List<Post>> response = restTemplate.exchange(
                postApiBase + API_ENDPOINT_POSTS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Post>>() {
                });


        return response.getBody();
    }
}
