package com.example.demospringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceController {
	@RequestMapping("/service")
	public String Hello(Model model) {
		System.out.println("service demo");
		model.addAttribute("message","Service Demo");
		return "hello";
	}
}
