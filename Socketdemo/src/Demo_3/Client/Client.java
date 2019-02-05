package Demo_3.Client;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] arge) throws IOException {
        //连接端口
        int port=4563;
        String IP="127.0.0.1";
        Socket socket = new Socket(IP, port);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        //发送问题
        outputStream.write("我是客户端  ".getBytes());
        System.out.println();
        //接收
        byte[] data = new byte[1024];
        int num=inputStream.read(data);
        System.out.println(new String(data,0,num));
        socket.close();
        inputStream.close();
        outputStream.close();


    }
}
