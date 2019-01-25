package Test_demo;
/**
 * 插入表
 */

import Model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class demo_3 {
    public static void main(String[] arge) throws IOException {
        //获取配置文件数据
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        //获取会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //获取会话对象
        SqlSession sqlSession = factory.openSession();
        User user=new User();
        user.setUname("大象");
        user.setAge(20);
        sqlSession.insert("Model.User.addUser",user);
        //提交事务
        sqlSession.commit();
        System.err.println(user);
        sqlSession.close();
    }
}
