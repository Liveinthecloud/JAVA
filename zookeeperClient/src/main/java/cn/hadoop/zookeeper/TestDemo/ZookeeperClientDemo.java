package cn.hadoop.zookeeper.TestDemo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ZookeeperClientDemo {
    ZooKeeper zooKeeper;
    /*构造一个zookeeper的客户端*/
    @Before
    public void  init() throws IOException {
        zooKeeper = ZookeeperUtil.getZookeeper();
    }
    /*创建*/
    @Test
    public void Create() throws IOException, KeeperException, InterruptedException {
        String create = zooKeeper.create("/idea/test1", "hello wrold".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(create);
        zooKeeper.close();

    }
    @Test
    /*修改*/
    public void Updata() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        Stat stat = zooKeeper.setData("/idea", "你好啊".getBytes("UTF-8"), -1);
        zooKeeper.close();
    }

    /*查询*/
    @Test
    public void Getdata() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        byte[] data = zooKeeper.getData("/idea/test", true, null);
        System.out.println(new String(data,"UTF-8"));
        zooKeeper.close();
    }
    /*获取所有子节点*/
    @Test
    public void ListChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/idea", false);
        for (String data: children
             ) {
            System.out.println(data);
        }
        zooKeeper.close();
    }
    /*删除节点*/
    @Test
    public void Rmovedata() throws KeeperException, InterruptedException {
        zooKeeper.delete("/idea/test",-1);
        zooKeeper.close();


    }
}
