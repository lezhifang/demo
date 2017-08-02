package com.example.demo.model;

/**
 * Created by LZF on 2017/8/2.
 * 存放在HashMap中的value类
 */
public class Hvalue {
    private int cunt;//用于记录用户在线期间发送的请求数
    private int showName;//记录在线用户最新看的电视节目名称

    public Hvalue(int cunt, int showName) {
        this.cunt = cunt;
        this.showName = showName;
    }

    public Hvalue() {
    }

    public int getCunt() {
        return cunt;
    }

    public Hvalue setCunt(int cunt) {
        this.cunt = cunt;
        return this;
    }

    public int getShowName() {
        return showName;
    }

    public Hvalue setShowName(int showName) {
        this.showName = showName;
        return this;
    }

    @Override
    public String toString() {
        return "Hvalue{" +
                "cunt=" + cunt +
                ", showName=" + showName +
                '}';
    }
}
