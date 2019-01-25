package cn.per.mybatis.service.impl;

import cn.per.mybatis.dao.Items.ItemsDao;
import cn.per.mybatis.model.Items;
import cn.per.mybatis.service.Service_interface.ItemsService;
import cn.per.mybatis.util.MybatisUtil;
import cn.per.mybatis.vo.ItemsVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ItemsServiceImpl implements ItemsService {
    @Override
    public void deleteItems(ItemsVo itemsVo) {
        SqlSession session = MybatisUtil.getFactory().openSession();
        ItemsDao mapper = session.getMapper(ItemsDao.class);
        mapper.deleteItems(itemsVo);
        session.commit();
        session.close();
    }

    @Override
    public List<Items> findItemsByNamePrice(Items items) {
        SqlSession session = MybatisUtil.getFactory().openSession();
        ItemsDao mapper = session.getMapper(ItemsDao.class);
        return mapper.findItemsByNamePrice(items);
    }
}
