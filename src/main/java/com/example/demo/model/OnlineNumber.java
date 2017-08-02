package com.example.demo.model;

/**
 * Created by LZF on 2017/7/18.
 * 每个时间观看电视人数
 */
public class OnlineNumber {
    private String date;
    private int userNumber;

    public String getDate() {
        return date;
    }

    public OnlineNumber setDate(String date) {
        this.date = date;
        return this;
    }

    public long getUserNumber() {
        return userNumber;
    }

    public OnlineNumber setUserNumber(int userNumber) {
        this.userNumber = userNumber;
        return this;
    }
}
