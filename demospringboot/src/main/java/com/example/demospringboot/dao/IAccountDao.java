package com.example.demospringboot.dao;

import com.example.demospringboot.domain.Account;
import com.example.demospringboot.dto.AccountDto;

import java.util.List;

public interface IAccountDao {
	// 添加
	public int insertAccount(AccountDto accountDto);
	// 更新
	public int updateAccount(Account account);
	// 删除
	public int deleteAccount(int id);

	// 更新余额
	public int updateBalance(int id,float money);

	// 查询所有账户
	public List<Account> findAllAccount();

	public List<Account> findAccountByName(String name);

}
