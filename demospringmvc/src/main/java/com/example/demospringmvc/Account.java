package com.example.demospringmvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller("account")
public class Account {

    private User user;
    private String password;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(){
        super();
     }

    @Override
    public String toString(){
        return "Account(name="+user.getName()+",age="+user.getAge()+",password="+password+")";
    }
}
