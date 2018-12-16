package pers.zengwei;
import java.net.*;
/**
 * @author zengwei
 * @date 2018/9/26 0026-下午 10:20
 */
//接收程序
public class Example {
    public static void main(String[] Q_Q)throws Exception{
        int num=1;
        //用于接收数据
        byte [] buf=new byte[1024];
        while(true){
            //定义DatagramSocket对象，监听的端号9888
            DatagramSocket ds=new DatagramSocket(9888);
            //定义DatagramPacket对象，用于接收数据
            DatagramPacket dp=new DatagramPacket(buf,1024);
            System.out.println("等待接收数据！");
            ds.receive(dp);            //等待接收数据，如果没有数据则会堵塞
            String str=new String(dp.getData(),0,dp.getLength())+"  from  "+
                    dp.getAddress().getHostName()+":"+dp.getPort();
            System.out.println(num+"  "+str);
            ds.close();
            num++;
        }

    }
}
