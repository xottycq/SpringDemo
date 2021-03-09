/**springboot访问静态资源，默认有两个默认目录：
 1.src/mian/resource目录
 2.ServletContext 根目录下( src/main/webapp )
可以通过 spring.web.resources.static-locations来配置
 * 通过银行基本业务演示了Controller对Service的调用，关键是通过@Resource注入所需的AccountSerrvice接口
 * 将Controller从页面获取的数据经适当加工后传入到Service中，调用其完成后的返回值再通过Model传回页面
 */
package com.example.demospringbootwar.controller;

import com.alibaba.fastjson.JSON;
import com.example.demospringbootwar.domain.TransferAccount;
import com.example.demospringbootwar.domain.User;
import com.example.demospringbootwar.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class AccountController {

	@RequestMapping("/hello")
	public String hello(Model m){
		System.out.println("Hello SpringBoot in JSP(hello.jsp)!");
		m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
		return "hello";
	}

	@Resource(name = "accountService2")
	private AccountService accountService;

	@GetMapping("/accountdemo")
		public String accountdemo(){
		System.out.println("Account Demo");
			return "accountmenu";
	}

	@RequestMapping("openaccount")
	public String openaccount(User user,Model model) {
		System.out.println("AccountController---openaccount："+user);
		model.addAttribute("operation","开户");
		if(accountService.openAccount(user))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("closeaccount")
	public String closeaccount(User user,Model model) {
		System.out.println("AccountController---closeaccount："+user);
		model.addAttribute("operation","销户");
		if(accountService.closeAccount(user))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("save")
	public String save(User user,float money,Model model) {
		System.out.println("AccountController---save："+user+"----"+money);
		user.setAge(20);
		model.addAttribute("operation","存款");
		if(accountService.saveMoney(user,money))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}
	@RequestMapping("withdraw")
	public String withdraw(User user,float money,Model model) {
		System.out.println("AccountController---withdraw："+user+"----"+money);
		user.setAge(20);
		model.addAttribute("operation","取款");
		if(accountService.withdrawMoney(user,money))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@PostMapping("transfer1")
	public String transfer1(@RequestBody HashMap<String, Object> map, Model model) {
		System.out.println("AccountController---transfer1"+map);
		model.addAttribute("operation","转账1");
		User outuser=JSON.parseObject(JSON.toJSONString(map.get("outuser")),User.class);
		User inuser= JSON.parseObject(JSON.toJSONString(map.get("inuser")),User.class);
		float money=(Integer)map.get("money");
		if(accountService.transfer(outuser,inuser,money))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("transfer2")
	public String transfer2(@RequestBody TransferAccount taccount, Model model) {
		System.out.println("AccountController---transfer2"+taccount.getOutuser());
		model.addAttribute("operation","转账2");
		if(accountService.transferWithTransaction(taccount.getOutuser(),taccount.getInuser(),taccount.getMoney()))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

}
