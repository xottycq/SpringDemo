/**Spring与mybatis整合方法二：采用注解方式映射SQL语句
 * 1）在Spring xml配置文件（applicationContext.xml）中配置：
 *    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
 *         <property name="dataSource" ref="dataSource" />
 *         <property name="configLocation" value="classpath:mybatis-config.xml" />
 *    </bean>
 *    <bean id="sqlsessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
 *        <constructor-arg index="0" ref="sqlSessionFactory" />
 *    </bean>
 *        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
 *        <property name="basePackage" value="com.example.demospringmvc.dao"/>
 *     </bean>
 * 2)在mybatis-config.xml中配置mapper和typeAlias
 *  <typeAliases>
 *      <package name="com.example.demospringmvc.pojo" />
 *      <package name="com.example.demospringmvc.dto" />
 *  </typeAliases>

 *  3）UserDaoByMybatis实现接口IUserDao，继承SqlSessionDaoSupport
 *    a)@Repository注解类
 *    b)@Resourece注入SqlSessionTemplate
 *    c)mapper =  getSqlSessionTemplate().getMapper(UserDaoMapper.class);
 *    d)用mapper调用注解接口中的方法实现Dao的各项功能
 *
 *   额外说明：在单独使用注解配置的mybatis mapper时，此时mapper形式上是一个接口（其中含mybatis的SQL语句注解），实际上可以直接调用其中
 *   的方法来完成相应的SQL功能，无需像本例一样又提供一层接口和实现的封装，即可以省略IUserDao和UserDaoByMybatis。
 *
 */
package com.example.demospringboot.dao.impl;

import com.example.demospringboot.dao.IUserDao;
import com.example.demospringboot.dao.UserDaoMapper;
import com.example.demospringboot.domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository(value="userdao_mybatis")

 public class UserDaoByMybatis extends SqlSessionDaoSupport implements IUserDao,ApplicationListener<ContextRefreshedEvent> {
    /*等效替换
    public class UserDaoByMybatis  implements IUserDao {
    // @Resource*/

    private UserDaoMapper mapper;

    //Spring初始化
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //保证在web容器中只执行一次该事件
        if (event.getApplicationContext().getParent() == null) {
            System.out.println("UserDaoByMybatis ini");
            mapper =  getSqlSession().getMapper(UserDaoMapper.class);
        }
    }

    @Resource
    public  void setSqlSessionTemplate(SqlSessionTemplate sst) {
        super.setSqlSessionTemplate(sst);
    }


    public List<User> queryAllUser() {
        System.out.println("Mybatis------queryAllUser");
        return mapper.queryAllUser();
    }

    public int addUser(User user){
        System.out.println("Mybatis------addUser");
        return mapper.addUser(user);
    }
    public int updateUser(User user){
        System.out.println("Mybatis------udateUser");
        return mapper.updateUser(user);
    }
    public int deleteUser(String name){
        System.out.println("Mybatis------deleteUser");
        return mapper.deleteUser(name);
    }
    public User  queryUser(String name){
        System.out.println("Mybatis------queryUser");
        return mapper.queryUser(name);
    }
}
