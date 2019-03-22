package cn.hadoop.zookeeper.distributesystem;

import cn.hadoop.zookeeper.TestDemo.ZookeeperUtil;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class TimeQueryServer {
    ZooKeeper zookeeper = ZookeeperUtil.getZookeeper();

    public TimeQueryServer() throws IOException {
    }

    public static void main(String[] arge) throws IOException, KeeperException, InterruptedException {

        TimeQueryServer timeQueryServer = new TimeQueryServer();
        timeQueryServer.registerServerTnfo(arge[0],arge[1]);
        new TimeQueryService(Integer.parseInt(arge[1])).start();

    }





    //注册服务器信息
    public void registerServerTnfo(String hostname,String port) throws KeeperException, InterruptedException {
        //判断主节点是否存在
        Stat exists = zookeeper.exists("/Server", false);
        if (exists==null){
            zookeeper.create("/Server",null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        //注册服务器数据到zookeeper的指定节点下
        String s = zookeeper.create("/Server/server", (hostname + ":" + port).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+"服务器注册信息成功：节点为：/Server/"+s);
    }

}
