package cn.per.mybatis.dao.User;

import cn.per.mybatis.model.User;

public interface UserDao {
    public User findUserByUserNameAndPasswordDao(User user);
}
