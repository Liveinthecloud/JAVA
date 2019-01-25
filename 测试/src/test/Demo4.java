package test;

import java.io.InputStream;

import model.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo4 {

	public static void main(String[] args) throws Exception {
		// 1:获取核心配置文件的数据
		InputStream in = Resources.getResourceAsStream("mybatis.xml");
		// 2:创建会话工厂(会话就是连接)
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 3:获取会话对象(相当于jdbc当中的connection)
		SqlSession session = factory.openSession();
		//修改数据
		User user=new User();
		user.setId(96);
		user.setUname("段誉");
		user.setAge(30);
		session.update("model.User.updateUser", user);
		int id=97;
		session.delete("model.User.deleteUser", id);
		session.commit();
		session.close();

	}

}
