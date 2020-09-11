package com.geektech.mytaskapp.models;

import java.io.Serializable;

public class User implements Serializable {

    private String textNickname;
    private int imageAvatar;

    public User(String textNickname, int imageAvatar) {
        this.textNickname = textNickname;
        this.imageAvatar = imageAvatar;
    }

    public String getTextNickname() {
        return textNickname;
    }

    public void setTextNickname(String textNickname) {
        this.textNickname = textNickname;
    }

    public int getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(int imageAvatar) {
        this.imageAvatar = imageAvatar;
    }
}