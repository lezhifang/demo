package com.example.demo.model;

/**
 * Created by LZF on 2017/8/2.
 * 存放data中用户名Id(u)、节目名称(s)、日期的类(d)
 */
public class Lvalue {
    private int userId;
    private int showName;
    private String date;

    public Lvalue(int userId, int showName, String date) {
        this.userId = userId;
        this.showName = showName;
        this.date = date;
    }

    public Lvalue() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowName() {
        return showName;
    }

    public void setShowName(int showName) {
        this.showName = showName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lvalue{" +
                "userId=" + userId +
                ", showName=" + showName +
                ", date='" + date + '\'' +
                '}';
    }
}
