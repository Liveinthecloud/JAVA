package cn.per.mybatis.test;

import cn.per.mybatis.model.User;
import cn.per.mybatis.service.Service_interface.UserService;
import cn.per.mybatis.service.impl.UserServiceImpl;

import java.util.Scanner;

public class UserTest {
    public static void main(String[] arge){
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入户名：");
        String username=scanner.nextLine();
        System.out.println("输入密码：");
        String password=scanner.nextLine();
        User vo = new User();
        vo.setUserName(username);
        vo.setPassword(password);
        UserService userService=new UserServiceImpl();
        User user = userService.findUserByUserNameAndPassword(vo);
        if(user!=null){
            System.out.println("欢迎"+ user.getUserName());
            System.out.println(user);
        }else {
            System.out.println("用户或密码输入错误！");
        }

    }
}
