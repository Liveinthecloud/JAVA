package Externalizable;

import java.io.*;

/*1.必须要有无参构造函数
  2.实现两个接口的方法
* */
public class Person implements Externalizable {
    private String name;
    private int year;

    public Person(){}
    public void set(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Serializable.Person{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(year);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name=in.readUTF();
        this.year=in.readInt();

    }
}
