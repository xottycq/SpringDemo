package com.example.demospringmvc.dao;

import com.example.demospringmvc.pojo.User;

import java.util.*;
public interface IUserDao {
    public List<User> queryAllUser();
    public int addUser(User user);
}
