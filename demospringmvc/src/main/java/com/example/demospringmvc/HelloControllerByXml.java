package com.example.demospringmvc;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloControllerByXml implements Controller{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("HelloControllerByXml----Hello Spring MVC!");
        ModelAndView mv=new ModelAndView();
        mv.addObject("message","Hello from XML!");
        mv.setViewName("hello");
        return mv;
    }
}
