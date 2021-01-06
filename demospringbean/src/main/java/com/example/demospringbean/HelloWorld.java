package com.example.demospringbean;

public class HelloWorld {
    private String message;
    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("Parent Message : " + message);
    }

    public void init() {
        // do some initialization work
        System.out.println("Initializing......");
    }

    public void destroy() {
        // do some destruction work
        System.out.println("Destroying......");
    }
}
