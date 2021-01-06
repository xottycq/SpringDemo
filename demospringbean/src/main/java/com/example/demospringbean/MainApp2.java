package com.example.demospringbean;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp2 {
    public static void main(String[] args) {
        AbstractApplicationContext  context =
                new ClassPathXmlApplicationContext("Beans.xml");
        System.out.println("构造器注入");
        TextEditor te1 = (TextEditor) context.getBean("textEditor1");
        te1.spellCheck();
        System.out.println("setter注入");
        TextEditor te2 = (TextEditor) context.getBean("textEditor2");
        te2.spellCheck();
        System.out.println("嵌套bean");
        TextEditor te = (TextEditor) context.getBean("textEditor");
        te.spellCheck();

        System.out.println("注入集合");
        JavaCollection jc=(JavaCollection)context.getBean("javaCollection");
        jc.getAddressList();
        jc.getAddressSet();
        jc.getAddressMap();
        jc.getAddressProp();
    }
}
