package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import model.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		// 1:获取核心配置文件的数据
		InputStream in = Resources.getResourceAsStream("mybatis.xml");
		// 2:创建会话工厂(会话就是连接)
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 3:获取会话对象(相当于jdbc当中的connection)
		SqlSession session = factory.openSession();
		String name="王";
		List<User> list=session.selectList("model.User.findUserByName", name);
		for(User user:list){
			System.out.println(user);
		}
		//
		session.close();

	}

}
