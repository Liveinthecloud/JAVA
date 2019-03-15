package per.zengwei.flow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobSubmitter {
    public static void main(String[] arge) throws Exception {
        //配置对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(JobSubmitter.class);
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setPartitionerClass(ProvincePartitioner.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job,new Path("F:\\flow\\input"));
        FileOutputFormat.setOutputPath(job,new Path("F:\\flow\\output"));
        //ReduceTasks的个数
        job.setNumReduceTasks(6);
        job.waitForCompletion(true);
    }
}
