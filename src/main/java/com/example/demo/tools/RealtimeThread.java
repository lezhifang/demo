package com.example.demo.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LZF on 2017/8/3.
 */
public class RealtimeThread extends Thread{

    @Override
    public void run() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式

        long start = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        HttpRequest.sendGet("http://127.0.0.1:8080/initialization", "date=" + df.format(new Date()));
        long end = System.currentTimeMillis();

        //统计每个时刻看电视人数
        while(true){
            calendar.setTime(new Date());
            calendar.add(Calendar.MILLISECOND, (int)(start - end));
            String date = df.format(calendar.getTime());
            try {
                Thread.sleep(1000 - (end - start));//保证1s发送一个请求
                start = System.currentTimeMillis();
                String s = HttpRequest.sendGet("http://127.0.0.1:8080/realtimeCalculation", "date=" + date);//实际是计算当前时间前一秒的数据
                System.out.println(s);
                end = System.currentTimeMillis();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
