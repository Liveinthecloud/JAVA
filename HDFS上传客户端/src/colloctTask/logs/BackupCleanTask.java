package colloctTask.logs;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import property.PropertyHolder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;

public class BackupCleanTask extends TimerTask {
    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        long now = new Date().getTime();
        try {
            //
            Properties proper = PropertyHolder.getProper();
            //探测本地备份目录
            File backupBaseDir = new File(proper.getProperty("LOG_BACKUP_BASE_DIR"));
            File[] dayBackDir = backupBaseDir.listFiles();
            //判断备份日期子目录是否超过24小时
            for (File dir:dayBackDir
                 ) {
                long time = sdf.parse(dir.getName()).getTime();
                if(now-time>24*60*60*1000){
                    FileUtils.deleteDirectory(dir);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
