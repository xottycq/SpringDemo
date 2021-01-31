/**Spring与mybatis整合方法一：采用XML方式映射SQL语句
 * 1）在Spring xml配置文件（applicationContext.xml）中配置：
 *    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
 *         <property name="dataSource" ref="dataSource" />
 *         <property name="configLocation" value="classpath:mybatis-config.xml" />
 *    </bean>
 *    <bean id="sqlsessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
 *        <constructor-arg index="0" ref="sqlSessionFactory" />
 *    </bean>
 *    <bean id="accountDao_mybatis" class="com.example.demospringmvc.dao.AccountDaoByMybatis">
 *        <property name="sqlSession" ref="sqlsessionTemplate"/>
 *   </bean>
 * 2)在mybatis-config.xml中配置mapper和typeAlias
 *  <typeAliases>
 *      <package name="com.example.demospringmvc.pojo" />
 *      <package name="com.example.demospringmvc.dto" />
 *  </typeAliases>
 *  <mappers>
 *      <mapper resource="mapper/AccountMapper.xml"/>
 *  </mappers>
 *  3）AccountDaoByMybatis实现接口IAccountDao，注入SqlSessionTemplate
 *     调用mapper中的方法来完成dao的相关功能，调用时：使用sqlsession的一系列SQL方法来调用mappen中的方法，在id冲突时可外加namespace名称
 *        Object selectOne(String statement, Object parameter)
 *        List selectList(String statement, Object parameter)
 *        int insert(String statement, Object parameter)
 *        int update(String statement, Object parameter)
 *        int delete(String statement, Object parameter)
 *
 *   额外说明：在单独使用xml配置的mybatis mapper时，可以把mapper的namespace名称设为接口名称（必须是全限定名，如本例：com.example.demospringmvc.dao.IAccountDao），
 *   mapper中id名称须与接口中的方法名称完全一致，二者返回类型也须一致。这时即不再需要额外的接口实现类（即本类），而把mapper.xml当成接口的实现，
 *   从而在需要的地方直接接调用接口中的方法即可完成相应SQL功能，从而代替上述sqlsession的SQL方法调用.此时只需将applicationContext.xml中的
 *   accountDao_mybatis配置改为：
 *      <bean id="accountDao_mybatis" class="org.mybatis.spring.mapper.MapperFactoryBean">
 *         <property name="mapperInterface" value="com.example.demospringmvc.dao.IAccountDao"/>
 *         <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
 *     </bean>
 */
package com.example.demospringmvc.dao;

import com.example.demospringmvc.dto.AccountDto;
import com.example.demospringmvc.pojo.Account;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDaoByMybatis implements IAccountDao {

	private UserDaoMapper mapper;

	private SqlSessionTemplate sqlSessionTemplate;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		System.out.println("SqlSessionTemplate");
	}

	@PostConstruct
	public void testInit(){
		System.out.println("AccountDaoByMybatis init");
		this.sqlSessionTemplate.getConfiguration().addMapper(UserDaoMapper.class);
	}

	// 添加账户
	public int addAccount(Account account) {
		System.out.println("Mybatis-----addAccount");
		mapper =  sqlSessionTemplate.getMapper(UserDaoMapper.class);
		mapper.addUser(account.getUser());
		AccountDto accountDto=new AccountDto();
		accountDto.setUserid(account.getUser().getId());
		accountDto.setBalance(0);
		System.out.println("id-----"+account.getUser().getId());
		return sqlSessionTemplate.update("AccountMapper.insertAccount",accountDto);
	}

	// 更新账户
	public int updateAccount(Account account) {
		System.out.println("Mybatis-----updateAccount");
		return sqlSessionTemplate.update("AccountMapper.updateAccount",account);
	}

	// 删除账户
	public int deleteAccount(int id) {
		System.out.println("Mybatis-----deleteAccount");
		return sqlSessionTemplate.update("AccountMapper.deleteAccount",id);

	}

	// 查询所有账户信息
	public List<Account> findAllAccount() {
		System.out.println("Mybatis-----findAllAccount");
		return sqlSessionTemplate.selectList("findAllAccount");
	}

	// 查询所有账户信息
	public List<Account> findAccountByName(String name) {
		System.out.println("Mybatis-----findAccountByName----"+name);
		return sqlSessionTemplate.selectList("AccountMapper.findAccountByName",name);
	}

	// 更新余额
	public int updateBalance(@Param("id")int id, @Param("money")float money, boolean add){
		System.out.println("Mybatis-----updateBalance");
		Map<String, Object> param=new HashMap<>();
		param.put("balance", money);
		param.put("id", id);
		if (add)
			param.put("balance", money);
		else
	  		param.put("balance", money*(-1));

		return this.sqlSessionTemplate.update("updateBalance",param);
	}

}
