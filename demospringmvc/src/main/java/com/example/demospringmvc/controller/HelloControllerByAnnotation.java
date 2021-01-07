package com.example.demospringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloControllerByAnnotation {
    @RequestMapping("/hello-annotation")    //这里相对于类上标注的路径，若类上没有标注则是绝对路径
    public ModelAndView sayHello() {
        System.out.println("HelloControllerByAnnotation----Hello Spring MVC!");
        ModelAndView mv=new ModelAndView();
        mv.addObject("message","Hello from Annotation!");
        mv.setViewName("hello");
        return mv;
    }
}
