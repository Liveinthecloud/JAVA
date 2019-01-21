package Demo_2.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_2 {
    public static void main(String[] arge) throws IOException {
        //监听端口号
        int port=4563;
        //注册端口
        ServerSocket ss=new ServerSocket(port);
        Socket sc=ss.accept();

        InputStream in=sc.getInputStream();
        byte[] data= new byte[1024];
        int num=in.read(data);
        System.out.println("接收客服端的消息"+new String(data,0,num));

        //给客服端回话
        OutputStream out=sc.getOutputStream();
        out.write("我接收到了".getBytes());


    }
}
