package colloctTask.logs;

import property.PropertyHolder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

/**
 * 源文件的过滤器
 */
public class srcFilenameFilter implements FilenameFilter {

    @Override
    public  boolean accept(File dir, String name) {
        Properties proper;
        try {
            proper = PropertyHolder.getProper();
            if(name.startsWith(proper.getProperty("LOG_LEGAL_PREFIX")))
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
