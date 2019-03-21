package cn.hadoop.zookeeper;

import org.apache.zookeeper.ZooKeeper;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*饿汉式 双重检测  线程安全*/
public class ZookeeperUtil {
    private static volatile ZooKeeper zooKeeper;
    private static Properties properties;
    static{
        properties = new Properties();
        try {
            FileInputStream in = new FileInputStream("Zookeeper.properties");
            properties.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ZookeeperUtil (){}

    public static ZooKeeper getZookeeper() throws IOException {
         if(zooKeeper==null){
             synchronized (ZookeeperUtil.class){
                 if(zooKeeper==null){
                     zooKeeper = new ZooKeeper(properties.getProperty("connect"),Integer.parseInt(properties.getProperty("sessionTimeout")), null);
                 }
             }
         }
        return zooKeeper;
    }
}
