package com.medialab_challenge.business;

import com.medialab_challenge.data.IRepository;
import com.medialab_challenge.data.Repository;
import com.medialab_challenge.vo.User;

import java.util.List;

public class MainActivityModel implements IMainActivityModel {
    private static MainActivityModel instance;
    private static IRepository repository;

    public static MainActivityModel getInstance() {
        if (null == instance) {
            instance = new MainActivityModel();
            repository = new Repository();
        }
        return instance;
    }


    @Override
    public void addUser(User user) {
        repository.saveUser(user);
    }

    @Override
    public List<User> getUserList() {
        return repository.getListUsers();
    }

    @Override
    public void deleteUser(User user) {
        repository.deleteUser(user);
    }
}
