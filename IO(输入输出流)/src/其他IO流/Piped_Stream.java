package 其他IO流;



import java.io.*;

/**
 * 管道流用于多线程之间的数据传输，
 * 它是一种比较特殊的流，必须先建立联系才能进行通信
 */

public class Piped_Stream {
    public static void main(String[] arge) throws Exception{
        final PipedInputStream pis=new PipedInputStream();
        final PipedOutputStream pos=new PipedOutputStream();
        //建立联系
        pis.connect(pos);//or pos.connect(pis)
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                //将从键盘读取的数据写入管道流,进行包装
                PrintStream ps=new PrintStream(pos);
                while (true){
                    System.out.print(Thread.currentThread().getName()+"要求输入的内容:");
                    try {
                        ps.println(br.readLine());
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"发送线程").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br=new BufferedReader(new InputStreamReader(pis));
                while (true){
                    try {
                        System.out.println(Thread.currentThread().getName()+"接收内容："+br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "接收线程").start();
    }
}
