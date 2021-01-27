package com.example.demospringmvc.dao;

import com.example.demospringmvc.pojo.User;

import java.util.*;
public interface IUserDao {
    public List<User> queryAllUser();
    public int addUser(User user);
    public int updateUser(User user);
    public int deleteUser(String name);
    public User  queryUser(String name);
}
