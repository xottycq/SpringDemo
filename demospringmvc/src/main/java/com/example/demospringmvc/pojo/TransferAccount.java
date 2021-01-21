package com.example.demospringmvc.pojo;


public class TransferAccount {
    private User outuser;
    private User inuser;
    private float money;

    public User getOutuser() {
        return outuser;
    }

    public void setOutuser(User outuser) {
        this.outuser = outuser;
    }

    public User getInuser() {
        return inuser;
    }

    public void setInuser(User inuser) {
        this.inuser = inuser;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString(){
        return "TransferAccount(outname="+outuser.getName()+",inname="+inuser.getName()+",money="+money+")";
    }

}
