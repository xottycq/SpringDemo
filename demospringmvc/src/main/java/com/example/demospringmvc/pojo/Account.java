package com.example.demospringmvc.pojo;

import org.springframework.stereotype.Controller;

//@Controller("account")
public class Account {
    private int id;
    private User user;
    private float balance;

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Account(){
        super();
     }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return "Account(name="+user.getName()+",age="+user.getAge()+",balance="+balance+")";
    }

}
