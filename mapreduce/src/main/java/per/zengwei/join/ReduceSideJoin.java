package per.zengwei.join;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ReduceSideJoin {
    public static class ReduceSideJoinMapper extends Mapper<LongWritable, Text, Text, JoinBean> {
        //可以减少重复的创建对象，减小了垃圾回收的压力
        FileSplit inputSplit;
        String fileName;
        JoinBean bean = new JoinBean();
        Text k = new Text();
        /*maptask在处理时，会先调用一次setup*/
        /*这个方法只调用一次*/
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            //获取读取到的文件的名字,避免每一行都去get文件名
            inputSplit = (FileSplit) context.getInputSplit();
            fileName = inputSplit.getPath().getName();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split(",");
            if (fileName.startsWith("order")) {
                bean.setJoinBean(split[0], split[1], "null", -1, "null", "order");
            } else {
                bean.setJoinBean("null", split[0], split[1], Integer.parseInt(split[2]), split[3], "user");
            }
            k.set(bean.getUserId());
            context.write(k, bean);
            System.out.println(bean);
        }

    }

    public static class ReduceSideJoinReduce extends Reducer<Text, JoinBean, JoinBean, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<JoinBean> values, Context context) throws IOException, InterruptedException {
            ArrayList<JoinBean> orderLiset = new ArrayList<JoinBean>();
            JoinBean userBean = null;
            try {

                //区分数据
                for (JoinBean bean : values
                ) {
                    if ("order".equals(bean.getTableName())) {
                        JoinBean newBean = new JoinBean();
                        BeanUtils.copyProperties(newBean, bean);
                        orderLiset.add(newBean);
                    }else{
                        userBean=new JoinBean();
                        BeanUtils.copyProperties(userBean, bean);
                    }
                }
                //拼接数据
                for (JoinBean bean : orderLiset) {
                    bean.setUserName(userBean.getUserName());
                    bean.setAge(userBean.getAge());
                    bean.setUserFriend(userBean.getUserFriend());
                    context.write(bean,NullWritable.get());
                    System.err.println(bean);
                }
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }
    }
    public static void main(String[] arge) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);


        job.setJarByClass(ReduceSideJoin.class);
        job.setMapperClass(ReduceSideJoinMapper.class);
        job.setReducerClass(ReduceSideJoinReduce.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(JoinBean.class);
        job.setOutputKeyClass(JoinBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("f:/order_user/input"));
        FileOutputFormat.setOutputPath(job,new Path("f:/order_user/output"));
        job.waitForCompletion(true);
    }
}
