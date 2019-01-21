package Demo_1.Server;

import org.omg.CORBA.portable.OutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] arge) throws IOException {
        ServerSocket ss;
        Socket sc;
        InputStream inputStream;

        //启动一个socket服务端，注册端口
        ss = new ServerSocket(10000);
        System.out.println("服务端启动...\n监听端口：8655");
        //监听该端口
        sc = ss.accept();//等待接收客服端的请求---阻塞方法
        //从连接中接收数据
        inputStream = sc.getInputStream();
        boolean on_off=true;
        while (on_off) {
            byte [] data =new byte[1024];
            int num;
            //从输入流中提取数据
            while ((num=inputStream.read(data))!=-1){
                System.out.println(new String(data,0,num));
            }
            on_off=false;
        }
        /**
         * 发送消息
         */
        OutputStream outputStream= (OutputStream) sc.getOutputStream();
        outputStream.write("你好".getBytes());

        //关闭流
        sc.close();
        inputStream.close();
        outputStream.close();
    }
}
