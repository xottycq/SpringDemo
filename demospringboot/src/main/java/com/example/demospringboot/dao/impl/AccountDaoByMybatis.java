/**SpringBoot与mybatis整合方法1：接口+实现类+XML方式映射SQL语句
 * 1.定义接口的实现类
 * 1)在application.propertiesl中配置mapper(xx.xml)位置和typeAlias(xx.xml中类的包名，以便简写类名即可)
 *	 mybatis.mapper-locations=classpath*:/mapper/*.xml
 * 	 mybatis.type-aliases-package=com.example.demospringboot.domain,com.example.demospringboot.dto
 * 2）注入SqlSessionTemplate
 * 3）实现接口IAccountDao，调用mapper中的方法来完成dao的相关功能，使用sqlsession的一系列SQL方法来调用mapper中的方法，在id冲突时可外加namespace名称
 *        Object selectOne(String statement, Object parameter)
 *        List selectList(String statement, Object parameter)
 *        int insert(String statement, Object parameter)
 *        int update(String statement, Object parameter)
 *        int delete(String statement, Object parameter)
 */
package com.example.demospringboot.dao.impl;

import com.example.demospringboot.dao.IAccountDao;
import com.example.demospringboot.dao.IUserDao;
import com.example.demospringboot.dto.AccountDto;
import com.example.demospringboot.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="accountdao_mybatis")
public class AccountDaoByMybatis  implements IAccountDao {

	@Resource(name="userdao_mybatis")
	private IUserDao userDao;
	/*等效替换
	@Resource
	private UserDaoMapper mapper;*/

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	// 添加账户
	public int insertAccount(AccountDto accountDto){
		System.out.println("AccountDao Mybatis-----insertAccount");
		return sqlSessionTemplate.update("AccountMapper.insertAccount",accountDto);
	}

	// 更新账户
	public int updateAccount(Account account) {
		System.out.println("AccountDao Mybatis-----updateAccount");
		return sqlSessionTemplate.update("AccountMapper.updateAccount",account);
	}

	// 删除账户
	public int deleteAccount(int id) {
		System.out.println("AccountDao Mybatis-----deleteAccount");
		return sqlSessionTemplate.update("AccountMapper.deleteAccount",id);

	}

	// 查询所有账户信息
	public List<Account> findAllAccount() {
		System.out.println("AccountDao Mybatis-----findAllAccount");
		return sqlSessionTemplate.selectList("findAllAccount");
	}

	// 查询所有账户信息
	public List<Account> findAccountByName(String name) {
		System.out.println("AccountDao Mybatis-----findAccountByName----"+name);
		return sqlSessionTemplate.selectList("AccountMapper.findAccountByName",name);
	}

	// 更新余额
	public int updateBalance(@Param("id")int id, @Param("money")float money){
		System.out.println("AccountDao Mybatis-----updateBalance");
		Map<String, Object> param=new HashMap<>();
		param.put("balance", money);
		param.put("id", id);
		param.put("balance", money);
		return this.sqlSessionTemplate.update("updateBalance",param);
	}

}
