package com.example.demo.model;


/**
 * Created by LZF on 2017/7/7.
 */
public class Data {
    private int userId;
    private String date;
    private int userBehavior;
    private int showName;


    public int getUserId() {
        return userId;
    }

    public Data setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Data setDate(String date) {
        this.date = date;
        return this;
    }

    public int getUserBehavior() {
        return userBehavior;
    }

    public Data setUserBehavior(int userBehavior) {
        this.userBehavior = userBehavior;
        return this;
    }

    public int getShowName() {
        return showName;
    }

    public Data setShowName(int showName) {
        this.showName = showName;
        return this;
    }

    @Override
    public String toString() {
        return "Data{" +
                "userId='" + userId + '\'' +
                ", date='" + date + '\'' +
                ", userBehavior=" + userBehavior +
                ", showName='" + showName + '\'' +
                '}';
    }
}
