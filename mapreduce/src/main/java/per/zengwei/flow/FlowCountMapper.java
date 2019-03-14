package per.zengwei.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper <LongWritable, Text,Text,FlowBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String dataline = value.toString();
        String[] split = dataline.split("\t");
        String phome = split[1];
        int upFlow = Integer.parseInt(split[split.length - 3]);
        int dFlow = Integer.parseInt(split[split.length - 2]);
        FlowBean flowBean = new FlowBean(upFlow, dFlow, phome);
        context.write(new Text(phome),flowBean);
        System.out.println(flowBean);
    }
}
