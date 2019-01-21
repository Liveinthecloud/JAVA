package TreadServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Talk implements Runnable{
    Socket socket;

    public Talk(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取输入输出流
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            //第一个问题
            byte[] data = new byte[1024];
            int num=in.read(data);
            System.out.println(new String(data,0,num));
            out.write("我接收到了你的第一个信息".getBytes());

            //第二个问题
            num=in.read(data);
            System.out.println(new String(data,0,num));
            out.write("我接收到了你的第二个信息".getBytes());


            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
