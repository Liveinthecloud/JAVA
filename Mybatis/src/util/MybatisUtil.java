package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory factory;
    static {
        //  获取配置文件
        InputStream in;
        try{
            in = Resources.getResourceAsStream("mybatis.xml");
            //创建会话工厂
            factory=new SqlSessionFactoryBuilder().build(in);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getFactory(){
        return factory;
    }

}
