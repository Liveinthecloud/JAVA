package 缓冲区;

import java.io.*;

public class TestBufferedputStream {
    public static void main(String[] arge) throws IOException {
        //创建一个带缓冲区的输入流
        BufferedInputStream bin=new BufferedInputStream(new FileInputStream("Resources\\Music\\逆鳞狂想曲.mp3"));
        //创建一个带缓冲区的输出流
        BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream("Resources\\Music\\逆鳞狂想曲(Buffered).mp3"));
        int len;
        long beginTime=System.currentTimeMillis();
        while ((len=bin.read())!=-1){
            bout.write(len);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("启用缓冲区总用时："+(endTime-beginTime)+"毫秒");
        bin.close();
        bout.close();
    }
}
