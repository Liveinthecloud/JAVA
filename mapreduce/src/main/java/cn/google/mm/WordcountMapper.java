package cn.google.mm;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordcountMapper extends Mapper <LongWritable,Text,Text,IntWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取每行数据  进行切割
        String line = value.toString();
        //空格切割
        String[] words = line.split(" ");
        for(String word:words){
            context.write(new Text(word),new IntWritable(1));
        }
    }

}
