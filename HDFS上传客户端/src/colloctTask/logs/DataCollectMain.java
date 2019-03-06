package colloctTask.logs;

import java.util.Timer;

public class DataCollectMain {
    public static void main(String[] arge){
        //启动定时服务器
        Timer timer = new Timer();
        timer.schedule(new CollectTask(),0,60*1000);
        timer.schedule(new BackupCleanTask(),0,60*1000);
    }
}
