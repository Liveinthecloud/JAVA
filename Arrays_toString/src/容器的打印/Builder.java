package 容器的打印;

import java.util.*;

class Generator {
    int key = 0;
    public String next() {

        switch (key) {

            case 0:
                key++;
                return "Snow White";
            case 1:
                key++;
                return "Bashful";
            case 2:
                key++;
                return "Doc";
            case 3:
                key++;
                return "Dopey";
            case 4:
                key++;
                return "Grumpy";
            case 5:
                key++;
                return "Happy";
            case 6:
                key++;
                return "Sleepy";
            default:
            case 7:
                key++;
                return "Sneezy";
        }
    }

    public void fillA(String[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = next();
        }
    }

    public Collection fill(Collection<String> c, int n) {
        key=0;//key还原从头输入
        for (int i = 0; i < n; i++)
            c.add(next());
        return c;
    }
}


public class Builder {
    public static void main(String[] arge){
        Generator generator=new Generator();
        String [] name=new String[10];
        generator.fillA(name);
        for(String temp:name)
            System.out.print(temp+" ");
        System.out.println();
        System.out.println(generator.fill(new ArrayList<>(),10));//可重复，按添加顺序存储排列，查找效率高，增删效率低
        System.out.println(generator.fill(new LinkedList<>(),10));//可重复，按添加顺序存储排列，查找效率低，增删效率高
        System.out.println(generator.fill(new TreeSet<>(),10));//元素不可重复，按照比较结果升序排列
        System.out.println(generator.fill(new HashSet<>(),10));//元素不可重复，最快的获取元素方法
        System.out.println(generator.fill(new LinkedHashSet<>(),10));//元素不可重复，可以按添加顺序保存，获取元素等于HashSet




    }
}
