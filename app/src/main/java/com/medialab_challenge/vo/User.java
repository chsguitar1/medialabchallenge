package com.medialab_challenge.vo;

import android.graphics.Bitmap;

public class User {
    public Integer id;
    public String name;
    public String email;
    public Bitmap image;

    public User(Integer id, String name, String email, Bitmap image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public User() {
    }
}
