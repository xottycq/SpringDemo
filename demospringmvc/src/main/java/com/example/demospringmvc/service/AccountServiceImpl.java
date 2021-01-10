package com.example.demospringmvc.service;

import com.example.demospringmvc.dao.AccountDao;
import com.example.demospringmvc.dao.IAccountDao;
import com.example.demospringmvc.pojo.Account;
import com.example.demospringmvc.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    @Resource
    private IAccountDao accountDao;

    //开户销户
    public boolean openAccount(User user){
        if(user.getName()=="" ||user.getAge()<18) return false;
        Account account=new Account();
        account.setUser(user);
        accountDao.addAccount(account);
        return true;
    }
    
    public boolean  closeAccount(User user){
        if(user.getName()=="" ||user.getAge()<18) return false;
        List<Account> accounts=accountDao.findAccountByName(user.getName());
        for(Account account:accounts)
            accountDao.deleteAccount(account.getId());
        return true;
    }
    // 存取钱
    public boolean  saveMoney(User user,float money){
        if(user.getName()=="" ||user.getAge()<18 || money<=0) return false;
        List<Account> accounts= accountDao.findAccountByName(user.getName());
        if (accounts.size()==0) return false;
        int acctID=accounts.get(0).getId();
        accountDao.updateBalance(acctID, money,true);
        return true;
    }

    public boolean  withdrawMoney(User user,float money){
        if(user.getName()=="" ||user.getAge()<18 || money<=0) return false;
        List<Account> accounts= accountDao.findAccountByName(user.getName());
        if (accounts.size()==0 ) return false;
        if(accounts.get(0).getBalance()<money) return false;
        int acctID=accounts.get(0).getId();
        accountDao.updateBalance(acctID, money,false);
        return true;
    }

    //转账
    public void  transfer(User outUser, User inUser, float money) {
        withdrawMoney(outUser,money);
        saveMoney(inUser,money);
    }

    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT, readOnly = false)
    public void  transferWithTransaction(User outUser, User inUser, float money) {
        withdrawMoney(outUser,money);
        // 模拟系统运行时的突发性问题
        int i = 1/0;
        saveMoney(inUser,money);
    }
}
