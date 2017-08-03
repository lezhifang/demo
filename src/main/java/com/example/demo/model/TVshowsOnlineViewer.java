package com.example.demo.model;

/**
 * Created by LZF on 2017/8/2.
 */
public class TVshowsOnlineViewer {
    private String date;
    private int showName;
    private int count;//统计在当前时间date观看电视节目showName的人数

    public TVshowsOnlineViewer(String date, int showName, int count) {
        this.date = date;
        this.showName = showName;
        this.count = count;
    }

    public TVshowsOnlineViewer() {
    }

    public String getDate() {
        return date;
    }

    public TVshowsOnlineViewer setDate(String date) {
        this.date = date;
        return this;
    }

    public int getShowName() {
        return showName;
    }

    public TVshowsOnlineViewer setShowName(int showName) {
        this.showName = showName;
        return this;
    }

    public int getCount() {
        return count;
    }

    public TVshowsOnlineViewer setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "TVshowsOnlineViewer{" +
                "date='" + date + '\'' +
                ", showName=" + showName +
                ", count=" + count +
                '}';
    }
}
