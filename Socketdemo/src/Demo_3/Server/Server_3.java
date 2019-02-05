package Demo_3.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_3 {
    public static void main(String[] arge) throws IOException {
        //监听端口号
        int port = 4563;
        //注册端口
        ServerSocket ss = new ServerSocket(port);

        while (true) {
            Socket sc = ss.accept();
            System.out.println("获取连接成功：");
            InputStream in = sc.getInputStream();//获取输入流
            OutputStream out = sc.getOutputStream();//获取输出流
            //接收消息
            byte[] data = new byte[1024];
            int num=in.read(data);
            System.out.println(new String(data,0,num));
            //发送消息
            out.write("\n我接收到了\n".getBytes());
            //关流
            sc.close();
            in.close();
            out.close();
        }


    }
}
