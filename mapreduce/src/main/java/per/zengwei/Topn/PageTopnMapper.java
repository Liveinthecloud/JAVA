package per.zengwei.Topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PageTopnMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //读取一行数据
        String line = value.toString();
        //切割数据
        String[] split = line.split(" ");
        //把数据给reduce ----  网站
        context.write(new Text(split[1]) ,new IntWritable(1));
    }
}
