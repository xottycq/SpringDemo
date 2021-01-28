/**
 * Service实现，注入所有需要用的Dao，通过调用Dao的各种方法来完成Service的所有业务逻辑
 */
package com.example.demospringmvc.service;

import com.example.demospringmvc.dao.IAccountDao;
import com.example.demospringmvc.pojo.Account;
import com.example.demospringmvc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @Qualifier("accountDao_jdbctemplate")
    private IAccountDao accountDao;

    //开户销户
    public boolean openAccount(User user){
        System.out.println("openAccount===="+user);
        if(user.getName()=="" ||user.getAge()<18) return false;
        Account account=new Account();
        account.setUser(user);
       if(accountDao.addAccount(account)<=0) return false;
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
        if(user.getName()=="" ||user.getAge()<18 || money<0) return false;
        List<Account> accounts= accountDao.findAccountByName(user.getName());
        if (accounts.size()==0) return false;
        int acctID=accounts.get(0).getId();
        if(accountDao.updateBalance(acctID, money,true)==0) return false;
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
    public boolean  transfer(User outUser, User inUser, float money) {
        return withdrawMoney(outUser,money) && saveMoney(inUser,money);
    }

    //带事务处理的转账
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT, readOnly = false)
    public boolean transferWithTransaction(User outUser, User inUser, float money) {
        boolean rt1=false;
        boolean rt2=false;
        try{
         rt1= withdrawMoney(outUser,money);
        // 模拟系统运行时的突发性问题
        int i = 1/0;
        rt2=saveMoney(inUser,money);}
        catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            return rt1&&rt2;
        }
    }

    //账户清单列表
    public List<Account> queryAllAccounts(){
        List<Account> accounts= accountDao.findAllAccount();
        return accounts;
    }
}
