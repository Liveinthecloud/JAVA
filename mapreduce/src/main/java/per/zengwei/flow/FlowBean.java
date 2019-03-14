package per.zengwei.flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {
    private int upFlow;
    private int dFlow;
    private int amountFlow;
    private String phome;
    //这个一定要有
    public FlowBean(){
        super();
    }

    public FlowBean(int upFlow, int dFlow,String phome) {
        this.upFlow = upFlow;
        this.dFlow = dFlow;
        this.phome=phome;
        this.amountFlow = upFlow+dFlow;
    }

    public String getPhome() {
        return phome;
    }

    public void setPhome(String phome) {
        this.phome = phome;
    }

    public int getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(int upFlow) {
        this.upFlow = upFlow;
    }

    public int getdFlow() {
        return dFlow;
    }

    public void setdFlow(int dFlow) {
        this.dFlow = dFlow;
    }

    public int getAmountFlow() {
        return amountFlow;
    }

    public void setAmountFlow(int amountFlow) {
        this.amountFlow = amountFlow;
    }
    /* hadoop系统在序列化该类的对象时要调用的方法*/
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phome);
        dataOutput.writeInt(upFlow);
        dataOutput.writeInt(dFlow);
        dataOutput.writeInt(amountFlow);
    }
    /*hadoop系统在反序列化该类时要调用的方法*/
    public void readFields(DataInput dataInput) throws IOException {
        this.phome=dataInput.readUTF();
        this.upFlow = dataInput.readInt();
        this.dFlow=dataInput.readInt();
        this.amountFlow=dataInput.readInt();


    }

    @Override
    public String toString() {
        return "FlowBean{" +
                "upFlow=" + upFlow +
                ", dFlow=" + dFlow +
                ", amountFlow=" + amountFlow +
                ", phome='" + phome + '\'' +
                '}';
    }
}
