package cn.per.mybatis.test;

import cn.per.mybatis.model.Items;
import cn.per.mybatis.service.Service_interface.ItemsService;
import cn.per.mybatis.service.impl.ItemsServiceImpl;

import java.util.List;

public class ItemsTest {
    public static void main(String[] arge){
        Items vo=new Items();
        //vo.setItemsName("小米");
       // vo.setPrice("9999");
        vo.setItemsId(3);
        ItemsService itemsService=new ItemsServiceImpl();
        List<Items> itemsByNamePrice = itemsService.findItemsByNamePrice(vo);
        for (Items i :itemsByNamePrice
                ) {
            System.out.println(i);
        }
    }
}
