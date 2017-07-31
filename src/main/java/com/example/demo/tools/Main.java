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


		Thread.sleep(60000);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		//初始化操作
		HttpRequest.sendGet("http://127.0.0.1:8080/initOnlineUserNum", "date=" + df.format(new Date()));
		//统计每个时刻看电视人数
		while(true){
			Thread.sleep(1000);
			String s = HttpRequest.sendGet("http://127.0.0.1:8080/nowWatchTVNum", "date=" + df.format(new Date()));
			System.out.println(s);
		}
	}
}
