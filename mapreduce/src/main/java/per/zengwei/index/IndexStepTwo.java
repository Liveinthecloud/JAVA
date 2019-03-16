package per.zengwei.index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class IndexStepTwo {
    static class IndexStepTwoMapper extends Mapper<LongWritable, Text,Text, Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("-");
            context.write(new Text(split[0]),new Text(split[1].replaceAll("\t","-->")));
        }
    }
    static class IndexStepTwoReduce extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String str=" ";
            for (Text value :values
                 ) {
                str+=value.toString()+"\t";
            }
            context.write(key,new Text(str));
        }
    }
   public static void main(String[] agre) throws IOException, ClassNotFoundException, InterruptedException {
       Configuration conf = new Configuration();

       Job job = Job.getInstance(conf);

       job.setJarByClass(IndexStepTwo.class);
       job.setMapperClass(IndexStepTwoMapper.class);
       job.setReducerClass(IndexStepTwoReduce.class);
       job.setMapOutputKeyClass(Text.class);
       job.setMapOutputValueClass(Text.class);
       job.setOutputKeyClass(Text.class);
       job.setOutputValueClass(Text.class);

       FileInputFormat.setInputPaths(job,new Path("f:\\wordfile\\output"));
       FileOutputFormat.setOutputPath(job,new Path("f:\\wordfile\\count"));

        job.waitForCompletion(true);

   }
}
