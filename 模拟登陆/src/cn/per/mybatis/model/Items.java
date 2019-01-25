package cn.per.mybatis.model;

public class Items {
    private int itemsId;
    private String itemsName;
    private String price;

    public int getItemsId() {
        return itemsId;
    }

    public void setItemsId(int itemsId) {
        this.itemsId = itemsId;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemsId=" + itemsId +
                ", itemsName='" + itemsName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
