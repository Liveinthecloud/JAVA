package 多线程问题;
/**
 * 1+...+100十个线程
 */
public class TestThread extends Thread{
    static int SUM=0;//10个线程的和
    String str;
    int begin=0;
    int sum=0;
    public TestThread(String str,int i){
        this.str=str;
        this.begin=i;
    }
    public void run(){
        for(int i=0;i<10;i++){
            sum+=begin;
            begin++;
        }
        System.out.println(str+"： "+sum);
        SUM+=sum;

    }

    public static void main(String[]arge){
        for(int i=0;i<10;i++){
            new TestThread("线程"+i,i*10+1).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1+...+100= "+TestThread.SUM);
    }
}
