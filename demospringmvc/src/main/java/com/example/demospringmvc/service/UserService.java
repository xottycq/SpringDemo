package com.example.demospringmvc.service;

import java.util.List;
import com.example.demospringmvc.pojo.User;

public interface UserService {
    public List<User> findUsersWithConditions(User user);
    
    public int add(User user);
}
