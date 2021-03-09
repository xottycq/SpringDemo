/**项目入口程序
 *只会扫描本本启动类当前包和以下的包，否则需要配置@ComponentScan指定额外需要扫秒的包
 *缺省读取resources下的application.properties或application.yml文件作为系统配置
 *项目总体目录结构如下：
 * main
 *   --java：xxxApplication.java(入口)、controller、service、dao、domain...
 *   --resources：pplication.properties、静态资源、模版、mybatis xml映射文件
 *   --webapp：jsp
 * test
 *   --java：单元测试程序
 * */
package com.example.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemospringbootApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemospringbootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemospringbootApplication.class, args);
    }
}
