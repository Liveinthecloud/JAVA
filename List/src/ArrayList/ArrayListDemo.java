package ArrayList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {

    public static void main(String[] args) {
        new ArrayList<>();
        new HashSet<>();
        new CopyOnWriteArrayList<>();
        new Thread();
        String str1="4545";
        String str2="4545";
        System.out.println(str1==str2);
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        str1=str1+"sdf";
        System.out.println(str1.hashCode());
        new StringBuffer();

    }
}
class a{
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}