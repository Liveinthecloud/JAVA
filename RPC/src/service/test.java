package service;

import testRPC.Product;

public class test {
    public static void main(String[] arge) throws Exception {
        ReadDataUtil rutil = new ReadDataUtil();
        ReadData instance = rutil.getInstance();
        Product p=instance.findProductById(1);
        System.out.println(p);
    }
}
