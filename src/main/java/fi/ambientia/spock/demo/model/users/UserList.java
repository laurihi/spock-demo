package fi.ambientia.spock.demo.model.users;

import fi.ambientia.spock.demo.model.posts.Post;

import java.io.Serializable;
import java.util.List;

public class UserList implements Serializable {

    private List<User> users;


    public UserList(List<User> users) {
        if(users==null)
            throw new IllegalArgumentException("Supply a list of users");
        this.users = users;
    }

    // created for tests, this should never be used in real development
    private UserList(){
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
