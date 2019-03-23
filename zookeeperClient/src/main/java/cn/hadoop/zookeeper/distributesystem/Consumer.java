package cn.hadoop.zookeeper.distributesystem;

import cn.hadoop.zookeeper.TestDemo.ZookeeperUtil;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*模拟用户通过zookeeper选择服务器，达到服务器上下线的动态感知*/
public class Consumer {
    //存放最新的服务器列表
    private ArrayList<String> onlineServers=new ArrayList<String>();
    ZooKeeper zookeeper=null;
    public Consumer()  {
    }
    public static void main(String[] arge) throws IOException, KeeperException, InterruptedException {
        Consumer consumer = new Consumer();
        consumer.setregister();
        consumer.getOnlineServers();
        consumer.sendRequest();
    }
@Before
    /*注册监听*/
    public void setregister() throws IOException {
        zookeeper = ZookeeperUtil.getZookeeper();
        zookeeper.register(new Watcher() {
            public void process(WatchedEvent event) {
                //当子节点发生改变，对zookeeper上注册的服务器再次查询，更新在线服务器列表
                if (event.getState()== Event.KeeperState.SyncConnected&&event.getType()== Event.EventType.NodeChildrenChanged){
                    try {
                        //事件回调逻辑，再次查询zookeeper上的在线服务器节点，再次注册监听
                        getOnlineServers();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Test
    /*查询在线服务器，并且把在线服务器列表更新*/
    public void getOnlineServers() throws KeeperException, InterruptedException {
        ArrayList<String> serversData=new ArrayList<String>();
        //监听子节点
        List<String> servers = zookeeper.getChildren("/Server", true);
        for (String child : servers
                ) {
            byte[] data = zookeeper.getData("/Server/" + child, false, null);
            String s = new String(data);
            serversData.add(s);
        }
        onlineServers=serversData;
        System.out.println("查询了一次zookeeper，当前在线的服务器有\n"+servers);
    }
    /*模拟用户请求*/
    public void sendRequest() throws IOException, InterruptedException {
        Random random = new Random();
        while (true){
            //随机选择一台在线的服务器
            int i = random.nextInt(onlineServers.size());
            //获取一台主机名
            String server = onlineServers.get(i);
            //获取主机名 和  端口
            String hostname= server.split(":")[0];
            int port= Integer.parseInt(server.split(":")[1]);
            System.out.println("请求主机："+server);
            Socket socket = new Socket(hostname, port);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            out.write("我来了".getBytes());
            out.flush();
            byte[] buf=new byte[256];
            int read = in.read(buf);
            System.out.println("服务器响应的时间为："+new String(buf,0,read));
            in.close();
            out.close();
            socket.close();
            Thread.sleep(2000);
        }
    }
}
