package 其他IO流;

import java.io.*;

/**
 * 多个流处理数据，将这些流进行合并
 * SequenceInputStream类可以将几个输入流串联起来，合并成一个流
 */
public class Sequence_Stream {
    public static void main(String[] arge) throws Exception {
        //打开两个输入流
        FileInputStream fin0=new FileInputStream("Resources\\TXT\\test1.txt");
        FileInputStream fin1=new FileInputStream("Resources\\TXT\\test.txt");
        //创建一个序列流
        SequenceInputStream sis=new SequenceInputStream(fin1,fin0);
        //创建输出流
        FileOutputStream fout=new FileOutputStream("Resources\\TXT\\(合并流)test.txt");
        byte[] buffer=new byte[1024];
        int len;
        while ((len=sis.read(buffer))!=-1){
            fout.write(buffer);
            fout.write("\r\n".getBytes());
        }
        sis.close();
        fout.close();
        /*
        * 添加多个对象时
        * vector.addElement(Stream1)
        * vector.addElement(Stream2)
        * vector.addElement(Stream3)
        * Enumeration e=vector.elements();
        * SequenceInputStream(e)
         * */
    }
}
