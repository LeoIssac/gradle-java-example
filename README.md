# gradle-java-spring
#1.创建 Gradle Java 项目

**1.**`new project`—`Gradle`—选择对应`SDK`—`Java`—`Next`。
&emsp;选择gradle项目，自己环境中的SDK（最低版本为JDK1.8），创建JAVA项目。
![new project1.png](https://upload-images.jianshu.io/upload_images/17834333-7d375ba3426fbfb4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***

**2.**输入`GroupId`—输入`artifictId`—`Next`。
&emsp;输入groupId，命名请遵循相应命名规范。公司-com;组织-org；个人-pers。
![new project1.png](https://upload-images.jianshu.io/upload_images/17834333-3f61888a0902fe79.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***

**3.**选择`Use auto-import`—选择`Use local gradle distribution`—`Next`。
&emsp;选择自动导入，使用本地gradle环境，选择后会自动出现本地配置的`GRADLE_HOME`路径,然后选择本地的`jdk`配置即可。
![new project3.png](https://upload-images.jianshu.io/upload_images/17834333-95118c513bfc5794.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***

**4.**输入`Project name`—选择`Project location`—`Finish`。
&emsp;输入项目名称，选择相对应的项目存储路径，点击Finish，一个gradle java项目创建完成。
![new project4.png](https://upload-images.jianshu.io/upload_images/17834333-6dab94edb331768c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***

**5.**看到项目中出现文件`build.gradle`并且`Build:Sync`无异常，说明gradle java项目已创建成功。
![gradle java.png](https://upload-images.jianshu.io/upload_images/17834333-da8cd97c94a58619.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***

**6.**点击`file`—`settings`—`Build,Excution,deployment`—`Build Tools`—`Gradle`—选择`Offline`。
&emsp;将gradle设置为离线工作模式，并且可查看到 `service directory path`为`GRADLE_USER_HOME`路径，该路径为gradle jar包存放地址。
![setting.png](https://upload-images.jianshu.io/upload_images/17834333-1e7afa2d1431f40d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***

#2.Gradle build.gradle配置文件介绍
```
//创建项目输入groupId
group 'pers.gradle'
//创建项目所输入的版本
version '1.0-SNAPSHOT'
//指定运行环境，java
apply plugin: 'java'
//jdk的版本，1.8
sourceCompatibility = 1.8
/**
 * 指定所使用仓库的路径
 * mavenCentral()，中央仓库，项目中所使用的jar包都会从中央仓库下载到本地仓库。
 *                 若本地仓库已经拥有相应的jar包，则不会重复下载
 *                 可以使用命令maven { url "http://maven.aliyun.com/nexus/content/groups/public" }指定maven的远程仓库地址
 * mavenLocal()本地仓库，与GRADLE_USER_HOME配置的路径一致。
 * */
repositories {
    mavenLocal()
    maven { url "http://maven.aliyun.com/nexus/content/groups/public" }
    mavenCentral()
}
/**
 * gradle所有的jar包文件坐标都在dependencies属性内放置
 * 每一个jar包都具备以下特点
 * 1.scope（作用域）：gradle支持compile、runtime、testCompile、testRuntime四种scope
 *   compile：jar包在编译期与运行期依赖。
 *   runtime：jar包在运行期依赖。
 *   testCompile：jar包在测试编译期与运行期依赖。
 *   testRuntime：jar包在测试运行期依赖。
 *   补充：
 *   providedCompile：jar包/依赖代码 仅在编译的时候需要，但是在运行时不需要依赖。
 *   providedCompile与compile,runtime区别：
 *   compile: 前提：apply plugin: 'war'或者apply plugin: 'java'
 *   providedCompile:前提：apply plugin: 'war'，若前提为'java',则使用compileOnly
 *   runtime:前提：apply plugin: 'war'
 *   以上所说的前提，如果不正确配置的话，就会遇到依赖包无法导入，以及runtime以及providedCompile无法使用的情况。
 * 2. group:与maven的groupId一致。
 *    name:与maven的artifactId一致。
 *    version:与maven的version一致。
 */
dependencies {
    //gradle jar包坐标可以访问，maven中央仓库[https://mvnrepository.com]，找到相对应的jar包，选择[gradle]选项卡，复制该地址即可。
    testCompile group: 'junit', name: 'junit', version: '4.12'
    //引入spring依赖
    compile group: 'org.springframework', name: 'spring-context', version: '5.1.5.RELEASE'
    //引入lombok依赖
    //@Data注解在类上，会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
    compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.2'

}
```
***

#3.Gradle settings.gradle配置文件介绍
```
//根项目名称
rootProject.name = 'gradle-example'

```
***

#4.Gradle 项目结构
![project structure.png](https://upload-images.jianshu.io/upload_images/17834333-7440dea26b941ed4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***

#5.Gradle Java Spring 项目
**1.**在`model`文件夹下编写实体类`User.java`。
```
package pers.gradle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户信息实体
 * @Data 添加getter、setter方法
 * @NoArgsConstructor 添加无参构造器
 * @AllArgsConstructor 添加全参构造器
 * @AllArgsConstructor 添加链式调用
 * @ToString 添加toString
 * @author Leo
 * @date 11:53 2019/5/17
 * @param
 * @return
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class User {
    private Long id;
    private String name;
    private Integer age;
}

```
***

**2.**在`service`文件夹下编写接口类`UserService.java`。
```
package pers.gradle.service;

import pers.gradle.model.User;

import java.util.List;
/**
 * 用户信息service
 * @author Leo
 * @date 10:37 2019/5/22
**/
public interface UserService {
    /**
     * getUserList() 获取User列表
     * @author Leo
     * @date 10:27 2019/5/22
     * @return java.util.List<pers.gradle.model.User>
    **/
    List<User> getUserList();
}

```
***

**3.**在`service`文件夹下编写接口实现类`UserServiceImpl.java`。
```
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
```
***

**4.**在`dao`文件夹下编写接口类`UserDao.java`。
```
package pers.gradle.dao;

import org.springframework.stereotype.Repository;
import pers.gradle.model.User;

import java.util.List;

/**
 * 用户信息dao
 * @author Leo
 * @date 13:25 2019/5/22
**/
@Repository
public interface UserDao {
    /**
     * getUserList() 获取用户信息列表
     * @author Leo
     * @date  2019/5/22
     * @return java.util.List<pers.gradle.model.User>
     **/
    List<User> getUserList();
}

```
***

**5.**在`dao`文件夹下编写接口实现类`UserDaoImpl.java`。
```
package pers.gradle.dao;

import pers.gradle.model.User;

import java.util.ArrayList;
import java.util.List;
/**
 * 用户信息dao实现类，模拟数据库返回结果
 * @author Leo
 * @date 13:23 2019/5/22
 * @param
 * @return
**/
public class UserDaoImpl implements UserDao{


    @Override
    public List<User> getUserList() {
        List<User> userList=new ArrayList<>();
        //链式调用
        User userZs=new User().setId(1L).setName("张三").setAge(18);
        userList.add(userZs);
        //链式调用
        User userLs=new User().setId(2L).setName("李四").setAge(20);
        userList.add(userLs);

        return userList;
    }
}
```
***

**6.**在`resources`文件夹下编写配置文件`beans.xml`。
```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <!--userService-->
    <bean id="userService" class="pers.gradle.service.UserServiceImpl"/>
    <!--userDao-->
    <bean id="userDao" class="pers.gradle.dao.UserDaoImpl"/>

</beans>
```
***

**7.**在`test.java.pers.gradle`文件夹下编写测试类`UserTest.java`。
```
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

```
***

**8.**获取输出结果。
```
用户张三：User(id=1, name=张三, age=18)
用户李四：User(id=2, name=李四, age=20)
```
***
#6.Github地址
&emsp;[gradle-springboot-web←戳这里](https://github.com/LeoIssac/gradle-java-spring.git). 

