package fi.ambientia.spock.demo.model;

import java.util.ArrayList;
import java.util.List;

public class PostList {

    private List<Post> posts;

    public PostList(List<Post> posts) {
        if(posts==null)
            throw new IllegalArgumentException("Supply a list of posts");
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
