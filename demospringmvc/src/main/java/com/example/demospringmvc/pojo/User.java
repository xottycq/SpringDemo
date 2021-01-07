package com.example.demospringmvc.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller("user")
public class User {
    private String name;
    private int age;
    public User(){
        super();
     }
    public User(String name,int age){
        super();
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    @Value("Tom")
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Value("12")
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString(){
        return "User(name="+name+",age="+age+")";
    }
}
