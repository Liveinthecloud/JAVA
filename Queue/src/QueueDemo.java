import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 队列先进先出（FIFO）的容器
 * 从容器的一端放入事物，从另一端取出，
 * 并且事物放入容器的顺序与取出顺序相同
 */
public class QueueDemo {
    public static void main(String[] arge){
        Queue<Integer> queue=new  LinkedList<>();
        Random random=new Random(47);
        for(int i=0;i<10;i++){
            int j=random.nextInt(20);
            System.out.print(j+" ");
            queue.offer(j);//进入队列
        }
        System.out.println();
        while (queue.peek()!=null){
            System.out.print(queue.remove()+" ");//出队列
        }
    }
}
