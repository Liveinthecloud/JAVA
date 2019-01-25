package Test_demo;
/*
查询表 通过 id
 */

import Model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class demo_1 {
    public static void main(String [] arge) throws IOException {
        //获取配置文件数据
        InputStream in= Resources.getResourceAsStream("mybatis.xml");
        //获取会话工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        //获取会话对象
        SqlSession sqlSession=factory.openSession();
        int id=5;
        User user=(User) sqlSession.selectOne("Model.User.findUserById",id);
        System.out.println(user);
        sqlSession.close();


    }
}
