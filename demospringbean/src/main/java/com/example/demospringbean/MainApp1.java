package com.example.demospringbean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static org.springframework.boot.logging.LogLevel.WARN;

public class MainApp1 {
    public static void main(String[] args) {
        AbstractApplicationContext  context =
                new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld objA = (HelloWorld) context.getBean("hello");
        objA.setMessage("I'm object A");
        objA.getMessage();
        HelloWorld objB =  context.getBean(HelloWorld.class);
        objB.getMessage();

        HelloChina objC = (HelloChina) context.getBean("helloChina",HelloChina.class);
        objC.getMessage1();
        objC.getMessage2();

        context.close();
        /*等效替代
        Resource resource=new ClassPathResource("Beans.xml");
        BeanFactory factory=new DefaultListableBeanFactory();
        BeanDefinitionReader bdr=new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
        bdr.loadBeanDefinitions(resource);
        HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");*/
    }
}
