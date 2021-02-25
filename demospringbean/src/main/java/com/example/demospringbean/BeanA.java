/**XML加载
 */
package com.example.demospringbean;

public class BeanA {
    private String message;
    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("BeanA Message : " + message);
    }

}
