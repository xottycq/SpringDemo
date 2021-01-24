package com.example.demospringmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demospringmvc.pojo.TransferAccount;
import com.example.demospringmvc.pojo.User;
import com.example.demospringmvc.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller

public class AccountController {

	@Resource
	private AccountService accountService;

	@RequestMapping("service")
	public String Hello(Model model) {
		System.out.println("service demo");
		model.addAttribute("message","Service Demo");
		return "servicemenu";
	}

	@RequestMapping("openaccount")
	public String openaccount(User user,Model model) {
		System.out.println("openaccount："+user);
		model.addAttribute("operation","开户");
		if(accountService.openAccount(user))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("closeaccount")
	public String closeaccount(User user,Model model) {
		System.out.println("closeaccount："+user);
		model.addAttribute("operation","销户");
		if(accountService.closeAccount(user))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("save")
	public String save(User user,float money,Model model) {
		System.out.println("save："+user+"----"+money);
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
		System.out.println("withdraw："+user+"----"+money);
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
		model.addAttribute("operation","转账1");
		User  outuser=JSON.toJavaObject((JSONObject)map.get("outuser"),User.class);
		User inuser= JSON.toJavaObject((JSONObject)map.get("inuser"),User.class);
		float money=(Integer)map.get("money");
		System.out.println("transfer1"+"----"+outuser+"----"+inuser+"----"+money);
		if(accountService.transfer(outuser,inuser,money))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("transfer2")
	public String transfer2(@RequestBody TransferAccount taccount, Model model) {
		System.out.println("transfer2"+taccount.getOutuser());
		model.addAttribute("operation","转账2");
		if(accountService.transferWithTransaction(taccount.getOutuser(),taccount.getInuser(),taccount.getMoney()))
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

}
