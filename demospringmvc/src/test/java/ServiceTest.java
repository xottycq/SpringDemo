import com.example.demospringmvc.pojo.Account;
import com.example.demospringmvc.pojo.User;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demospringmvc.service.AccountService;
import org.junit.jupiter.api.Test;

import java.util.List;

//测试类
public class ServiceTest {
	static ApplicationContext applicationContext;

	@BeforeAll
	public static void init() {
		System.out.println("初始化数据");
		// 加载配置文件
		applicationContext =
				new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void userServiceTest1() {
		UserService userService =
				(UserService) applicationContext.getBean("userService");
		User user = new User("李四1", 10);
		userService.add(user);
	}

	@Test
	public void accountServiceTest1() {
		AccountService accountService =
				(AccountService) applicationContext.getBean("accountService");
		User user = new User("王七", 22);
		boolean result = accountService.openAccount(user);
		System.out.println(result);
	}

	@Test
	public void accountServiceTest2() {
		AccountService accountService =
				(AccountService) applicationContext.getBean("accountService");
		User user = new User("王七", 22);
		boolean result = accountService.closeAccount(user);
		System.out.println(result);
	}

	@Test
	public void accountServiceTest3() {
		AccountService accountService =
				(AccountService) applicationContext.getBean("accountService");
		User user = new User("王七", 22);
		boolean result = accountService.saveMoney(user, 100);
		System.out.println(result);
	}

	@Test
	public void accountServiceTest4() {
		AccountService accountService =
				(AccountService) applicationContext.getBean("accountService");
		User user = new User("王七", 22);
		boolean result = accountService.withdrawMoney(user, 50);
		System.out.println(result);
	}


	@Test
	public void xmlTest() {

		// 获取accountService实例
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		User user1 = new User("王七", 20);
		User user2 = new User("王六", 20);
		// 调用实例中的转账方法
		accountService.transfer(user1, user2, 10.0f);
		// 输出提示信息
		System.out.println("转账成功！");
	}

	@Test
	public void annotationTest() {
		// 获取accountService实例
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		User user1 = new User("王七", 20);
		User user2 = new User("王六", 20);
		// 调用实例中的转账方法
		accountService.transferWithTransaction(user1, user2, 1.0f);
		// 输出提示信息
		System.out.println("转账成功！");
	}

	@Test
	public void mybatisTest1() {
		// 获取accountService实例
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		List<Account> accounts= accountService.queryAllAccounts();
		for (Account account:accounts){
			System.out.println(account);
		}
	}

	@Test
	public void mybatisTest2() {
		// 获取accountService实例
		UserService userService = (UserService) applicationContext.getBean("userService");
		List<User> users= userService.findAllUser();
		for (User user:users){
			System.out.println(user);
		}
	}
}
