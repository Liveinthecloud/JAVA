package 字节流;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileputStream {
    public static void main(String[] arge) throws IOException {
        //开启输入字节流
        FileInputStream fin=new FileInputStream("Resources\\Image\\InputStream.png");
        //开启输出字节流
        FileOutputStream fout=new FileOutputStream("Resources\\Image\\复制InputStream.png");
        int data;
        while ((data=fin.read())!=-1){
            System.out.print(data);     //输出到显示
            fout.write(data);           //写入到复制文件中
        }
        fin.close();
        fout.close();
    }
}
