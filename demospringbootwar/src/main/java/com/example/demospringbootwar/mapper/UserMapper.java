/**mybatis注解形式：将SQL注解绑定在方法上形成一个mapper接口，代替了传统的"mapper接口+mapper.xml（其中定义SQL）",用法有二：
 *1）单独使用：具体方法见DaoControoler相关注释部分，核心是使其成为bean，然后注入到其被调用的地方（如DaoControoler）
 *2）引入接口实现类后调用：在接口实现类中通过sqlSessionTemplate().getMapper()引入，使用Dao时不再调用这个mapper，而是使用接口实现类
 */
package com.example.demospringbootwar.mapper;

import com.example.demospringbootwar.domain.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

//@Mapper
public interface UserMapper {
    @Select("select * from tb_user")
    public List<User> queryAllUser();

    @Insert("insert into tb_user(name,age) values (#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")    //通过user.getId()获取自增主键id
    public int addUser(User user);

    @Update("update tb_user set age=#{age} where name=#{name}")
    public int updateUser(User user);

    @Delete("delete from tb_user  where name=#{name}")
    public int deleteUser(String name);

    @Select("select * from tb_user where name=#{name} limit 1")
    public User queryUser(String name);
}
