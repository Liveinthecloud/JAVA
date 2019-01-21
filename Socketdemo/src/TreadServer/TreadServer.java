package TreadServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TreadServer {
    public static void main(String[] arge) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        System.out.println("开启成功：");
        while (true) {
            Socket sc = ss.accept();
            new Thread(new Talk(sc)).start();
        }
    }
}
