package hdfs.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.Buffer;

public class HdfsCilent {
   static FileSystem fs=null;
   static{
       try {
           Configuration conf = new Configuration();
           fs = FileSystem.get(new URI("hdfs://master:9000/"), conf, "root");
       }catch (Exception e){
           e.getLocalizedMessage();
       }

   }
    /**
     * 下载文件
     */
    @Test
    public void download()throws Exception {
        fs.copyToLocalFile(new Path("/jdk-8u201-linux-x64.tar.gz"),new Path("I:/"));
        fs.close();
        System.out.println("下载完成");
    }
    /*
    * 读取hdfs中的文件内容
    * */
    @Test
    public void readData() throws Exception{
        FSDataInputStream open = fs.open(new Path("/rating.json"));
        /*byte[] buf = new byte[1024];
        open.read(buf);
        System.out.println(new String(buf));*/
        BufferedReader buf = new BufferedReader(new InputStreamReader(open,"utf-8"));
        String line=null;
        while ((line=buf.readLine())!=null){
            System.out.println(line);
        }
        open.close();
        fs.close();

    }
    /**
     *向hdfs中写数据
     */
    @Test
    public void writeData() throws Exception{
        FSDataOutputStream out = fs.create(new Path("/rating.json"), true);
//        C:\Users\zengwei\Desktop\sun.jpg
        FileInputStream in = new FileInputStream("C:\\Users\\zengwei\\Desktop\\rating.json");
        byte[] buf = new byte[1024];
        int read=0;
        while((read=in.read(buf))!=-1){
            out.write(buf,0,read);//防止最后没有1024字节，导致没有覆盖完前一次的数据 ，而导致文件乱码
        }
        in.close();
        out.close();
        fs.close();
    }
}
