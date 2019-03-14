package per.zengwei.Topn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PageTopnReduce extends Reducer<Text, IntWritable,Text,IntWritable> {
    TreeMap<PageCount,Object> treeMap=new TreeMap<PageCount, Object>();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        for (IntWritable value:values
             ) {
            count+=value.get();
        }
        PageCount pageCount = new PageCount(key.toString(),count);
        treeMap.put(pageCount,null);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        int topn = conf.getInt("top.n", 5);
        int i=0;
        Set<Map.Entry<PageCount, Object>> entrySet = treeMap.entrySet();
        for (Map.Entry<PageCount, Object> entry:entrySet
             ) {
            context.write(new Text(entry.getKey().getPage()),new IntWritable(entry.getKey().getCount()));
            i++;
            if(i==topn) return;
        }
    }
}
