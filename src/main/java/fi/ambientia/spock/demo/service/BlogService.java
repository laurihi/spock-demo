package fi.ambientia.spock.demo.service;

import fi.ambientia.spock.demo.external.BlogApiClient;
import fi.ambientia.spock.demo.model.Post;
import fi.ambientia.spock.demo.model.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private BlogApiClient blogApiClient;

    @Autowired
    public BlogService(BlogApiClient blogApiClient){
        this.blogApiClient = blogApiClient;
    }

    public PostList getPosts() {
        List<Post> posts = blogApiClient.getPosts();
        PostList result = new PostList(posts);
        return result;
    }


}
