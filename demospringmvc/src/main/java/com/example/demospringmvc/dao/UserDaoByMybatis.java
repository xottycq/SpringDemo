package com.example.demospringmvc.dao;

import com.example.demospringmvc.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value="UserDaoByMybatis")
public class UserDaoByMybatis extends SqlSessionDaoSupport implements IUserDao {

    @Resource
    public  void setSqlSessionTemplate(SqlSessionTemplate jb) {
        super.setSqlSessionTemplate(jb);
    }

    public List<User> queryAllUser() {
        System.out.println("Mybatis------");
        SqlSession sqlSession = getSqlSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        return mapper.queryAllUser();
    }

    public int addUser(User user) {
//        KeyHolder keyHolder1 = new GeneratedKeyHolder();
//        // 获取到插入数据生成的ID
//        String n=user.getName();
//        int a=user.getAge();
//        String sql="insert into tb_user(name,age) values (?, ?)";
//        getJdbcTemplate().update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                // 设置返回的主键字段名
//                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, n);
//                ps.setInt(2, a);
//                return ps;
//            }
//        }, keyHolder1);
        return 0;
    }

    public List<Integer> queryUserIDByName(String name){

        return new ArrayList<>();
    }
}
