package fi.ambientia.spock.demo.controller;

import fi.ambientia.spock.demo.model.posts.PostList;
import fi.ambientia.spock.demo.model.users.UserList;
import fi.ambientia.spock.demo.service.BlogService;
import fi.ambientia.spock.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public UserList getUsers(){
        return userService.getUsers();
    }
}
