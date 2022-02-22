package com.medialab_challenge.business;

import com.medialab_challenge.vo.User;

import java.util.List;

public interface IMainActivityModel {
    void addUser(User user);
    List<User> getUserList();
    void deleteUser(User user);
}
