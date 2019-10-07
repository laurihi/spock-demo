package fi.ambientia.spock.demo.model.posts;

import java.io.Serializable;
import java.util.List;

public class PostList implements Serializable {

    private List<Post> posts;


    public PostList(List<Post> posts) {
        if(posts==null)
            throw new IllegalArgumentException("Supply a list of posts");
        this.posts = posts;
    }

    // created for tests, this should never be used in real development
    private PostList(){
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
