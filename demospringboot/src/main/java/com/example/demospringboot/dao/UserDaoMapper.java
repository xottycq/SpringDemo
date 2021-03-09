/**Spring与mybatis整合方法3：注解方式映射SQL语句
 *  1.定义一个接口，对其中的方法用mybatis注解书写相应的SQL语句
 *  2.该接口用@mapper注解,这样就会自动生成接口的实现类。
 *  3.在需要的地方先注入接口实例，然后即可用该接口名称直接调用其中的方法即可完成相应SQL功能。示例详见AccountServieImpl
 *  4.在需要的地方也可以先注入SqlSessionTemplate，然后用其方法sqlSessionTemplate().getMapper()引入该接口的实例
 */
package com.example.demospringboot.dao;

import com.example.demospringboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDaoMapper {

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
