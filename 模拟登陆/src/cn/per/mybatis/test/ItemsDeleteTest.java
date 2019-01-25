package cn.per.mybatis.test;

import cn.per.mybatis.service.impl.ItemsServiceImpl;
import cn.per.mybatis.vo.ItemsVo;

import java.util.ArrayList;
import java.util.List;

public class ItemsDeleteTest {
    public static void main(String[] arge){
        ItemsVo vo = new ItemsVo();
        List<Integer> list=new ArrayList<>();
        list.add(3);
        list.add(1);
        vo.setIds(list);
        new ItemsServiceImpl().deleteItems(vo);
    }
}
