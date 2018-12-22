package 字符流;


import java.io.*;

public class TestReaderWriter {
    public static void main(String[] arge) throws IOException {
        //开启输入流
        FileReader reader=new FileReader("Resources\\TXT\\test1.txt");
        //开启输出流
        FileWriter writer=new FileWriter("Resources\\TXT\\test(输出).txt");
        int ch;
        while ((ch=reader.read())!=-1){
            System.out.print((char)ch);
            writer.write(ch);
        }
        writer.close();
        reader.close();

        //缓冲类
        BufferedReader bfr=new BufferedReader(new FileReader("Resources\\TXT\\test1.txt"));
        BufferedWriter bfw=new BufferedWriter(new FileWriter("Resources\\TXT\\testbuffer(输出).txt"));
        //跟踪行号
        LineNumberReader lnb=new LineNumberReader(bfr);
        lnb.setLineNumber(0);
        String str;
        while ((str=lnb.readLine())!=null){
            bfw.write(lnb.getLineNumber()+": "+str);
            //不会读取换行符,加个换行
            bfw.newLine();
        }

        bfr.close();
        /**
         * 由于包装流内部使用了缓冲区,在循环中调用BufferedWriter的 write()方法写字符时,这些字符首先会被写入缓冲区,
         * 当缓冲区写满时或调用close()方法时,缓冲区中的字符才会被写入目标文件。
         * 因此在循环结束时一定要调用close()方法,否则极有可能会导致部分存在缓冲区中的数据无法被写入目标文件中。
         */
        bfw.close();

    }
}
