package Test_demo;

import Model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class demo_4 {
    public static void main(String [] arge) throws IOException {
        //获取配置文件数据
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        //获取会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //获取会话对象
        SqlSession sqlSession = factory.openSession();
        User user = new User();
        user.setUname("老虎");
        user.setAge(30);
        user.setId(6);
        sqlSession.update("Model.User.updateUser",user);
        //删除 通过 id
        int id=2;
        sqlSession.delete("Model.User.deleteUser",id);
        sqlSession.commit();
        sqlSession.close();
    }
}
