/**注解加载
 */
package com.example.demospringbean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class BeanB {
    @Value("Inject by Annotation")
    private String message;
    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("BeanB Message : " + message);
    }

}
