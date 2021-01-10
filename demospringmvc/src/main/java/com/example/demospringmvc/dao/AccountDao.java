package com.example.demospringmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.demospringmvc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demospringmvc.pojo.Account;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


public class AccountDao implements IAccountDao {
	// 声明JdbcTemplate属性及其setter方法
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Resource
	private UserDao userDao;

	// 添加账户
	public int addAccount(Account account) {
		int userid=userDao.addUser(account.getUser());
		account.getUser().setId(userid);
		// 定义SQL
		String sql = "insert into tb_account(userid,balance) values(?,?)";
		// 定义数组来存放SQL语句中的参数
		Object[] obj = new Object[] { 
                           account.getUser().getId(),
                           account.getBalance() 
         };
		// 执行添加操作，返回的是受SQL语句影响的记录条数
		int num = this.jdbcTemplate.update(sql, obj);
		return num;
	}
	// 更新账户
	public int updateAccount(Account account) {
		// 定义SQL
		String sql = "update tb_account set userid=?,balance=? where id = ?";
		// 定义数组来存放SQL语句中的参数
		Object[] params = new Object[] { 
                               account.getUser().getId(),
                               account.getBalance(), 
                               account.getId() 
          };
		// 执行添加操作，返回的是受SQL语句影响的记录条数
		int num = this.jdbcTemplate.update(sql, params);
		return num;
	}
	// 删除账户
	public int deleteAccount(int id) {
		// 定义SQL
		String sql = "delete  from tb_account where id = ? ";
		// 执行添加操作，返回的是受SQL语句影响的记录条数
		int num = this.jdbcTemplate.update(sql, id);
		return num;
	}
	
	// 通过id查询账户数据信息
	public Account findAccountById(int id) {
	    //定义SQL语句
	    String sql = "select * from tb_account,tb_user where TB_ACCOUNT.id = ? and TB_USER.ID=TB_ACCOUNT.USERID";
	    // 创建一个新的BeanPropertyRowMapper对象
//	    RowMapper<Account> rowMapper =
//	new BeanPropertyRowMapper<Account>(Account.class);
	    // 将id绑定到SQL语句中，并通过RowMapper返回一个Object类型的单行记录
	    return this.jdbcTemplate.queryForObject(sql, new AccountRowMapper(), id);
	}

	// 查询所有账户信息
	public List<Account> findAllAccount() {
	    // 定义SQL语句
	    String sql = "select * from tb_account,tb_user where TB_USER.ID=TB_ACCOUNT.USERID";
	    // 创建一个新的BeanPropertyRowMapper对象
//	    RowMapper<Account> rowMapper =
//	new BeanPropertyRowMapper<Account>(Account.class);
	    // 执行静态的SQL查询，并通过RowMapper返回结果
	    return this.jdbcTemplate.query(sql, new AccountRowMapper());
	}
	// 查询所有账户信息
	public List<Account> findAccountByName(String name) {
		// 定义SQL语句
		String sql = "select * from tb_account,tb_user where TB_USER.name=? AND TB_USER.ID=TB_ACCOUNT.USERID";
		// 创建一个新的BeanPropertyRowMapper对象
//	    RowMapper<Account> rowMapper =
//	new BeanPropertyRowMapper<Account>(Account.class);
		// 执行静态的SQL查询，并通过RowMapper返回结果
		return this.jdbcTemplate.query(sql, new AccountRowMapper(),name);
	}

	// 更新余额
	public int updateBalance(int id,float money,boolean add){
		int num=0;
		if (add)
		num=this.jdbcTemplate.update("update tb_account set balance = balance +? "
				+ "where id = ?",money, id);
		else
			num=this.jdbcTemplate.update("update tb_account set balance = balance -? "
					+ "where id = ?",money, id);

		return num;
	}

	public int getAccountIdByUserId(int userId){
		String sql = "select id from tb_account where userId = ?";
		RowMapper<Account> rowMapper =
				new BeanPropertyRowMapper<Account>(Account.class);
		Account account=this.jdbcTemplate.queryForObject(sql, rowMapper, userId);
		return account.getId();
	}

	private class AccountRowMapper implements RowMapper<Account> {
		private ResultSet rs;
		private int rowNum;

		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			this.rs = rs;
			this.rowNum = rowNum;
			User user = new User();
			user.setId(rs.getInt("tb_user.id"));
			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			Account account=new Account();
			account.setId(rs.getInt("tb_account.id"));
			account.setBalance(rs.getFloat("balance"));
			account.setUser(user);
			return account;
		}

	}

}
