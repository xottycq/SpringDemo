import com.example.demospringmvc.dao.IUserDao;
import com.example.demospringmvc.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demospringmvc.pojo.User;
import com.example.demospringmvc.pojo.Account;
import com.example.demospringmvc.dao.IAccountDao;
import com.example.demospringmvc.service.UserServiceImpl;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateTest {
	static ApplicationContext applicationContext;
	@Resource(name="user")
	private User p;


	@BeforeAll
	public static void init() {
		System.out.println("初始化数据");
		// 加载配置文件
		applicationContext =
				new ClassPathXmlApplicationContext("applicationContext.xml");
	}
@Test
public void test() {
	p=(User) applicationContext.getBean("user");
	System.out.println(p);
}



	@Test
	public void querryUser(){
		// 获取AccountDao实例
		IUserDao userDao =
				(IUserDao) applicationContext.getBean("userDao");
		List<User> users = userDao.queryAllUser();;
		// 循环输出集合中的对象
		for (User user : users) {
			System.out.println(user);
		}
	}
	//使用execute()方法建表
//	@Test
//	public void mainTest() {
//
//	    // 使用execute()方法执行SQL语句，创建用户账户管理表account
//	    jdTemplate.execute("create table account(" +
//	                           "id int primary key auto_increment," +
//	                           "username varchar(50)," +
//	                           "balance double)");
//	    System.out.println("账户表account创建成功！");
//	}

	@DisplayName("addAccount")
	@Test
	public void addAccountTest() {
	    // 获取AccountDao实例
	    IAccountDao accountDao =
	            (IAccountDao) applicationContext.getBean("accountDao");
	    // 创建Account对象，并向Account对象中添加数据
	    Account account = new Account();
	    account.setUser(new User("张四",20));
	    account.setBalance(1000.00f);
	    // 执行addAccount()方法，并获取返回结果
	    int num = accountDao.addAccount(account);
	    if (num > 0) {
	        System.out.println("成功插入了" + num + "条数据！");
	    } else {
	        System.out.println("插入操作执行失败！");
	    }
	}
	
	@Test
	public void updateAccountTest() {
		// 获取AccountDao实例
	    IAccountDao accountDao =
	            (IAccountDao) applicationContext.getBean("accountDao");
	    // 创建Account对象，并向Account对象中添加数据
	    Account account = new Account();
	    account.setId(1);
	    account.setUser(new User("张三",20));
	    account.setBalance(2000.00f);
	    // 执行updateAccount()方法，并获取返回结果
	    int num = accountDao.updateAccount(account);
	    if (num > 0) {
	        System.out.println("成功修改了" + num + "条数据！");
	    } else {
	        System.out.println("修改操作执行失败！");
	    }
	}
	
	@Test
	public void deleteAccountTest() {
	    // 获取AccountDao实例
	    IAccountDao accountDao =
	            (IAccountDao) applicationContext.getBean("accountDao");
	    // 执行deleteAccount()方法，并获取返回结果
	    int num = accountDao.deleteAccount(1);
	    if (num > 0) {
	        System.out.println("成功删除了" + num + "条数据！");
	    } else {
	        System.out.println("删除操作执行失败！");
	    }
	}

	@Test
	public void findAccountByIdTest() {
	    // 获取AccountDao实例
	    IAccountDao accountDao =
	            (IAccountDao) applicationContext.getBean("accountDao");
	    // 执行findAccountById()方法
	    Account account = accountDao.findAccountById(2);
	    System.out.println(account);
	}

	@Test
	public void findAllAccountTest() {
	    // 获取AccountDao实例
	    IAccountDao accountDao =
	            (IAccountDao) applicationContext.getBean("accountDao");
	    // 执行findAllAccount()方法,获取Account对象的集合
	    List<Account> account = accountDao.findAllAccount();
	    // 循环输出集合中的对象
	    for (Account act : account) {
	        System.out.println(act);
	    }
	}
}
