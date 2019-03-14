package per.zengwei.Topn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.Properties;

public class JobSubmitter {
    public static void main(String[] arge) throws Exception {
        //配置对象
        Configuration conf = new Configuration();
        //conf.setInt("top.n",3);
        Properties properties = new Properties();
        properties.load(JobSubmitter.class.getClassLoader().getResourceAsStream("topn.properties"));
        conf.setInt("topn.n",Integer.parseInt(properties.getProperty("topn.n")));

        Job job = Job.getInstance(conf);
        job.setJarByClass(JobSubmitter.class);
        job.setMapperClass(PageTopnMapper.class);
        job.setReducerClass(PageTopnReduce.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("F:\\topn\\input"));
        FileOutputFormat.setOutputPath(job,new Path("F:\\topn\\output"));
        //ReduceTasks的个数
        job.setNumReduceTasks(2);
        job.waitForCompletion(true);
    }
}

