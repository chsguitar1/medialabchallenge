package com.medialab_challenge.data;

import com.medialab_challenge.vo.User;

import java.util.List;

public interface IRepository {
    public User getUser(Integer id);
    public List<User> getListUsers();
    public void saveUser(User user);
    public void deleteUser(User user);
}
