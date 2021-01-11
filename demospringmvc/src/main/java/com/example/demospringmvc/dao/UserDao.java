package com.example.demospringmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.example.demospringmvc.pojo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao extends JdbcDaoSupport implements IUserDao {

    @Resource
    public  void setJb(JdbcTemplate jb) {
        super.setJdbcTemplate(jb);
    }
    public List<User> queryAllUser() {
        System.out.println("JDBC Template------");
        String sql="select * from tb_user";
        List<Map<String,Object>> list=getJdbcTemplate().queryForList(sql);
        List<User> userList=new ArrayList<>();
        for(Map<String,Object> row:list)
        {
            User user=new User();
            user.setName((String)row.get("name"));
            user.setAge((Integer)row.get("age"));
            userList.add(user);
        }
        return userList;

    }

    public int addUser(User user) {
        KeyHolder keyHolder1 = new GeneratedKeyHolder();
        // 获取到插入数据生成的ID
        String n=user.getName();
        int a=user.getAge();
        String sql="insert into tb_user(name,age) values (?, ?)";
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // 设置返回的主键字段名
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, n);
                ps.setInt(2, a);
                return ps;
            }
        }, keyHolder1);
        return keyHolder1.getKey().intValue();
    }

    public List<Integer> queryUserIDByName(String name){
        String sql="select * from tb_user where name=?";
        List<Integer> list=getJdbcTemplate().queryForList(sql,Integer.class,name);
        return list;
    }
}
