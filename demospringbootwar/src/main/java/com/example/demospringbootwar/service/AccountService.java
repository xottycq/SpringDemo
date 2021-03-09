/**
 * Service接口：定义所有需要的业务逻辑方法（名称、参数和返回值）
 */
package com.example.demospringbootwar.service;

import com.example.demospringbootwar.domain.Account;
import com.example.demospringbootwar.domain.User;

import java.util.List;

public interface AccountService {
    //开户销户
    boolean openAccount(User user);
    boolean closeAccount(User user);

    // 存取钱
    boolean saveMoney(User user, float money);
    boolean withdrawMoney(User user, float money);

    // 转账
    boolean transfer(User outUser, User inUser, float money);
    boolean transferWithTransaction(User outUser, User inUser, float money);

    List<Account> queryAllAccounts();
}
