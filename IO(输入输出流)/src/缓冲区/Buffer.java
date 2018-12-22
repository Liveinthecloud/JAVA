package 缓冲区;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 建立适当的缓冲区可以提高数据传输的效率
 */
public class Buffer {
    public static void main(String[] arge){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //开启输入流
                    FileInputStream in=new FileInputStream("Resources\\Music\\逆鳞狂想曲.mp3");
                    //开启输出流
                    FileOutputStream out=new FileOutputStream("Resources\\Music\\逆鳞狂想曲(复制).mp3");

                    //------------------不用缓冲区-----------------
                    int data;
                    long beginTime=System.currentTimeMillis();
                    while ((data=in.read())!=-1){
                        out.write(data);
                    }
                    long endTime=System.currentTimeMillis();
                    System.out.println("总用时："+(endTime-beginTime)+"毫秒");
                    in.close();
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
        //--------------------------------------------

        //------------------建立缓冲a区----------------
        //开启输入流
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //开启输入流
                    FileInputStream inbf=new FileInputStream("Resources\\Music\\逆鳞狂想曲.mp3");
                    //开启输出流
                    FileOutputStream outbf=new FileOutputStream("Resources\\Music\\逆鳞狂想曲(Buffer复制).mp3");
                    byte[] buffer=new byte[1024];
                    int b;
                    long beginTime=System.currentTimeMillis();
                    while ((b=inbf.read(buffer))!=-1){
                        outbf.write(buffer);
                        //System.out.println(buffer);
                    }
                    long endTime=System.currentTimeMillis();
                    System.out.println("启用缓冲区总用时："+(endTime-beginTime)+"毫秒");
                    inbf.close();
                    outbf.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
