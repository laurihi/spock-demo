package fi.ambientia.spock.demo.service;

import fi.ambientia.spock.demo.external.BlogApiClient;
import fi.ambientia.spock.demo.model.posts.Post;
import fi.ambientia.spock.demo.model.posts.PostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    private BlogApiClient blogApiClient;

    @Autowired
    public BlogService(BlogApiClient blogApiClient){
        this.blogApiClient = blogApiClient;
    }

    public PostList getPostsByUser(long userId){
        List<Post> usersPosts = blogApiClient.getPostsByUser(userId);

        usersPosts = usersPosts.stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());

        return new PostList(usersPosts);
    }
    public PostList getPosts() {
        List<Post> posts = blogApiClient.getPosts();
        PostList result = new PostList(posts);
        return result;
    }


}
