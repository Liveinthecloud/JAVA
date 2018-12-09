package 多线程;

public class Part implements Runnable {
    //共享资源
    int part=10000;
    //各个工厂的计数器
    int sun1=0;
    int sun2=0;
    int sun3=0;
    int sun4=0;


    /**线程锁
     * 在run（）在并发情况下存在的问题是：
     * 假如part=1，进入while循环，但是part还没减为0，其它进程也进入了while循环
     * 用lock控制退出
     */
    public synchronized boolean lock(){
           part--;
           if(part==-1||part==-2||part==-3)
               return true;
           return false;
    }

    public void run(){


            while(part>0){
                if(Thread.currentThread().getName().equals("工厂一")){
                    if(lock())
                        break;
                    sun1++;
                }
                if(Thread.currentThread().getName().equals("工厂二")){
                    if(lock())
                        break;
                    sun3++;
                }
                if(Thread.currentThread().getName().equals("工厂三")){
                    if(lock())
                        break;
                    sun4++;
                }
                if(Thread.currentThread().getName().equals("工厂四")){
                    if(lock())
                        break;
                    sun2++;
                }
            }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void show(){
        System.out.println("工厂一："+sun1+"\n工厂二"+sun2+"\n工厂三："+sun3+"\n工厂四"+sun4);
        System.out.println("检查："+sun1+"+"+sun2+"+"+sun3+"+"+sun4+"="+(sun1+sun2+sun3+sun4));
    }

}
