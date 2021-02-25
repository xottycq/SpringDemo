/**configuration加载
 */
package com.example.demospringbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ClassConfiguration {
    @Bean
    public BeanC getBeanC(){
        BeanC beanc = new BeanC();
        beanc.setMessage("Inject by Java Configuration");
        return beanc;
    }
}
