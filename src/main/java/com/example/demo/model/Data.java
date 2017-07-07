package com.example.demo.model;

import java.util.Date;

/**
 * Created by LZF on 2017/7/7.
 */
public class Data {
    private int id;
    private String date;
    private int userBehavior;
    private String showName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserBehavior() {
        return userBehavior;
    }

    public void setUserBehavior(int userBehavior) {
        this.userBehavior = userBehavior;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
