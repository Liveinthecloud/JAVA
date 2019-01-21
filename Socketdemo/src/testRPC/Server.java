package testRPC;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] arge) throws IOException {
        ServerSocket ss;
        Socket sc;
        OutputStream out;
        ObjectOutputStream oos;
        //启动一个socket服务端，注册端口
        ss = new ServerSocket(10000);
        sc=ss.accept();
        out = sc.getOutputStream();
        oos = new ObjectOutputStream(out);
        oos.writeObject(new Product(1,"香蕉",78.3f));
        ss.close();
        sc.close();
        oos.close();
    }
}
