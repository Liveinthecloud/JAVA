package test;

import java.io.IOException;
import java.io.InputStream;

import model.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		//1:获取核心配置文件的数据
		InputStream in=Resources.getResourceAsStream("mybatis.xml");
		//2:创建会话工厂(会话就是连接)
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		//3:获取会话对象(相当于jdbc当中的connection)
		SqlSession session=factory.openSession();
		//4:操作数据库
		int id=2;
		User user=(User)session.selectOne("model.User.findUserById", id);
		System.out.println(user);
		session.close();

	}

}
