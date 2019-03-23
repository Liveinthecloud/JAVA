package cn.hadoop.zookeeper.distributesystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/*模拟服务器业务*/
public class TimeQueryService extends Thread{
    private int port;
    public TimeQueryService(int port){
        this.port=port;
    }
    @Override
    public void run() {
        try {
            System.out.println("业务线程已绑定端口"+port+"准备接受消费端请求了...");
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket accept = serverSocket.accept();
                InputStream in = accept.getInputStream();
                OutputStream out = accept.getOutputStream();
                out.write(new Date().toString().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
