package com.example.demo.tools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
	final static int cycle_time = 10000;//心跳周期
	final static int user_num = 3;//用户数，也就是线程数
	
	public static void main(String args[]) throws InterruptedException, ParseException {

		for (int i = 0; i < user_num; i++) {
			UserThread user = new UserThread(i, cycle_time);
			user.start();
		}

		Thread.sleep(2000);

		//初始化和实时计算
		RealtimeThread realtime = new RealtimeThread();
		realtime.start();
	}
}

