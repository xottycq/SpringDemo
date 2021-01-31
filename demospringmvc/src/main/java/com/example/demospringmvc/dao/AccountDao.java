/**Dao:调用Sql语句，完成与数据库的各种交互操作（增删改查）
 * 本例中使用Spring自带的JdbcTemplate提供的一系列SQL操作方法来具体操作SQL
 * 1）execute：可以执行所有SQL语句，一般用于执行DDL语句。
 * 2）update：用于执行INSERT、UPDATE、DELETE等DML语句，返回成功处理的记录行数
 * 3）queryXXX：用于DQL数据查询语句，返回int、long、object、map、list，对于返回List<T>需要做数据库字段和对象属性之间的映射
 *   a)List<Account> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));此时需要确保数据库表列名称与Java实体类属性名称相同
 *   b)List<String> list = jdbcTemplate.query(sql, new SingleColumnRowMapper<>(String.class))--返回单列数据
 *   c)List<Product> query = jdbcTemplate.query(sql, new RowMapper<Product>() {
 *       @Override
 *       public Product mapRow(ResultSet arg0, int arg1) throws SQLException {
 *          Product p = new Product();
 *          p.setPid(arg0.getInt("pid"));
 *          p.setPname(arg0.getString("pname"));
 *          p.setPrice(arg0.getDouble("price"));
 *          return p;
 *       }
 *    });
 *   query---返回多行记录    queryForObject---返回单行记录
 */
package com.example.demospringmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.demospringmvc.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demospringmvc.pojo.Account;

import javax.annotation.Resource;

public class AccountDao implements IAccountDao {
	// 声明JdbcTemplate属性及其setter方法,配合xml中此类的配置语句<bean id="accountDao_jdbctemplate">完成了该属性初始化赋值
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Resource
	private UserDao userDao;

	// 添加账户
	public int addAccount(Account account) {
		System.out.println("jdbc--dao");
		int userid=userDao.addUser(account.getUser());
		if(userid<=0) return userid;
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
		int num;
		// 定义SQL
		String sql1 = "delete from tb_user where id in (select userid from  tb_account where id = ?)";
		this.jdbcTemplate.update(sql1, id);
		String sql2 = "delete  from tb_account where id = ? ";
		// 执行添加操作，返回的是受SQL语句影响的记录条数
		 num= this.jdbcTemplate.update(sql2, id);
		return num;
	}
	
	// 通过id查询账户数据信息
//	public Account findAccountById(int id) {
//	    //定义SQL语句
//	    String sql = "select * from tb_account,tb_user where TB_ACCOUNT.id = ? and TB_USER.ID=TB_ACCOUNT.USERID";
//	    // 创建一个新的BeanPropertyRowMapper对象
////	    RowMapper<Account> rowMapper =
////	new BeanPropertyRowMapper<Account>(Account.class);
//	    // 将id绑定到SQL语句中，并通过RowMapper返回一个Object类型的单行记录
//	    return this.jdbcTemplate.queryForObject(sql, new AccountRowMapper(), id);
//	}

	// 查询所有账户信息
	public List<Account> findAllAccount() {
		System.out.println("JDBC Template");
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
		System.out.println(name);
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

//	public int getAccountIdByUserId(int userId){
//		String sql = "select id from tb_account where userId = ?";
//		RowMapper<Account> rowMapper =
//				new BeanPropertyRowMapper<>(Account.class);
//		Account account=this.jdbcTemplate.queryForObject(sql, rowMapper, userId);
//		return account.getId();
//	}

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
			System.out.println(account);
			return account;
		}

	}

}
