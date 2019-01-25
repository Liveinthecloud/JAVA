package cn.per.mybatis.dao.Items;

import cn.per.mybatis.model.Items;
import cn.per.mybatis.vo.ItemsVo;

import java.util.List;

public interface ItemsDao {
    public List<Items> findItemsByNamePrice(Items items);
    public void deleteItems(ItemsVo itemsVo);

}
