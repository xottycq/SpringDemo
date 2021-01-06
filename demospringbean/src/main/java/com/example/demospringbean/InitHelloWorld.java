package com.example.demospringbean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;
public class InitHelloWorld implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       if(beanName=="hello")
        System.out.println("BeforeInitialization : " + beanName);
        return bean;  // you can return any other object as well
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName=="hello")
        System.out.println("AfterInitialization : " + beanName);
        return bean;  // you can return any other object as well
    }
}
