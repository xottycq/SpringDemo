/**实体类：区别于controller、service和dao等功能类的数据类，不需要注入而交由spring管理
 *数据类是带状态的（不同值就是不同状态），通常也不适合使用单例模式
 *标准构成：private属性+getter/setter+toString
 */
package com.example.demospringbootwar.domain;

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
