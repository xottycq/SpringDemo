package com.example.demospringmvc.controller;

import com.example.demospringmvc.dao.IAccountDao;
import com.example.demospringmvc.dao.IUserDao;
import com.example.demospringmvc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DaoController {
	@Autowired
	@Qualifier("userdao_jdbctemplate")
	private IUserDao userDao;

	@RequestMapping("jdbctemplate")
	public String demo1(Model model) {
		System.out.println("jdbcTemplate demo");
		model.addAttribute("daotitle","Dao Demo--jdbcTemplate");
		return "daomenu";
	}

	@RequestMapping("mybatis")
	public String demo2(Model model) {
		System.out.println("mybatis demo");
		model.addAttribute("daotitle","Dao Demo--mybatis");
		return "daomenu";
	}

	@RequestMapping("adduser")
	public String adduser(User user, Model model) {
		System.out.println("adduser："+user);
		model.addAttribute("operation","新增用户");
		if(userDao.addUser(user)>0)
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("deleteuser")
	public String deleteuser(String name,Model model) {
		System.out.println("deleteuser："+name);
		model.addAttribute("operation","删除用户");
		if(userDao.deleteUser(name)>0)
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("updateuser")
	public String updateuser(User user,Model model) {
		System.out.println("updateuser："+user);
		model.addAttribute("operation","修改用户");
		if(userDao.updateUser(user)>0)
			model.addAttribute("result","成功！");
		else
			model.addAttribute("result","失败！");
		return "result";
	}

	@RequestMapping("queryuser")
	public String queryuser(String name,Model model) {
		System.out.println("queryuser："+name);
		model.addAttribute("operation","查询用户");
		User user=userDao.queryUser(name);
		if(user!=null) {
			model.addAttribute("result", "成功！");
			List<User> list = new ArrayList<>();
			list.add(user);
			model.addAttribute("users", list);
		}
		else
			model.addAttribute("result","失败！");

		return "result";
	}

	@RequestMapping("queryalluser")
	public String queryalluser(Model model) {
		System.out.println("queryalluser");
		model.addAttribute("operation","查询用户");
		List<User> list =userDao.queryAllUser();
		if(list.size()>0) {
			model.addAttribute("result", "成功！");
			model.addAttribute("users", list);
		}
		else {
			model.addAttribute("result", "失败！");
		}
		return "result";
	}


}
