package Hadoop.HDFS;

import java.io.File;
import java.io.FilenameFilter;
/**
 * 源文件的过滤器
 */
public class srcFilenameFilter implements FilenameFilter {
    @Override
    public  boolean accept(File dir, String name) {
        if(name.startsWith("access.log."))
            return true;
        return false;
    }
}
