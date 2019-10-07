package fi.ambientia.spock.demo.service;

import fi.ambientia.spock.demo.external.UserApiClient;
import fi.ambientia.spock.demo.model.posts.Post;
import fi.ambientia.spock.demo.model.users.User;
import fi.ambientia.spock.demo.model.users.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserApiClient userApiClient;

    @Autowired
    public UserService(UserApiClient userApiClient){
        this.userApiClient = userApiClient;
    }

    public UserList getUsers() {
        List<User> users = userApiClient.getUsers();
        return new UserList(users);
    }
}

