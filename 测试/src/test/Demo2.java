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
		// 1:��ȡ���������ļ�������
		InputStream in = Resources.getResourceAsStream("mybatis.xml");
		// 2:�����Ự����(�Ự��������)
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 3:��ȡ�Ự����(�൱��jdbc���е�connection)
		SqlSession session = factory.openSession();
		String name="��";
		List<User> list=session.selectList("model.User.findUserByName", name);
		for(User user:list){
			System.out.println(user);
		}
		//
		session.close();

	}

}
