package 转换流;



import java.io.*;

public class Test1 {
    public static void main(String[]arge) throws IOException {
        //字节流转换成字符流
        InputStreamReader inr=new InputStreamReader(new FileInputStream("Resources\\TXT\\test1.txt"));
        BufferedReader bufr=new BufferedReader(inr);        //包装类
        OutputStreamWriter outw=new OutputStreamWriter(new FileOutputStream("Resources\\TXT\\(转换流)test1.txt"));
        BufferedWriter bufw=new BufferedWriter(outw);

        String str;
        while ((str=bufr.readLine())!=null){
            bufw.write(str+"\r\n");

        }
        //用了缓冲包装类一定要关闭流，不然会导致数据丢失。
        bufr.close();
        bufw.close();
    }
}
