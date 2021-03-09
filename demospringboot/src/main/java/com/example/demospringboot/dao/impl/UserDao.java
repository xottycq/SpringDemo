/**使用Helper类JdbcDaoSupport引入JdbcTemplate
 * 1）extends JdbcDaoSupport
 * 2）@Repository注解，配合xml中注解扫描配置
 * 3）@Resource注解dataSource或jdbcTemplate的setter方法
 * 4）getJdbcTemplate()
 */
package com.example.demospringboot.dao.impl;

import com.example.demospringboot.dao.IUserDao;
import com.example.demospringboot.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

@Repository(value="userdao_jdbctemplate")
public class UserDao extends JdbcDaoSupport implements IUserDao {
    @Resource
    public  void setJT(JdbcTemplate jt) {
        super.setJdbcTemplate(jt);
    }

/*等效
    @Resource
    public void  setDS(DataSource dataSource) {
    super.setDataSource(dataSource);
}*/
    public int addUser(User user) {
        System.out.println("JDBC Template-----addUser");
        KeyHolder keyHolder1 = new GeneratedKeyHolder();
        // 获取到插入数据生成的ID
        String n=user.getName();
        int a=user.getAge();
        String sql="insert into tb_user(name,age) values (?, ?)";
       int num=0;
        try{
        num=getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // 设置返回的主键字段名
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, n);
                ps.setInt(2, a);
                return ps;
            }
        }, keyHolder1);}
        catch(Exception e){
                return -1;
        }
        user.setId(keyHolder1.getKey().intValue());    //将自增id放入user，保持与mybatis同样效果
        return num;
    }

    public int updateUser(User user) {
        System.out.println("JDBC Template-----updateUser");
        // 获取到插入数据生成的ID
        String n=user.getName();
        int a=user.getAge();
        String sql="update tb_user set age=? where name=?";
        int num=getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                // 设置返回的主键字段名
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, a);
                ps.setString(2, n);
                return ps;
            }
        });
        return num;
    }

    public int deleteUser(String name) {
        System.out.println("JDBC Template-----deleteUser");
        return getJdbcTemplate().update("delete from tb_user  where name=?",name);
    }

    public User  queryUser(String name){
        System.out.println("JDBC Template-----queryUser");
        return getJdbcTemplate().queryForObject("select * from tb_user where name=? limit 1",
                new RowMapper<User>(){
                    @Override
                    public User mapRow(ResultSet rs, int index) throws SQLException {
                        User user=new User();
                        user.setName(rs.getString("name"));
                        user.setAge(rs.getInt("age"));
                        return user;
                       }
                    },
                 name);
    }

    public List<User> queryAllUser(){
        System.out.println("JDBC Template-----queryAllUser");
        String sql="select * from tb_user";
        List<User> list=getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(User.class));
        return list;
    }
/*等效替代
        public List<User> queryAllUser() {
        System.out.println("JDBC Template-----queryAllUser1");
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
    }*/
}
