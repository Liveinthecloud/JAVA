package per.zengwei.friends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class CommonFriendsTwo {
    public static class CommonFriendsTwoMapper extends Mapper<LongWritable, Text,Text,Text>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("\t");
            context.write(new Text(split[0]),new Text(split[1]));
        }
    }
    public static class CommonFriendsTwoReducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String friends="";
            for (Text value :values
                    ) {
                friends+=value.toString()+"\t";
            }
            context.write(key,new Text(friends));
        }
    }
    public static void main(String [] arge) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);


        job.setJarByClass(CommonFriendsOne.class);
        job.setMapperClass(CommonFriendsTwoMapper.class);
        job.setReducerClass(CommonFriendsTwoReducer.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("f:/friends/output"));
        FileOutputFormat.setOutputPath(job,new Path("f:/friends/output_2"));
        job.waitForCompletion(true);
    }
}
