/**
 * Service实现，注入所有需要用的Dao，通过调用Dao的各种方法来完成Service的所有业务逻辑
 */
package com.example.demospringbootwar.service.impl;

import com.example.demospringbootwar.domain.Account;
import com.example.demospringbootwar.domain.User;
import com.example.demospringbootwar.dto.AccountDto;
import com.example.demospringbootwar.mapper.AccountMapper;
import com.example.demospringbootwar.mapper.UserMapper;
import com.example.demospringbootwar.service.AccountService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("accountService2")
public class AccountServiceMybatisImpl implements AccountService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userDao;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AccountMapper accountDao;
    
    //开户销户
    public boolean openAccount(User user){
        System.out.println("AccountServiceMybatisImpl---openAccount:"+user);
        if(user.getName()=="" ||user.getAge()<18) return false;
        try {
            if (userDao.addUser(user) <= 0) return false;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        int userid=user.getId();
        AccountDto account=new AccountDto();
        account.setUserid(userid);
        account.setBalance(0);
       if(accountDao.insertAccount(account)<=0) return false;
        return true;
    }
    
    public boolean  closeAccount(User user){
        System.out.println("AccountServiceMybatisImpl---closeAccount:"+accountDao);
        if(user.getName()=="" ||user.getAge()<18) return false;
        List<Account> accounts=accountDao.findAccountByName(user.getName());
        if (accounts.size()==0) return false;
        for(Account account:accounts)
            accountDao.deleteAccount(account.getId());
        return true;
    }

    // 存取钱
    public boolean  saveMoney(User user,float money){
        System.out.println("AccountServiceMybatisImpl---saveMoney");
        if(user.getName()=="" ||user.getAge()<18 || money<0) return false;
        List<Account> accounts= accountDao.findAccountByName(user.getName());
        System.out.println(accounts.size()+"---"+user.getName());
        if (accounts.size()==0) return false;
        int acctID=accounts.get(0).getId();
        if(accountDao.updateBalance(acctID, money)==0) return false;
        return true;
    }

    public boolean  withdrawMoney(User user,float money){
        System.out.println("AccountServiceMybatisImpl---withdrawMoney");
        if(user.getName()=="" ||user.getAge()<18 || money<=0) return false;
        List<Account> accounts= accountDao.findAccountByName(user.getName());
        if (accounts.size()==0 ) return false;
        if(accounts.get(0).getBalance()<money) return false;
        int acctID=accounts.get(0).getId();
        accountDao.updateBalance(acctID, money*(-1));
        return true;
    }

    //转账
    public boolean  transfer(User outUser, User inUser, float money) {
        System.out.println("AccountServiceMybatisImpl---transfer");
        return withdrawMoney(outUser,money) && saveMoney(inUser,money);
    }

    //带事务处理的转账
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public boolean transferWithTransaction(User outUser, User inUser, float money) {
        System.out.println("AccountServiceMybatisImpl---transferWithTransaction");
        boolean rt1=false;
        boolean rt2=false;
        try{
         rt1= withdrawMoney(outUser,money);
        // 模拟系统运行时的突发性问题
        int i = 1/0;
        rt2=saveMoney(inUser,money);}
        catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
