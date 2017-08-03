package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.DataService;
import com.example.demo.service.OnlineNumberService;
import com.example.demo.service.TVshowsOnlineViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static com.example.demo.controller.DataController.list;

/**
 * Created by LZF on 2017/7/18.
 */
@Controller
public class RealtimeCalculationController {

    static int onlineNum = 0;//用来实时统计当前时间看电视的人数
    static int[] showCount = new int[10];//表示10个节目
    private static HashMap<Integer,Hvalue> map = new HashMap<Integer,Hvalue>();//使用HashMap存放每个在线用户请求数总数和最新看的电视节目

    @Autowired
    private OnlineNumberService onlineNumberService;
    @Autowired
    TVshowsOnlineViewerService tVshowsOnlineViewerService;
    @Autowired
    private DataService dataService;

    @RequestMapping(path = {"/initialization"}, method = {RequestMethod.GET})
    @ResponseBody
    public String initialization(Model model, @RequestParam(value = "date") String date) throws ParseException {
        //将MySQL数据库中date时间前5个心跳周期的数据添加到HashMap中   初始化HashMap
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(date));
        calendar.add(Calendar.SECOND, -50);//用于计算5个心跳周期的起始时间  即：数据库查询条件date>=#{start} and date<#{end}
        String start = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
        //System.out.println("----------------" + start + "----------------");
        List<Data> datas = dataService.selectUserIdBydate(start, date);

        for(Data data : datas){
            int userId = data.getUserId();
            int showName = data.getShowName();
            if(map.containsKey(userId)){//已存在该用户Id    userId用户发送请求数+1,当前看的电视节目若为心跳，则节目不变
                map.get(userId).setCunt( map.get(userId).getCunt() + 1 );

                if(showName != -1){
                    map.get(userId).setShowName(showName);
                }
                System.out.println(date + "\tmap初始化的时候\t" + map.toString());//----------------------------------------
            }else{//不存在该用户Id
                Hvalue hvalue = new Hvalue().setCunt(1).setShowName(showName);
                map.put(userId, hvalue);//添加对应的key=userId并将在线人数设置为1,并当前看的电视节目
                System.out.println(date + "\tmap初始化的时候\t" + map.toString());
            }
        }

        onlineNum = map.size();//初始化当前时间看电视人数
        for(Map.Entry<Integer,Hvalue> entry : map.entrySet()){
            int index = entry.getValue().getShowName();
            ++showCount[index];//索引对应相应的节目  例如：索引0存放当前看节目名称为0的总人数
        }

        //初始化LinkedList，即：只保留date时间前5个心跳周期的数据
        synchronized (list){
            while(true){
                if(list.size()!=0  && (list.getLast().getDate()).compareTo(start) < 0) {
                    list.removeLast();
                    System.out.println(date + "\tlist初始化时候\t" + list.size() +  "\t" + list.toString());//-----------------------------------------------
                }else{
                    break;
                }
            }
        }
        return "初始化成功";
    }

    @RequestMapping(path = {"/realtimeCalculation"}, method = {RequestMethod.GET})
    public String realtimeCalculation(Model model, @RequestParam(value = "date") String date) throws ParseException {
        System.out.println("请求开始时间\t" + date);//-----------------------------------

        //添加date时间用户请求
        synchronized (list){
            for(Lvalue lvalue : list){
                if(lvalue.getDate().equals(date)){
                    int userId = lvalue.getUserId();
                    int showName = lvalue.getShowName();

                    if(map.containsKey(userId)){//已存在该用户Id
                        map.get(userId).setCunt( map.get(userId).getCunt() + 1 );

                        if(showName != -1){//非心跳
                            --showCount[map.get(userId).getShowName()];
                            ++showCount[showName];
                            map.get(userId).setShowName(showName);
                        }
                        System.out.println(date + "\tmap实时计算时候\t" + map.toString());//-------------------
                    }else{//不存在该用户Id
                        Hvalue hvalue = new Hvalue().setCunt(1).setShowName(showName);
                        map.put(userId, hvalue);
                        ++showCount[showName];
                        ++onlineNum;//当前时间在线用户人数+1
                        System.out.println(date + "\tmap实时计算时候\t" + map.toString());//-------------------
                    }

                }else{
                    if(lvalue.getDate().compareTo(date) < 0){
                        break;
                    }
                }

            }
        }

        //减去date时间前50s(5个周期)起始时间内用户发送请求
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(date));
        calendar.add(Calendar.SECOND, -50);//计算5个心跳周期的起始时间
        String start = df.format(calendar.getTime());
        System.out.println("减去前50秒后的时间" + start);//-----------------------

        synchronized (this){
            while(true){
                if(list.size()!=0  && (list.getLast().getDate()).equals(start)) {
                    int userId = list.getLast().getUserId();

                    if(map.containsKey(userId)) {//已存在该用户Id
                        if(map.get(userId).getCunt() - 1 > 0){
                            map.get(userId).setCunt( map.get(userId).getCunt() - 1 );//Id号为userId的用户在线期间发送请求总数-1
                            System.out.println(date + "\tmap实时计算时候\t" + map.toString());//-------------------
                        }else{//等于0,删除该条记录
                            --showCount[map.get(userId).getShowName()];
                            --onlineNum;
                            map.remove(userId);
                            System.out.println(date + "\tmap实时计算时候\t" + map.toString());//-------------------
                        }
                    }
                    list.removeLast();
                    System.out.println(date + "\tlist初始化时候\t" + list.size() +  "\t" + list.toString());//-------------
                }else{
                    break;
                }
            }
        }

        model.addAttribute("date", date);
        model.addAttribute("onlineNum", onlineNum);

        //实时在线人数存入数据库中
        OnlineNumber onlineNumber = new OnlineNumber().setDate(date).setUserNumber(onlineNum);
        onlineNumberService.addWatchTVNum(onlineNumber);

        //每个节目有多少人在看参入数据库
        for(int i = 0; i < showCount.length; i ++){
            TVshowsOnlineViewer viewer = new TVshowsOnlineViewer(date, i, showCount[i]);
            tVshowsOnlineViewerService.addTVshowsOnlineViewer(viewer);
        }

        return "nowWatchTVNum";
    }
}
