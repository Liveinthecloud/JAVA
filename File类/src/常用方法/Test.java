package 常用方法;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] arge) throws IOException {
        File file0=new File("assets\\file0.txt");
        if(!file0.exists()){
            file0.createNewFile();
            System.out.println(file0.getName()+"建立成功:"+file0.getParent());
        }
        File file1=new File("D:\\Java\\File类","assets\\file1.txt");
        if(!file1.exists()){
            file1.createNewFile();
            System.out.println(file1.getName()+"建立成功:"+file1.getParent());
        }

        File file2=new File(file1.getParent(),"file2.txt");
        if(!file2.exists()){
            file2.createNewFile();
            System.out.println(file2.getName()+"建立成功:"+file2.getParent());
        }
        file0.delete();
        file1.delete();
        file2.delete();

    }
}
