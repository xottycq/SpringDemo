package com.example.demospringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterceptorController {
	@RequestMapping("/interceptor")
	public String Hello(Model model) {
		System.out.println("interceptor");
		model.addAttribute("message","Interceptor Demo");
		return "hello";
	}
}
