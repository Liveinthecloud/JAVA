package per.zengwei.Order.Order_2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OrderTopn_2 {
    public static class OrderTopn_2Mapper extends Mapper<LongWritable, Text,OrderBean_2, NullWritable>{
        OrderBean_2 bean_2=new OrderBean_2();
        NullWritable v= NullWritable.get();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split(",");
            bean_2.setBean(split[0],split[1],split[2],Float.parseFloat(split[3]),Integer.parseInt(split[4]));
            context.write(bean_2,v);
        }
    }
    public static class OrderTopn_2Reducer extends Reducer<OrderBean_2,NullWritable,OrderBean_2,NullWritable>{
        @Override
        protected void reduce(OrderBean_2 key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            int i=0;
            for (NullWritable v :values
                    ) {
                context.write(key,v);
                if(++i==3) return;
            }
        }
    }
    public static void main(String [] arge) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //要获取的数据个数
        conf.setInt("order.top.n",2);
        Job job = Job.getInstance(conf);


        job.setJarByClass(OrderTopn_2.class);
        job.setMapperClass(OrderTopn_2Mapper.class);
        job.setReducerClass(OrderTopn_2Reducer.class);

        job.setPartitionerClass(OrderIdPartition.class);
        job.setGroupingComparatorClass(OrderIdGroupingComparator.class);

        job.setMapOutputKeyClass(OrderBean_2.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(OrderBean_2.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("f:/order/input"));
        FileOutputFormat.setOutputPath(job,new Path("f:/order/output_2"));
        job.waitForCompletion(true);
    }
 }
