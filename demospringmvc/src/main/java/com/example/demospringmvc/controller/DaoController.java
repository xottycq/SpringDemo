package com.example.demospringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DaoController {
	@RequestMapping("/jdbctemplate")
	public String Hello1(Model model) {
		System.out.println("jdbcTemplate demo");
		model.addAttribute("message","Dao Demo--jdbcTemplate");
		return "hello";
	}

	@RequestMapping("/mybatis")
	public String Hello2(Model model) {
		System.out.println("mybatis demo");
		model.addAttribute("message","Dao Demo--mybatis");
		return "hello";
	}
}
