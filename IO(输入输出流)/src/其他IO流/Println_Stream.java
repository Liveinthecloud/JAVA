package 其他IO流;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Println_Stream {
    public static void main(String[] arge) throws FileNotFoundException {
        //将FileOutputStream读取到的数据 输出
        PrintStream ps=new PrintStream(new FileOutputStream("Resources\\TXT\\PrintStream.txt"));
        Person p1=new Person();
        ps.print("这是一个数字");
        ps.println(10);
        ps.println(p1);//重写toString()方法
    }
}
