package Arrays_asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Arrays.asList()方法
 * 1.接受一个数组或是一个用逗号分开的元素列表（使用可变参数）
 * 2.并将其转换为一个List对象
 */
public class AddingGroups {
    public static void main(String[]args){
        Collection<Integer> collection=new
                ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));//第一种添加形式

        Integer [] moreInts={7,8,9,10,11,12};
        collection.addAll(Arrays.asList(moreInts));//第二种添加形式

        for(Integer temp:collection){
            System.out.print(temp+" ");
        }
        System.out.println();
        //Arrays.asList()的限制
        List<Integer> list=Arrays.asList(moreInts);
        list.set(1,99);
       // list.add(15);（没添加上） 报错提示 ：因为其底层表示的是数组，因此不能调整尺寸大小
        for(Integer temp:list){
            System.out.print(temp+" ");
        }
    }
}
