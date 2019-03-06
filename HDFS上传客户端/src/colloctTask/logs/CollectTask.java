package colloctTask.logs;


import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import property.PropertyHolder;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

public class CollectTask extends TimerTask {

    public void run() {
        try {
            //获取配置文件
            Properties propers= PropertyHolder.getProper();
            //构造一个log4j日志
            Logger logger = Logger.getLogger("INFO");
            //上传到HDFS  创建一个 HDFS客户端对象
            FileSystem fs = FileSystem.get(new URI(propers.getProperty("HDFS_URI")), new Configuration(), "root");
            //探测源目录
            File srcDir = new File(propers.getProperty("LOG_SOURCE_DIR"));

            //临时目录
            File toUpDataDir = new File(propers.getProperty("LOG_TOPULOAD_DIR"));
            if(!toUpDataDir.exists()){
                toUpDataDir.mkdirs();
            }
            //HDFS上传路径
            Path  hdfsPath = new Path(propers.getProperty("HDFS_DEST_BASE_DIR") + getTime()+"/");
            if (fs.exists(hdfsPath)) {
                fs.mkdirs(hdfsPath);
            }
            //备份目录
            File backupDir = new File(propers.getProperty("LOG_BACKUP_BASE_DIR") + getTime()+"/");
            if(!backupDir.exists()){
                backupDir.mkdirs();
            }

            //找到要采集的文件
            File[] listfile = srcDir.listFiles(new srcFilenameFilter());
            //记录日志
            logger.info("如下文件需要采集："+ Arrays.toString(listfile));
            //移到临时目录
            for (File file : listfile
            ) {
                FileUtils.moveFileToDirectory(file, toUpDataDir, true);
            }
            File[] topuloadFile = toUpDataDir.listFiles();
            //记录日志
            logger.info("如下文件已经移动到了待上传目录："+ Arrays.toString(topuloadFile));
            for (File file:topuloadFile
                 ) {
                //上传文件到HDFS并改名
                fs.copyFromLocalFile(new Path(file.getAbsolutePath()),new Path(hdfsPath+ "/"+propers.getProperty("HDFS_FILE_PREFIX") + UUID.randomUUID() + propers.getProperty("HDFS_FILE_SUFFIX")));
                logger.info("文件上传到HDFS："+ file.getAbsolutePath()+"---->"+hdfsPath);
                FileUtils.moveFileToDirectory(file, backupDir, true);
                logger.info("文件备份："+ file.getAbsolutePath()+"---->"+backupDir);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取当前采集时间
   public  String getTime(){
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        return simpleDateFormat.format(new Date());
   }
}
