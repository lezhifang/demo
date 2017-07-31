package com.example.demo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CycleThread extends Thread {	

	int id;
	int cycle_time;
	
	public CycleThread(int _id, int _cycle_time) {
		// TODO Auto-generated constructor stub
		this.id = _id;
		this.cycle_time = _cycle_time;
	}
	
    @Override
    public void run() {
    	while (true) {
	        try {
	        	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	        	//System.out.println(id + "\t" + df.format(new Date()) + "\t2\tnull");
				HttpRequest.sendGet("http://127.0.0.1:8080/addData",
						"userId="+ id + "&date=" + df.format(new Date()) + "&userBehavior=" + 2 + "&showName=" + (-1));
	            sleep(cycle_time);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
    	}
    
    }
}