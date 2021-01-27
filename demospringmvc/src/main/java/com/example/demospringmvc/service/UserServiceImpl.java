package com.example.demospringmvc.service;

import java.util.List;

import com.example.demospringmvc.dao.IUserDao;
import com.example.demospringmvc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demospringmvc.pojo.User;

import javax.annotation.Resource;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource(name="userdao_jdbctemplate")
    private IUserDao userDao;

    @Override
    public List<User> findAllUser() {
        try {
            return userDao.queryAllUser();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

	@Override
	public int add(User user) {
//		int i=1/0; //模拟添加操作后系统突然出现的异常问题
		return  userDao.addUser(user);
	}

}
