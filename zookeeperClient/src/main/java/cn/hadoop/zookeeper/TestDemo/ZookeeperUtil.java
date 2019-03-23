package cn.hadoop.zookeeper.TestDemo;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*饿汉式 双重检测  线程安全*/
public class ZookeeperUtil {
    private static volatile ZooKeeper zooKeeper;
    private static Properties properties;

    private ZookeeperUtil (){}
    private ZookeeperUtil(Watcher watcher){
        zooKeeper.register(watcher);
    }

    /*static{
        properties = new Properties();
        try {
           // FileInputStream in = new FileInputStream("Zookeeper.properties");
            properties.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static ZooKeeper getZookeeper() throws IOException {
         if(zooKeeper==null){
             synchronized (ZookeeperUtil.class){
                 if(zooKeeper==null){
                     zooKeeper = new ZooKeeper("master:2181,node1:2181,node2:2181",2000, null);
                 }
             }
         }
        return zooKeeper;
    }
}
