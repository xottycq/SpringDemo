/**三种Bean加载方式
 * 1.xml文件加载
 * 1）ApplicationContext：在初始化应用上下文时就实例化所有单实例的Bean
 *   a)ClassPathXmlApplicationContext:缺省资源类型classpath
 *   b)FileSystemXmlApplicationContext:缺省资源类型file,可以用绝对路径（带盘符）或相对路径（项目目录）为根目录
 *   c)XmlWebApplicationContext：web程序专用,xml进行配置
 *     ServletAC包含RootAC，RootAC是ServletAC的Parent，先在RootAC中搜索，然后在ServletAC中搜索bean
 *     WebApplicationContext servletAC = RequestContextUtils.findWebApplicationContext(request);//推荐，
 * 	   Spring容器：WebApplicationContext rootAC = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
 *
 *     ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc); //在获取失败时抛出异常
 *     ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);//在获取失败时返回null
 *     c.1.servlet.getServletContext()
 *     c.2.this.getServletContext()
 *     c.3.request.getSession().getServletContext();
 *   d)AnnotationConfigWebApplicationContext:web程序专用,注解进行配置
 * 2）BeanFactory:在初始化容器时，并未实例化Bean，直到第一次访问某个Bean时才实例化目标Bean
 * 2.注解加载：xml和注解中两种扫描方式
 * 3.java配置加载：AnnotationConfigApplicationContext
 */
package com.example.demospringbean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
public class BeanLoadDemo {
    public static void main(String[] args) {
        //XML加载
        ApplicationContext contextA =
                new ClassPathXmlApplicationContext("beans2.xml"); //缺省资源类型是classpath

        BeanA beanA = (BeanA) contextA.getBean("beanA");
        beanA.getMessage();

        //注解加载
        ApplicationContext contextB =
                new FileSystemXmlApplicationContext("classpath:beans2.xml"); //缺省资源类型是file,文件路径为：demospringbean/build/resources/main/beans2.xml

        BeanB beanB = (BeanB) contextB.getBean("beanB");
        beanB.getMessage();

        //配置加载
        AnnotationConfigApplicationContext contextC = new AnnotationConfigApplicationContext();
        contextC.register(ClassConfiguration.class);
        contextC.refresh();
        BeanC beanc = (BeanC) contextC.getBean("getBeanC");
        beanc.getMessage();
        contextC.close();

        //使用BeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource res = new ClassPathResource("beans2.xml");
        reader.loadBeanDefinitions(res);
        /*等效替代：
         reader.loadBeanDefinitions("beans2.xml");*/
        //可选：BeanFactory beanfactory = (BeanFactory)factory;
        beanA = (BeanA) factory.getBean("beanA");
        beanA.getMessage();

        //ApplicationContextAware接口
        beanA = (BeanA) AppContextUtil.getBean("beanA");
        /*等效替代
          ApplicationContext ctx=AppContextUtil.getApplicationContext();
          beanA = (BeanA) ctx.getBean("beanA");
         */
        beanA.getMessage();


    }
}
