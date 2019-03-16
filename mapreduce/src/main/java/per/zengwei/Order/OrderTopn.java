package per.zengwei.Order;

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
import java.util.ArrayList;
import java.util.Collections;

public class OrderTopn {
    public static class OrderTopnMapper extends Mapper<LongWritable, Text,Text,OrderBean> {
        //避免每次都生成一个对象
        OrderBean bean = new OrderBean();
        Text k=new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split(",");
            bean.setBean(split[0],split[1],split[2],Float.parseFloat(split[3]),Integer.parseInt(split[4]));
            k.set(split[0]);
            context.write(k,bean);//给出去的都被序列化了，没存在内存中
        }
    }
    public static class OrderTopnReducer extends Reducer<Text,OrderBean,OrderBean, NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {
            //a获取topn的值 要输出几个值
            int top = context.getConfiguration().getInt("order.top.n", 3);


            ArrayList<OrderBean> beanList = new ArrayList<OrderBean>();
            for (OrderBean value:values
                 ) {
                OrderBean newBean = new OrderBean();
                newBean.setBean(value.getOrderId(),value.getUserId(),value.getPdtName(),value.getPrice(),value.getNumber());
                beanList.add(newBean);
            }
            //对OrderBean排序 比中金额
            Collections.sort(beanList);
            for(int i=0;i<top;i++){
                context.write(beanList.get(i),NullWritable.get());
            }

        }
    }
    public static void main(String [] arge) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //要获取的数据个数
        conf.setInt("order.top.n",2);
        Job job = Job.getInstance(conf);


        job.setJarByClass(OrderTopn.class);
        job.setMapperClass(OrderTopnMapper.class);
        job.setReducerClass(OrderTopnReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(OrderBean.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("f:/order/input"));
        FileOutputFormat.setOutputPath(job,new Path("f:/order/output"));
        job.waitForCompletion(true);
    }
}
