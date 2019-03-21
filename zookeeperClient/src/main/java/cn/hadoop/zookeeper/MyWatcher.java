package cn.hadoop.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class MyWatcher implements Watcher {
    public void process(WatchedEvent event) {
        try {
            ZooKeeper zookeeper = ZookeeperUtil.getZookeeper();
            if (event.getState()== Event.KeeperState.SyncConnected&&event.getType()== Event.EventType.NodeDataChanged){
                System.out.println(event.getPath());//收到的事件发生的节点路径
                System.out.println(event.getType());//收到的事件的类型
                System.out.println("数据改动");
                byte[] data = zookeeper.getData("/idea", true, null);
                System.out.println(new String(data));
            }else if(event.getState()== Event.KeeperState.SyncConnected&&event.getType()== Event.EventType.NodeChildrenChanged){
                System.out.println("子节点变化");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
