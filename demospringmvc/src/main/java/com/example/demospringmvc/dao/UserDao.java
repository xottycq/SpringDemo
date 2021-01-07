//package com.example.demospringmvc.dao;
//
//import java.util.*;
//
//import com.example.demospringmvc.pojo.User;
//import org.springframework.jdbc.core.support.JdbcDaoSupport;
//
//public class UserDao extends JdbcDaoSupport implements IUserDao {
//
//    public List<User> QueryAllUser() {
//
//        String sql="select * from tb_user";
//        List<Map<String,Object>> list=getJdbcTemplate().queryForList(sql);
//        List<User> userList=new ArrayList<User>();
//        for(Map<String,Object> row:list)
//        {
//            User user=new User();
//            user.setName((String)row.get("name"));
//            user.setAge((Integer)row.get("age"));
//            userList.add(user);
//        }
//        return userList;
//
//    }
//
//    public Boolean AddUser(User user) {
//        String sql="insert into tb_user (user.name,user.age) values (?,?)";
//        int row=getJdbcTemplate().update(sql, new Object[]{name,age});
//        if(row>0)
//        {
//            return true;
//        }
//        return false;
//    }
//
//}
