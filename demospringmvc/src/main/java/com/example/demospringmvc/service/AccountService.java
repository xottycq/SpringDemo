package com.example.demospringmvc.service;

import com.example.demospringmvc.pojo.User;

public interface AccountService {
    //开户销户
    public boolean  openAccount(User user);
    public boolean  closeAccount(User user);
    // 存取钱
    public boolean  saveMoney(User user,float money);
    public boolean  withdrawMoney(User user,float money);
    // 转账
    public void transfer(User outUser,User inUser,float money);
    public void transferWithTransaction(User outUser,User inUser,float money);
}
