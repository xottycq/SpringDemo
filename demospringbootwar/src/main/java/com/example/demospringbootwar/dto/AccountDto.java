/**用于传输、转换的数据对象
 */
package com.example.demospringbootwar.dto;


public class AccountDto {
    private int id;
    private int userid;
    private float balance;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public AccountDto(){
        super();
    }

    @Override
    public String toString(){
        return "Account(id="+id+",userid="+userid+",balance="+balance+")";
    }
}
