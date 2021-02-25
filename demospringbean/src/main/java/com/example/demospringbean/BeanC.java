/**configuration注解加载
 */
package com.example.demospringbean;

public class BeanC {
    private String message;
    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("BeanC Message : " + message);
    }

}
