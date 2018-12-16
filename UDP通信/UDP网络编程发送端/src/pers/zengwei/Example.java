package pers.zengwei;
import java.net.*;
/**
 * @author zengwei
 * @date 2018/9/27 0027-下午 2:13
 */
public class Example {
    public static void main(String[] Q_Q)throws Exception{
        int num=1;
        //创建一个DatagramSocket
        DatagramSocket ds=new DatagramSocket(3211);
        while (num<100){
            Thread.sleep(2000);
            String str=num+"  hello world";
            //创建要发送的数据包，包括发送的数据，数据的长度，接收端的IP地址以及端口号
            DatagramPacket dp=new DatagramPacket(str.getBytes(),str.length(),
                    InetAddress.getByName("localhost"),9888);
            System.out.println("发送信息");
            ds.send(dp); //发送数据
            num++;

        }
        ds.close();
    }
}
