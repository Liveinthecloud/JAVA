package Demo_1.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] arge) throws IOException, InterruptedException {
        //向服务端发生建立连接的请求
        Socket sc = new Socket("192.168.1.105", 8655);
        //从连接中拿到发送颇高的工具
        OutputStream outputStream=sc.getOutputStream();
        //发送数据
        int c=10;
        while (c-->0){
            outputStream.write("你好".getBytes());
        }


        /**
         * 接收数据
         */
        Thread.sleep(10000);
        InputStream inputStream=sc.getInputStream();
        byte []data=new byte[1024];
        inputStream.read(data);
        System.out.println(new String(data));
        sc.close();
        outputStream.close();
        inputStream.close();
    }
}