import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Statistics {
    public static void main(String []arge){
        Random rand=new Random();
        Map<Integer,Integer> map=new
                HashMap<Integer, Integer>();
        for(int i=0;i<10000;i++){
            int num=rand.nextInt(20);//生成随机数
            Integer freq=map.get(num);      //num自动装包
            map.put(num,freq==null?1:freq+1);
        }
        //在IDEA中对输出的集合进行了排序输出
        //但是HashMap是无序的
        System.out.println(map);
    }

}
