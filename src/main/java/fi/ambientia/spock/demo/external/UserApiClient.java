package fi.ambientia.spock.demo.external;

import fi.ambientia.spock.demo.model.posts.Post;
import fi.ambientia.spock.demo.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserApiClient {

    private final RestTemplate restTemplate;

    private String postApiBase;

    //https://jsonplaceholder.typicode.com/users
    private final String API_ENDPOINT_POSTS = "/users";

    public UserApiClient(@Value("${post.api.base}") String postApiBase,
                         @Autowired RestTemplate restTemplate){

        this.postApiBase = postApiBase.replaceAll("/$", "");
        this.restTemplate = restTemplate;
    }

    public List<User> getUsers(){

        ResponseEntity<List<User>> response = restTemplate.exchange(
                postApiBase + API_ENDPOINT_POSTS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });

        return response.getBody();
    }


}
