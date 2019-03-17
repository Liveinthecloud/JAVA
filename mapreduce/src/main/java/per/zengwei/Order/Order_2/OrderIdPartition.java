package per.zengwei.Order.Order_2;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class OrderIdPartition extends Partitioner<OrderBean_2, NullWritable> {
    @Override
    //自定义给Reducer Task 的数据
    public int getPartition(OrderBean_2 orderBean_2, NullWritable nullWritable, int i) {
        //按照OrderId来分发数据，OrderId一样的分到一个数组
        return (orderBean_2.getOrderId().hashCode()&Integer.MAX_VALUE)%i;
    }
}
