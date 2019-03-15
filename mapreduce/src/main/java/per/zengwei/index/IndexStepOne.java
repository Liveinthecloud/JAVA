package per.zengwei.index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class IndexStepOne {
    public static class IndexStepOneMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        @Override
        //产生<hello-文件名,1>
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {//产生<hello-文件名,1>
            //获取文件名
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String filename = inputSplit.getPath().getName();
            //从输入切片信息中获取数据
            String[] split = value.toString().split(" ");
            for (String w :split
                    ) {
                context.write(new Text(w+"-"+filename),new IntWritable(1));
            }

        }
    }
    public static class IndexStepOneReduce extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count=0;
            for (IntWritable value:values
                 ) {
                count+=value.get();
            }
            context.write(key,new IntWritable(count));
        }
    }
    public static void main(String[] arge) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(IndexStepOne.class);
        job.setMapperClass(IndexStepOneMapper.class);
        job.setReducerClass(IndexStepOneReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("f:\\wordfile\\input"));
        FileOutputFormat.setOutputPath(job,new Path("f:\\wordfile\\output"));

        job.waitForCompletion(true);
    }
}
