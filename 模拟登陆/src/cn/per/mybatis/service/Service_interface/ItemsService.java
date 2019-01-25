package cn.per.mybatis.service.Service_interface;

import cn.per.mybatis.model.Items;
import cn.per.mybatis.vo.ItemsVo;

import java.util.List;

public interface ItemsService {
    public List<Items> findItemsByNamePrice(Items items);
    public void deleteItems(ItemsVo itemsVo);
}
