package Serializable;

import java.io.*;

public class Test_1 {
    public static void main(String[] arge) throws Exception {
        Person person_1 = new Person("张三", 11);
        Person person_2 = new Person("张三", 11);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile.xml"));
        oos.writeObject(person_1);
        oos.writeObject(person_2);

        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempFile.xml"));
        Person o = (Person)ois.readObject();
        Person o_1 = (Person)ois.readObject();

        System.out.println(o);
        System.out.println(o_1);

        ois.close();
    }
}
