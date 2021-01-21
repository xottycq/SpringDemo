package com.example.demospringmvc.dao;

import com.example.demospringmvc.pojo.Account;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository(value="accountDao2")
public class AccountDaoByMybatis implements IAccountDao {
	public AccountDaoByMybatis(){};
	@Resource
	private SqlSessionTemplate sqlSession;
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
//	public  AccountDaoByMybatis(SqlSessionTemplate sqlSession){
//		System.out.println("Inside  AccountDaoByMybatis constructor." );
//		this.sqlSession = sqlSession;
//	};

	// 添加账户
	public int addAccount(Account account) {
		System.out.println("mybatis");
		return 0;
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
//		int num = this.jdbcTemplate.update(sql, params);
		return 0;
	}
	// 删除账户
	public int deleteAccount(int id) {
		// 定义SQL
		String sql = "delete  from tb_account where id = ? ";
		// 执行添加操作，返回的是受SQL语句影响的记录条数
//		int num = this.jdbcTemplate.update(sql, id);
		return 0;
	}
	
	// 通过id查询账户数据信息
	public Account findAccountById(int id) {
	    //定义SQL语句
	    String sql = "select * from tb_account,tb_user where TB_ACCOUNT.id = ? and TB_USER.ID=TB_ACCOUNT.USERID";
	    // 创建一个新的BeanPropertyRowMapper对象
//	    RowMapper<Account> rowMapper =
//	new BeanPropertyRowMapper<Account>(Account.class);
	    // 将id绑定到SQL语句中，并通过RowMapper返回一个Object类型的单行记录
//	    return this.jdbcTemplate.queryForObject(sql, new AccountRowMapper(), id);
		return new Account();
	}

	// 查询所有账户信息
	public List<Account> findAllAccount() {
		System.out.println("Mybatis-----"+sqlSession);
		return sqlSession.selectList(
				"findAllAccount");
		/*等效替代：
		IAccountDao mapper = sqlSession.getMapper(IAccountDao.class);
		return mapper.findAllAccount();*/
	}

	// 查询所有账户信息
	public List<Account> findAccountByName(String name) {
		// 定义SQL语句
		String sql = "select * from tb_account,tb_user where TB_USER.name=? AND TB_USER.ID=TB_ACCOUNT.USERID";
		// 创建一个新的BeanPropertyRowMapper对象
//	    RowMapper<Account> rowMapper =
//	new BeanPropertyRowMapper<Account>(Account.class);
		// 执行静态的SQL查询，并通过RowMapper返回结果
//		return this.jdbcTemplate.query(sql, new AccountRowMapper(),name);
		return new ArrayList<Account>();
	}

	// 更新余额
	public int updateBalance(int id,float money,boolean add){
		int num=0;
//		if (add)
//		num=this.jdbcTemplate.update("update tb_account set balance = balance +? "
//				+ "where id = ?",money, id);
//		else
//			num=this.jdbcTemplate.update("update tb_account set balance = balance -? "
//					+ "where id = ?",money, id);

		return num;
	}

	public int getAccountIdByUserId(int userId){
		String sql = "select id from tb_account where userId = ?";
		RowMapper<Account> rowMapper =
				new BeanPropertyRowMapper<Account>(Account.class);
//		Account account=this.jdbcTemplate.queryForObject(sql, rowMapper, userId);
//		return account.getId();
		return 0;
	}


}
