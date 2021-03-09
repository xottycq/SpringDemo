package com.example.demospringboot.dao;

import com.example.demospringboot.domain.User;

import java.util.List;

public interface IUserDao {
    public List<User> queryAllUser();
    public int addUser(User user);
    public int updateUser(User user);
    public int deleteUser(String name);
    public User  queryUser(String name);
}
