package com.example.demo.tools;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	final static int cycle_time = 10000;//心跳周期
	final static int user_num = 3;//用户数，也就是线程数
	
	public static void main(String args[]) throws InterruptedException {
		
		for (int i = 0; i < user_num; i++) {
			UserThread user = new UserThread(i, cycle_time);
			user.start();
		}

		//初始化操作
		Thread.sleep(60000);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		HttpRequest.sendGet("http://127.0.0.1:8080/initialization", "date=" + df.format(new Date()));

		long start = 0;
		long end = 0;
		//统计每个时刻看电视人数
		while(true){
			Thread.sleep(1000 - (end - start));//保证1s发送一个请求
			start = System.currentTimeMillis();
			String s = HttpRequest.sendGet("http://127.0.0.1:8080/realtimeCalculation", "date=" + df.format(new Date()));
			System.out.println(s);
			end = System.currentTimeMillis();
		}
	}
}