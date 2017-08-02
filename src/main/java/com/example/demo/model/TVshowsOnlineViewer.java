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

    public void setDate(String date) {
        this.date = date;
    }

    public int getShowName() {
        return showName;
    }

    public void setShowName(int showName) {
        this.showName = showName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
