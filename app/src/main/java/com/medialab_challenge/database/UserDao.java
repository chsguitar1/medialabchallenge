package com.medialab_challenge.database;

import android.util.Log;

import com.medialab_challenge.vo.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private static UserDao instance;

    List<User> userList = new ArrayList<>();

    public static UserDao getinstance() {
        if (null == instance) {
            instance = new UserDao();
        }
        return instance;
    }

    @Override
    public User getUser(Integer id) {
        return userList.stream()
                .filter(user -> id.equals(user.id))
                .findAny()
                .orElse(null);

    }

    @Override
    public List<User> getListUsers() {
        return userList;
    }

    @Override
    public void saveUser(User user) {
        if (user.id != null && getUser(user.id) != null) {
           userList.stream().map( u -> {
               if(!u.equals(user)){
                u = user;
               }
               return u;
           });

        } else {
            Integer id = 0;
            if (userList.isEmpty()) {
                id++;
            } else {
                id = userList.get(0).id + 1;
            }
            user.id = id;
            userList.add(user);
        }
        Log.i("lista", userList.toString());
    }

    @Override
    public void deleteUser(User user) {
        userList.remove(user);
    }
}
