
package Externalizable;

import java.io.*;

public class Test_2 {
    public static void main(String[] arge) throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.set("王二", 24);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile_1.xml"));
        oos.writeObject(person);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tempFile_1.xml"));
        Person o = (Person)ois.readObject();
        System.out.println(o);
        ois.close();
    }
}
