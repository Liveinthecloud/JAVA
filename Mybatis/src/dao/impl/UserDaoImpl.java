package dao.impl;

import Model.User;
import dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import util.MybatisUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    /**
     * 原始dao的实现类的方法
     * @param id
     * @return
     */
    @Override
    public User findUserByID(int id) {
        //数据库操作 获取会话
        SqlSession session= MybatisUtil.getFactory().openSession();
        User o = session.selectOne("Model.User.findUserById", id);
        return o;
    }

    /**
     * 使用mapper代理
     * @param name
     * @return
     */
    @Override
    public List<User> findUserByName(String name) {
        SqlSession session= MybatisUtil.getFactory().openSession();
        UserDao mapper = session.getMapper(UserDao.class);
        return mapper.findUserByName(name);
    }
}
