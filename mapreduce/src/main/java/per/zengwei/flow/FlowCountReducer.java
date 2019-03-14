package per.zengwei.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
    @Override
    /*key:手机号
    * values:这个手机号所产生的所有的访问记录中的流量
    * */

    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        int upSum=0;
        int dSum=0;
        for (FlowBean value: values
             ) {
            upSum+=value.getUpFlow();
            dSum+=value.getdFlow();
        }
        FlowBean flowBean = new FlowBean(upSum, dSum, key.toString());
        context.write(key,flowBean);
        System.err.println(flowBean);
    }
}
