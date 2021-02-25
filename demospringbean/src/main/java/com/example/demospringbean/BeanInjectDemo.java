package com.example.demospringbean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class BeanInjectDemo {
    public static void main(String[] args) {
        AbstractApplicationContext  context =
                new ClassPathXmlApplicationContext("Beans.xml");
        /*******Spring Bean的注入概念及父子Bean********/
        //获取实例并改变message属性值
        ClassParentOfA objAP1 = (ClassParentOfA) context.getBean("helloworld");
        objAP1.setMessage("I'm object A");
        objAP1.getMessage();
        //因scope="prototype"，重新获取实例时，message属性的初值由bean.xml决定
        ClassParentOfA objAP2 =  context.getBean(ClassParentOfA.class);
        objAP2.getMessage();

        ClassA objA =  context.getBean("helloChina", ClassA.class);
        //message值来自其父类
        objA.getMessage();
        //message2值在beans.xml中定义
        objA.getMessage2();

        /*等效替代
        Resource resource=new ClassPathResource("Beans.xml");
        BeanFactory factory=new DefaultListableBeanFactory();
        BeanDefinitionReader bdr=new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
        bdr.loadBeanDefinitions(resource);
        ClassParentOfA obj = (ClassParentOfA) factory.getBean("helloWorld");*/

        /*******Spring Bean的4种注入方式，前两种常用********/
        //1.构造器注入
        System.out.println("1.构造器注入");
        ClassB objB1 =  (ClassB) context.getBean("classB1");
        objB1.spellCheck();

        //2.setter注入
        System.out.println("2.setter注入");
        ClassB objB2 = (ClassB) context.getBean("classB2");
        objB2.spellCheck();

        //3.静态工厂注入
        System.out.println("3.静态工厂注入");
        ClassB objB3 = (ClassB) context.getBean("classB3");
        objB3.spellCheck();

        //4.实例工厂注入
        System.out.println("4.实例工厂注入");
        ClassB objB4 = (ClassB) context.getBean("classB4");
        objB4.spellCheck();

        //嵌套bean（xml中嵌套格式）
        System.out.println("嵌套bean");
        ClassB objB = (ClassB) context.getBean("classB");
        objB.spellCheck();

        //集合注入
        System.out.println("集合注入");
        ClassCollection jc=(ClassCollection)context.getBean("javaCollection");
        jc.getAddressList();
        jc.getAddressSet();
        jc.getAddressMap();
        jc.getAddressProp();
        context.close();
    }
}
