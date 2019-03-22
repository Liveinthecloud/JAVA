package cn.hadoop.zookeeper.TestDemo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZookeeperWatchDemo {
    ZooKeeper zooKeeper;
    @Before
    public void  init() throws IOException {
        zooKeeper = ZookeeperUtil.getZookeeper();
        zooKeeper.register(new MyWatcher());    //添加监听响应
    }
    @Test
    public void GetWatch() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/idea", true, null); //监听节点
        System.out.println(new String(data));
        Thread.sleep(Long.MAX_VALUE);
    }
    @Test
    public void GetChildrenWatch() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/idea", true);
        for (String child :children
                ) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

}
