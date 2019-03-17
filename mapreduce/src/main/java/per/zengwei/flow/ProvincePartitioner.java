package per.zengwei.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class ProvincePartitioner extends Partitioner<Text,FlowBean> {
    static HashMap<String , Integer> codeMap=new HashMap<String, Integer>();
    static{
        codeMap.put("135",0);   //给reduce task 0
        codeMap.put("136",1);   //给reduce task 1
        codeMap.put("137",2);   //给reduce task 2
        codeMap.put("138",3);   //给reduce task 3
        codeMap.put("139",4);   //给reduce task 4

    }
    /*设置reduce task 接收的数据*/
    @Override
    public int getPartition(Text text, FlowBean flowBean, int i) {
        Integer code = codeMap.get(text.toString().substring(0, 3));
        return code==null?5:code;
    }
}
