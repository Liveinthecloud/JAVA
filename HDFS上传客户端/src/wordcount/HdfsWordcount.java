package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HdfsWordcount {
    public static void main(String[] arge) throws Exception {

        /*初始化*/
        Properties propers = new Properties();
        propers.load(HdfsWordcount.class.getClassLoader().getResourceAsStream("job.properties"));
        Class<?> mapper_class = Class.forName(propers.getProperty("MAPPER_CLASS"));
        Mapper mapper = (Mapper)mapper_class.newInstance();
        Context context = new Context();
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration(), "root");
        Path input_path = new Path(propers.getProperty("INPUT_PATH"));
        Path output_path = new Path(propers.getProperty("OUTPUT_PATH"));
        if(fs.exists(output_path)){
            throw new RuntimeException("输出目录"+output_path+"已存在！");
        }
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(input_path, false);
        /*处理源数据*/
        BufferedReader buf;
        FSDataInputStream in;
        while (iterator.hasNext()){
            LocatedFileStatus file = iterator.next();
            in = fs.open(file.getPath());
            buf = new BufferedReader(new InputStreamReader(in));

            //去hdfs中读文件：一次读一行

            String line=null;
            while ((line=buf.readLine())!=null){
                System.out.println(line);
                //对每一行进行业务处理
                mapper.map(line,context);
            }
            buf.close();
        }
        /*
        * 输出结果
        *
        *
        * */
        HashMap<Object, Object> contextMap = context.getContextMap();
        FSDataOutputStream out = fs.create(new Path(output_path,new Path("res.dat")));
        Set<Map.Entry<Object, Object>> entries = contextMap.entrySet();
        for (Map.Entry<Object, Object> objectEntry: entries
             ) {
            out.write((objectEntry.getKey().toString()+ "\t"+objectEntry.getValue().toString()+"\n").getBytes());
        }
        out.close();

    }
}
