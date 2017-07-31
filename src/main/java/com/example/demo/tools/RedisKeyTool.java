package com.example.demo.tools;

/**
 * Created by LZF on 2017/6/17.
 */
public class RedisKeyTool {
    private static String SPLIT = ":";
    private static String NOW_WATCHTV_NUM = "now_watchTV_num";
    private static String ONLINE_USER_NUM = "online_user_Num";
    private static String EVERYMILLS_SENDREQUEST_USERID = "everymills_sendRequest_userId";//每秒钟内发送请求信息的用户id

    public static String getNowWatchTVNumKey(){
        return NOW_WATCHTV_NUM ;
    }

    public static String getOnlineUserNum(){
        return ONLINE_USER_NUM ;
    }

    public static String getEverymillsSendrequestUserid(String date){
        return EVERYMILLS_SENDREQUEST_USERID + SPLIT + date;
    }

}
