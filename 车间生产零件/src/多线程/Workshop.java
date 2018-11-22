package 多线程;

public class Workshop extends Thread {
    //构造器
    public Workshop(){}
    public Workshop(Runnable runnable,String thread_name){
        super(runnable,thread_name);
    }
    public static void main(String[]arge) throws InterruptedException {
        Part part=new Part();
        new Workshop(part,"工厂一").start();
        new Workshop(part,"工厂二").start();
        new Workshop(part,"工厂三").start();
        new Workshop(part,"工厂四").start();
        /**
         * 休眠main线程
         * 等待工厂生产完毕在输出值
         */
        Thread.sleep(2000);
       part.show();
    }
}
