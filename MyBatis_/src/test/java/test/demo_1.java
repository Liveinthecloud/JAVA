package test;


import Model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.out;

public class demo_1 {
    public static void main(String [] arge) throws IOException {
        //获取配置文件数据
        InputStream in= Resources.getResourceAsStream("mybatis_config.xml");
        //获取会话工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        //获取会话对象
        SqlSession sqlSession=factory.openSession();
        int id=1;
        User user=(User) sqlSession.selectOne("Model.User.findUserById",id);
        System.out.println(user);


    }
}
