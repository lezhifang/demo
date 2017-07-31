package com.example.demo.tools;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserThread extends Thread {	

	int id;
	int cycle_time;
	CycleThread cycle_thread = null;
	public UserThread(int _id, int _cycle_time) {
		this.id = _id;
		this.cycle_time = _cycle_time;
		cycle_thread = new CycleThread(_id, _cycle_time);
	}
	
    @Override
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		//System.out.println(id + "\t" + df.format(new Date()) + "\t0\t0");
		synchronized (this) {//先开机
			HttpRequest.sendGet("http://127.0.0.1:8080/addData",
					"userId=" + id + "&date=" + df.format(new Date()) + "&userBehavior=" + 0 + "&showName=" + 0);
		}
		cycle_thread.start();//发心跳
    	while (true)
		{
			int rand_sleep_time = (int)(Math.random()*20) * 1000 + 3000;
			int tv_id = (int)(Math.random()*10);
	        try {
	        	sleep(rand_sleep_time);
	        	//System.out.println(id + "\t" + df.format(new Date()) + "\t1\t" + tv_id);
				HttpRequest.sendGet("http://127.0.0.1:8080/addData",
						"userId="+ id + "&date=" + df.format(new Date()) + "&userBehavior=" + 1 + "&showName=" + tv_id);

			} catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        int rand_turn_time = (int)(Math.random()*5);
	        if (rand_turn_time == 0) {
	        	break;
	        }
    	}
    	//System.out.println(id + "\t" + df.format(new Date()) + "\t3\tnull");
    	cycle_thread.stop();
    }
}