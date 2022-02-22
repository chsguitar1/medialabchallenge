package com.medialab_challenge.business;

import com.medialab_challenge.data.IRepository;
import com.medialab_challenge.data.Repository;
import com.medialab_challenge.vo.User;

public class UserDetailsModel implements IUserDetailsModel {
    private static UserDetailsModel instance;
    private static IRepository repository;

    public static UserDetailsModel getInstance() {
        if (null == instance) {
            instance = new UserDetailsModel();
            repository = new Repository();
        }
        return instance;
    }

    @Override
    public User getUser(Integer userid) {
        return repository.getUser(userid);
    }

    @Override
    public void saveUser(User user) {
        repository.saveUser(user);
    }
}
