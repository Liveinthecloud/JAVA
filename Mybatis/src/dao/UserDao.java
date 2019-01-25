package dao;

import Model.User;

import java.util.List;

public interface UserDao {
    public User findUserByID(int id);
    public List<User> findUserByName(String name);
}
