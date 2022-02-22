package com.medialab_challenge.data;

import com.medialab_challenge.database.UserDao;
import com.medialab_challenge.vo.User;

import java.util.List;

public class Repository implements IRepository {


    @Override
    public User getUser(Integer id) {
        return UserDao.getinstance().getUser(id);
    }

    @Override
    public List<User> getListUsers() {
        return UserDao.getinstance().getListUsers();
    }

    @Override
    public void saveUser(User user) {
        UserDao.getinstance().saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        UserDao.getinstance().deleteUser(user);
    }
}
