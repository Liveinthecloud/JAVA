package 其他IO流;

import java.io.*;

/**
 * 有时候并不需要存储整个对象，只需要存储成员数据的时候可以用
 */
public class Data_Stream {
    public static void main(String[] arge) throws IOException {
        //添加包装类
        DataOutputStream dos=new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Resources\\TXT\\DataStream.txt")
                )
        );
        dos.writeByte(12);  //写入字节
        dos.writeBoolean(true); //写一个布尔值
        dos.writeUTF("你好");
        dos.writeChar('1');
        dos.close();
        DataInputStream dis=new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("Resources\\TXT\\DataStream.txt")
                )
        );
        //读取顺序要与写入循序一致
        System.out.println(dis.readByte());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readUTF());
        System.out.println(dis.readChar());
        dis.close();





    }
}
