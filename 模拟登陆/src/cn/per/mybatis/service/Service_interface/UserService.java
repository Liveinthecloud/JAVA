package cn.per.mybatis.service.Service_interface;

import cn.per.mybatis.model.User;

public interface UserService {
    public User findUserByUserNameAndPassword(User user);
}
