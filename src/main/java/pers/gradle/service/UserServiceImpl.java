package pers.gradle.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import pers.gradle.dao.UserDao;
import pers.gradle.model.User;

import java.util.List;

/**
 * 用户信息service实现
 * @author Leo
 * @date 13:25 2019/5/22
**/
@Service
public class UserServiceImpl implements UserService{

    @Override
    public List<User> getUserList() {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("beans.xml");
        UserDao userDao=applicationContext.getBean(UserDao.class);
        return userDao.getUserList();
    }
}
