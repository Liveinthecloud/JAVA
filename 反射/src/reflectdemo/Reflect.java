package reflectdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Reflect {
    public static void main(String[] arge) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        /**
         * 获取Class三种方法
         */

        // 1.
        Person person=new Person();
        Class class_1=person.getClass();
        //2.
        Class class_2=Person.class;
        //3.
        Class class_3=Class.forName("reflectdemo.Person");
        //获取类型完整名字
        System.out.println(class_1.getName());
        //获取所有属性
        Field[] fields=class_2.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field);
        }
        //获取类所有方法
        Method[] methods=class_3.getDeclaredMethods();
        for(Method method:methods){
            System.out.println(method.getName());
        }
        //获取特定的方法
        Method method = null;
        try {
             method=class_2.getMethod("setName", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //通过类的不带参数的构造方法创建这个类的一个对象
        Object instance=class_1.newInstance();
        //调用方法
        method.invoke(instance,"张三");

        Properties properties=new Properties();
        //找到当前工程下的文件的相对路径
        properties.load(Reflect.class.getClassLoader().getResourceAsStream("string.properties"));
        String type=properties.getProperty("type");
        String func=properties.getProperty("func");
        String paramType=properties.getProperty("paramType");


    }
}
