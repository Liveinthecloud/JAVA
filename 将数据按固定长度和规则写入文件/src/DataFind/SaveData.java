package DataFind;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveData {
    public static void main(String[] arge) throws IOException {
        prodcut p0 = new prodcut(0, "苹果", 11.5f);
        prodcut p2 = new prodcut(1, "水晶葡萄", 12.5f);
        prodcut p3 = new prodcut(2, "梨", 19.5f);
        prodcut p4 = new prodcut(3, "火龙果", 20.5f);
        SaveData.save(p0);
        SaveData.save(p2);
        SaveData.save(p3);
        SaveData.save(p4);


    }
    public static void save(prodcut p) throws IOException {
        DataOutputStream dout = new DataOutputStream(new FileOutputStream("datafile.dat",true));
        dout.writeInt(p.getId());
        byte[] bytes = p.getName().getBytes("UTF-8");
        byte[] data = new byte[20];
        System.arraycopy(bytes,0,data,0,bytes.length);
        dout.write(data);
        dout.writeFloat( p.getPrice());
        dout.close();
    }
}
