/**Spring与mybatis整合方法2：接口+XML方式映射SQL语句句
 *  1.定义一个接口，其名称与xxx.xml(SQL映射文件)的namespace名称（全限定名）一致，xxx.xml中id名称须与接口中的方法名称完全一致，二者返回类型也须一致。
 *  2.该接口用@mapper注解,这样可根据xxx.xml，自动生成接口的实现类。
 *  3.在需要的地方先注入接口，然后即可用该接口名称直接调用其中的方法即可完成相应SQL功能。示例详见AccountServieImpl
 */
package com.example.demospringboot.dao;

import com.example.demospringboot.domain.Account;
import com.example.demospringboot.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public  interface AccountMapper {

	public int insertAccount(AccountDto accountDto);
	// 更新账户
	public int updateAccount(Account account);

	// 删除账户
	public int deleteAccount(int id) ;

	// 查询所有账户信息
	public List<Account> findAllAccount() ;

	// 查询所有账户信息
	public List<Account> findAccountByName(String name) ;

	// 更新余额
	public int updateBalance(@Param("id")int id, @Param("balance")float money);

}
