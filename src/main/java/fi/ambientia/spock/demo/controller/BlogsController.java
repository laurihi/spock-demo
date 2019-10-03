package fi.ambientia.spock.demo.controller;

import fi.ambientia.spock.demo.model.PostList;
import fi.ambientia.spock.demo.service.BlogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class BlogsController {

    private BlogService blogService;

    @Autowired
    public BlogsController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/posts")
    public PostList getPosts(){
        return blogService.getPosts();
    }

    @GetMapping("/posts/user/{id}")
    public PostList getPostsByUser(@PathVariable("id") Long userId){
        return blogService.getPostsByUser(userId);
    }
}
