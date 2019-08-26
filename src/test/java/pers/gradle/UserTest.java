package pers.gradle;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.gradle.model.User;
import pers.gradle.service.UserService;

import java.util.List;

public class UserTest {
    @Test
    public void getUserList(){
        //获取spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("beans.xml");
        //从容器中获取UserService对象
        UserService userService=applicationContext.getBean(UserService.class);
        //调用方法
        List<User> userList = userService.getUserList();
        //输出用户列表
        userList.forEach(u-> System.out.println("用户"+u.getName()+"："+u));
    }
}
