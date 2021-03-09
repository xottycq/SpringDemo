/**springboot访问静态资源，有两个默认目录：
 1.src/mian/resources(通常它就是classpath)：优先级排序为：/META-INF/resources/>>>/resources/>>>/static/>>>/public/
 2.ServletContext 根目录(src/main/webapp )
可以通过 spring.web.resources.static-locations来配置，一旦配置，原来的默认就失效了
 */
package com.example.demospringboot.controller;

import com.alibaba.fastjson.JSON;
import com.example.demospringboot.domain.TransferAccount;
import com.example.demospringboot.domain.User;
import com.example.demospringboot.service.AccountService;
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
	@Resource
	private AccountService accountService;

	@RequestMapping("/hello")
	public String hello(Model m){
		System.out.println("Hello SpringBoot in JSP(hello.jsp)!");
		m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
		return "hello";
	}

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
