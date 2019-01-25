package Test_demo;
/**
 * 查询表 通过名字
 */

import Model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class demo_2 {
    public static void main(String[] arge) throws IOException {
        //获取配置文件数据
        InputStream in= Resources.getResourceAsStream("mybatis.xml");
        //获取会话工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        //获取会话对象
        SqlSession sqlSession=factory.openSession();
        String name="王";
        List<User> user = sqlSession.selectList("Model.User.findUserByName", name);
        for (User u:user
             ) {
            System.err.println(u);
        }
        sqlSession.close();
    }
}
