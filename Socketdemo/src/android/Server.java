package android;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import testRPC.Product;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] arge) throws IOException {
        ServerSocket ss;
        Socket sc;
        InputStream in;
        boolean flag=true;

        //启动一个socket服务端，注册端口
        ss = new ServerSocket(10000);
        //接收套字节
        sc=ss.accept();
        //获取输入流
        in = sc.getInputStream();
        //缓冲区
        byte[] data = new byte[1024];
        int num;
        while (flag){
           num= in.read(data);
           System.out.println(new String(data,0,num));
        }
        in.close();
        sc.close();
        ss.close();
    }
}
