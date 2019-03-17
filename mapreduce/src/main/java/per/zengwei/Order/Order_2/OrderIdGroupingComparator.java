package per.zengwei.Order.Order_2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderIdGroupingComparator extends WritableComparator {

    public OrderIdGroupingComparator() {
        super(OrderBean_2.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean_2 o1=(OrderBean_2)a;
        OrderBean_2 o2=(OrderBean_2)b;

        return o1.getOrderId().compareTo(o2.getOrderId());

    }
}
