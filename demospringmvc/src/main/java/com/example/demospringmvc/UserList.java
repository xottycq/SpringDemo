package com.example.demospringmvc;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("userlist")
public class UserList {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
