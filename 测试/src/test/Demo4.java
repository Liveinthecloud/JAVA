package test;

import java.io.InputStream;

import model.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo4 {

	public static void main(String[] args) throws Exception {
		// 1:��ȡ���������ļ�������
		InputStream in = Resources.getResourceAsStream("mybatis.xml");
		// 2:�����Ự����(�Ự��������)
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		// 3:��ȡ�Ự����(�൱��jdbc���е�connection)
		SqlSession session = factory.openSession();
		//�޸�����
		User user=new User();
		user.setId(96);
		user.setUname("����");
		user.setAge(30);
		session.update("model.User.updateUser", user);
		int id=97;
		session.delete("model.User.deleteUser", id);
		session.commit();
		session.close();

	}

}
