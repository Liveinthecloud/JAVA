package 其他IO流;

import java.io.*;

/**
 * 永久保存这些对象，则可以将对象转为字节数据写入到硬盘上，这个过程称为对象序列化。
 */
public class Object_Stream {
public static void main(String[] arge) throws IOException,ClassNotFoundException {
    //创建对象
    Person p1=new Person("张三","123456",20);
    Person p2=new Person("王二","123466",21);
    //创建文件输出流对象
    ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("Resources\\TXT\\ObjectStream.txt"));
    oos.writeObject(p1);
    oos.writeObject(p2);
    //读取对象
    ObjectInputStream ois=new ObjectInputStream(new FileInputStream("Resources\\TXT\\ObjectStream.txt"));
    Person p3=(Person) ois.readObject();
    System.out.println("Name: "+p3.getName());
    System.out.println("ID: "+p3.getId());
    System.out.println("Age: "+p3.getAge());

    Person p4=(Person) ois.readObject();
    System.out.println("Name: "+p4.getName());
    System.out.println("ID: "+p4.getId());
    System.out.println("Age: "+p4.getAge());
    ois.close();


}


}

//被保存的类必须保证该对象实现Serializable接口，不然会出现异常
class  Person implements Serializable{
    private String name="空";
    private String id="空";
    private  int age=0;
    //构造方法
    public Person(){}
    public Person(String name,String id,int  age){
        super();
        this.name=name;
        this.age=age;
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public int getAge(){
        return age;
    }
    public String toString(){
        return name+"\n"+id+"\n"+age;
    }
}
