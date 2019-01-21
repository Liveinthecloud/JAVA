package TreadServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] arge) throws IOException {
      while (true){
          Client.openClient();
          int i=10;
          if(i--<0){
              break;
          }
      }
}
    public static void openClient() throws IOException {
        //连接端口
        int port=10000;
        String IP="127.0.0.1";
        Socket socket = new Socket(IP, port);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        //发送问题
        outputStream.write("我是客户端问题一  ".getBytes());
        System.out.println();
        //接收
        byte[] data = new byte[1024];
        int num=inputStream.read(data);
        System.out.println(new String(data,0,num));

        outputStream.write("我是客户端问题二  ".getBytes());
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
