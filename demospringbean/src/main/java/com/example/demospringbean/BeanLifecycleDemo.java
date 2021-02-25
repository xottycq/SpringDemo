/** 1、Bean自身的方法：配置文件中<bean>的init-method和destroy-method指定的方法
    2、Bean级生命周期接口方法：BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法
    3、容器级生命周期接口方法：InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。
    4、工厂后处理器接口方法：这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等
 非常有用的工厂后处理器　　接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。
 *开启：BeanFactoryPostProcessor->BeanPostProcessor->InstantiationAwareBeanPostProcessor->BeanNameAware->BeanFactoryAware
 *InitializingBean接口调用afterPropertiesSet()->init-method->BeanPostProcessor调用postProcessAfterInitialization->
 *InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization
 *关闭：DiposibleBean接口调用destory()->destroy-method
 */
package com.example.demospringbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleDemo {
    public static void main(String[] args) {

        System.out.println("开始初始化Spring容器......");

        ApplicationContext factory = new ClassPathXmlApplicationContext("beans3.xml");

        System.out.println("Spring容器初始化成功!");

        //获取Preson对象，并使用
        Person person = factory.getBean("person",Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭Spring容器......");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}
