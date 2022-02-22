package com.medialab_challenge.business;

import com.medialab_challenge.vo.User;

public interface IUserDetailsModel {
    public User getUser(Integer userId);
    public void saveUser(User user);
}
