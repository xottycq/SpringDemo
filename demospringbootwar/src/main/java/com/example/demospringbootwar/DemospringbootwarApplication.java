package com.example.demospringbootwar;

import com.example.demospringbootwar.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*等效替换：
@SpringBootApplication+@Mapper（UserMapper/AccuntMapper）
*/
@SpringBootApplication
@MapperScan(value = "com.example.demospringbootwar.mapper")
public class DemospringbootwarApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemospringbootwarApplication.class);
    }

   public static void main(String[] args) {
        SpringApplication.run(DemospringbootwarApplication.class, args);
    }

}
