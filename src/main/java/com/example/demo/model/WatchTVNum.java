package com.example.demo.model;

/**
 * Created by LZF on 2017/7/18.
 * 每个时间观看电视人数
 */
public class WatchTVNum {
    private String date;
    private int userNumber;

    public String getDate() {
        return date;
    }

    public WatchTVNum setDate(String date) {
        this.date = date;
        return this;
    }

    public long getUserNumber() {
        return userNumber;
    }

    public WatchTVNum setUserNumber(int userNumber) {
        this.userNumber = userNumber;
        return this;
    }
}
