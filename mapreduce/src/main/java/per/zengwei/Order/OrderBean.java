package per.zengwei.Order;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements Writable , WritableComparable<OrderBean> {
    private String orderId;
    private String userId;
    private String pdtName;
    private float price;
    private int number;
    private float amountFee;

    public void setBean(String orderId, String userId, String pdtName, float price, int number) {
        this.orderId = orderId;
        this.userId = userId;
        this.pdtName = pdtName;
        this.price = price;
        this.number = number;
        this.amountFee=price*number;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPdtName() {
        return pdtName;
    }

    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(float amountFee) {
        this.amountFee = amountFee;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", pdtName='" + pdtName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", amountFee=" + amountFee +
                '}';
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(userId);
        dataOutput.writeUTF(pdtName);
        dataOutput.writeFloat(price);
        dataOutput.writeInt(number);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.orderId=dataInput.readUTF();
        this.userId=dataInput.readUTF();
        this.pdtName=dataInput.readUTF();
        this.price=dataInput.readFloat();
        this.number=dataInput.readInt();
        this.amountFee=this.price*this.number;
    }

    public int compareTo(OrderBean o) {
        //先比总金额，在比商品名
        return Float.compare(o.getAmountFee(),this.amountFee)==0?this.pdtName.compareTo(o.getPdtName()):Float.compare(o.getAmountFee(),this.amountFee);
    }
}
