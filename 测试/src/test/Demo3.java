package test;


import java.io.InputStream;

import model.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo3{

    public static void main(String[] args) throws Exception {
        // 1:获取核心配置文件的数据
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        // 2:创建会话工厂(会话就是连接)
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3:获取会话对象(相当于jdbc当中的connection)
        SqlSession session = factory.openSession();
        //注意:mybatis框架默认非自动提交事务，如果进行增删改的操作时候，需要手动提交事务
        //插入数据
        User user=new User();
        user.setUname("王王1");
        user.setAge(20);
        session.insert("model.User.addUser", user);
        System.out.println(user);
        //提交事务
        session.commit();
        session.close();

    }

}
