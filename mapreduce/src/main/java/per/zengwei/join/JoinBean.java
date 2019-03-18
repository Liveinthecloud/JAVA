package per.zengwei.join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class JoinBean implements Writable {
    private String orderId;
    private String userId;
    private String userName;
    private int age;
    private String userFriend;
    private String tableName;

    public JoinBean(){super();}

    public void setJoinBean(String orderId, String userId, String userName, int age, String userFriend, String tableName) {
        this.orderId = orderId;
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.userFriend = userFriend;
        this.tableName = tableName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserFriend() {
        return userFriend;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setUserFriend(String userFriend) {
        this.userFriend = userFriend;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(userId);
        dataOutput.writeUTF(userName);
        dataOutput.writeInt(age);
        dataOutput.writeUTF(userFriend);
        dataOutput.writeUTF(tableName);

    }

    public void readFields(DataInput dataInput) throws IOException {
        this.orderId = dataInput.readUTF();
        this.userId = dataInput.readUTF();
        this.userName = dataInput.readUTF();
        this.age = dataInput.readInt();
        this.userFriend = dataInput.readUTF();
        this.tableName = dataInput.readUTF();
    }


    @Override
    public String toString() {
        return "JoinBean{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", userFriend='" + userFriend + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
