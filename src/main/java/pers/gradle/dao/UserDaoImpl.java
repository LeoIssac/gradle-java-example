package pers.gradle.dao;

import pers.gradle.model.User;

import java.util.ArrayList;
import java.util.List;
/**
 * 用户信息dao实现类
 * @author Leo
 * @date 13:23 2019/5/22
 * @param
 * @return
**/
public class UserDaoImpl implements UserDao{


    @Override
    public List<User> getUserList() {
        List<User> userList=new ArrayList<>();
        User userZs=new User().setId(1L).setName("张三").setAge(18);
        userList.add(userZs);
        User userLs=new User().setId(2L).setName("李四").setAge(20);
        userList.add(userLs);

        return userList;
    }
}
