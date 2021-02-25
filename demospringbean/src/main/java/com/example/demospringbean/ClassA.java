package com.example.demospringbean;

public class ClassA {
    private String message;
    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("Child Message : " + message);
    }

    private String message2;
    public void setMessage2(String message){
        this.message2  = message;
    }
    public void getMessage2(){
        System.out.println("Child Message : " + message2);
    }
    public void init() {
        // do some initialization work
        System.out.println("Child Initializing......");
    }

    public void destroy() {
        // do some destruction work
        System.out.println("Child Destroying......");
    }
}
