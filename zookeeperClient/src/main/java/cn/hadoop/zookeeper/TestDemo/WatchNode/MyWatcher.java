package cn.hadoop.zookeeper.TestDemo.WatchNode;

import cn.hadoop.zookeeper.TestDemo.Util.ZookeeperUtil;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


/**
 * 自定义事件响应逻辑
 *
 * KeeperState.SyncConnected
 * 一旦客户端和服务器的某一个节点建立连接（注意，虽然集群有多个节点，但是客户端一次连接到一个节点就行了），
 * 并完成一次version、zxid的同步，这时的客户端和服务器的连接状态就是SyncConnected
 */
public class MyWatcher implements Watcher {
    public void process(WatchedEvent event) {
        try {
            //zookeeper是一个单利模式 已注册事件自定义的监听
            ZooKeeper zookeeper = ZookeeperUtil.getZookeeper();
                                                                                            //  主节点信息发生改变
            if (event.getState()== Event.KeeperState.SyncConnected&&event.getType()== Event.EventType.NodeDataChanged){
                System.out.println(event.getPath());//收到的事件发生的节点路径
                System.out.println(event.getType());//收到的事件的类型
                System.out.println("数据改动");
                byte[] data = zookeeper.getData("/idea", true, null);
                System.out.println(new String(data));

                /*当node-x这个节点的直接子节点被创建、被删除、子节点数据发生变更时，该事件被触发*/
            }else if(event.getState()== Event.KeeperState.SyncConnected&&event.getType()== Event.EventType.NodeChildrenChanged){
                System.out.println("子节点变化:\n"+event.toString());
                zookeeper.getChildren("/idea",true);//再次注册监听
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
