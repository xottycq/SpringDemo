package com.example.demospringmvc.dao;

import java.util.List;

import com.example.demospringmvc.pojo.Account;

public interface IAccountDao {
	// 添加
	public int addAccount(Account account);
	// 更新
	public int updateAccount(Account account);
	// 删除
	public int deleteAccount(int id);

	// 更新余额
	public int updateBalance(int id,float money,boolean add);

	// 查询所有账户
	public List<Account> findAllAccount();

	public List<Account> findAccountByName(String name);

}
