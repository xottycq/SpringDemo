/**与SpringMVC完全一样
 * Service实现，注入所有需要用的Dao，通过调用Dao的各种方法来完成Service的所有业务逻辑
 * AccountDao可以使用下列三种之一：
 *  1.   @Autowired
 *       @Qualifier(value = "accountDao_jdbctemplate")
 *       private IAccountDao accountDao;
 *  2.   @Autowired
 *       @Qualifier(value = "accountDao_mybatis")
 *       private IAccountDao accountDao;
 *  3.   @Autowired
 *       private AccountMapper accountDao;
 * 同样：UserDao也可以使用下列三种之一：
 *  1.   @Autowired
 *       @Qualifier(value = "userDao_jdbctemplate")
 *       private IUserDao userDao;
 *  2.   @Autowired
 *       @Qualifier(value = "userDao_mybatis")
 *       private IUserDao userDao;
 *  3.   @Autowired
 *       private UserDaoMapper userDao;
 */
package com.example.demospringboot.service.impl;

import com.example.demospringboot.dao.AccountMapper;
import com.example.demospringboot.dao.IAccountDao;
import com.example.demospringboot.dao.IUserDao;
import com.example.demospringboot.dao.UserDaoMapper;
import com.example.demospringboot.domain.Account;
import com.example.demospringboot.domain.User;
import com.example.demospringboot.dto.AccountDto;
import com.example.demospringboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier(value = "accountdao_jdbctemplate")
    private IAccountDao accountDao;
    @Autowired
    @Qualifier(value = "userdao_jdbctemplate")
    private IUserDao userDao;


//    @Autowired
//    @Qualifier(value = "accountdao_mybatis")
//    private IAccountDao accountDao;
//    @Autowired
//    @Qualifier(value = "userdao_mybatis")
//    private IUserDao userDao;


//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    private AccountMapper accountDao;
//
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    private UserDaoMapper userDao;

    //开户
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

    //销户
    public boolean  closeAccount(User user){
        System.out.println("AccountServiceImpl---closeAccount:"+accountDao);
        if(user.getName()=="" ||user.getAge()<18) return false;
        List<Account> accounts=accountDao.findAccountByName(user.getName());
        if (accounts.size()==0) return false;
        int n=0;
        for(Account account:accounts)
            n+=accountDao.deleteAccount(account.getId());
        return (n>0);
    }

    // 存取钱
    public boolean  saveMoney(User user,float money){
        System.out.println("AccountServiceImpl---saveMoney");
        if(user.getName()=="" ||user.getAge()<18 || money<0) return false;
        List<Account> accounts= accountDao.findAccountByName(user.getName());
        System.out.println(accounts.size()+"---"+user.getName());
        if (accounts.size()==0) return false;
        int acctID=accounts.get(0).getId();
        if(accountDao.updateBalance(acctID, money)==0) return false;
        return true;
    }

    public boolean  withdrawMoney(User user,float money){
        System.out.println("AccountServiceImpl---withdrawMoney");
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
        System.out.println("AccountServiceImpl---transfer");
        return withdrawMoney(outUser,money) && saveMoney(inUser,money);
    }

    //带事务处理的转账
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public boolean transferWithTransaction(User outUser, User inUser, float money) {
        System.out.println("AccountServiceImpl---transferWithTransaction");
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
