/**SpringMVC
 * 1 => StartupListener.setApplicationContext
 * 2 => StartupListener.setServletContext
 * 3 => StartupListener.afterPropertiesSet
 * 4.1 => MyApplicationListener.onApplicationEvent
 * 4.2 => MyApplicationListener.onApplicationEvent
 * 4.1 => MyApplicationListener.onApplicationEvent
 */
package com.example.demospringmvc.init;

import org.h2.tools.Server;
import org.springframework.beans.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import javax.servlet.ServletContext;
import java.sql.SQLException;

@Component
public class StartupListener implements ApplicationContextAware, ServletContextAware,
        InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        System.out.println( "1 => StartupListener.setApplicationContext" );
//        try {
//            System.out.println("正在启动h2...");
//            Server server = Server.createTcpServer(
//                    new String[] { "-tcp", "-tcpAllowOthers", "-tcpPort",
//                            "9092" }).start();
//            System.out.println("启动成功：" + server.getStatus());
//        } catch (SQLException e) {
//            System.out.println("启动h2出错：" + e.toString());
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

    }

    @Override
    public void setServletContext(ServletContext context) {
        System.out.println( "2 => StartupListener.setServletContext" );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println( "3 => StartupListener.afterPropertiesSet" );
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        System.out.println( "4.1 => MyApplicationListener.onApplicationEvent" );
        if (evt.getApplicationContext().getParent() == null ) {
            System.out.println( "4.2 => MyApplicationListener.onApplicationEvent" );
        }
    }
}
