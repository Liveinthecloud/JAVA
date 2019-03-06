package property;

import java.io.IOException;
import java.util.Properties;
/**
 *配置文件的加载的单例设计模式（双重检测）
 */

public class PropertyHolder {
    private static volatile Properties proper;
    private PropertyHolder(){}
    public static Properties getProper() throws IOException {
        if(proper==null){
            synchronized (PropertyHolder.class){
                if(proper==null){
                    proper = new Properties();
                    proper.load(PropertyHolder.class.getClassLoader().getResourceAsStream("collect.properties"));
                }
            }
        }
        return proper;
    }



}
