package DataFind;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 实现快速查找
 */

public class ReadData {

    public static void main(String[] arge) throws IOException {
        prodcut p=ReadData.findprodcut(0);
        System.out.println(p.toString());
    }
    public static prodcut findprodcut(int i) throws IOException {
        //

        //可以从文件任何位子读取数据
        RandomAccessFile r = new RandomAccessFile("datafile.dat", "r");
        long pos=i*28;
        //跳到指定位置
        r.seek(pos);


        //读取数据
        int id=r.readInt();//返回4个字节
        byte[] bytes = new byte[20];
        int read = r.read(bytes);
        new String(bytes);
        String trim = new String(bytes).trim();//返回name

        float v = r.readFloat();//价格
        return new prodcut(id,trim,v);
    }
}
