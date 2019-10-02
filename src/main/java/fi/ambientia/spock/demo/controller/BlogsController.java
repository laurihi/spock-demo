package fi.ambientia.spock.demo.controller;

import fi.ambientia.spock.demo.model.PostList;
import fi.ambientia.spock.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
