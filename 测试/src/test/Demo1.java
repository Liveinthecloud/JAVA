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
		//1:��ȡ���������ļ�������
		InputStream in=Resources.getResourceAsStream("mybatis.xml");
		//2:�����Ự����(�Ự��������)
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		//3:��ȡ�Ự����(�൱��jdbc���е�connection)
		SqlSession session=factory.openSession();
		//4:�������ݿ�
		int id=2;
		User user=(User)session.selectOne("model.User.findUserById", id);
		System.out.println(user);
		session.close();

	}

}
