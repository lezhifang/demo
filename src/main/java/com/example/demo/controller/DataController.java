package com.example.demo.controller;

import com.example.demo.model.Data;
import com.example.demo.model.Lvalue;
import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.LinkedList;
/**
 * Created by LZF on 2017/7/7.
 */
@Controller
public class DataController {
    static LinkedList<Lvalue> list = new LinkedList<Lvalue>();//使用List存放每秒钟内发送请求信息的用户id和shaowName

    @Autowired
    private DataService dataService;

    @RequestMapping(path = {"/addData"}, method = {RequestMethod.GET})
    @ResponseBody
    public String addData(Model model,
                           @RequestParam(value = "userId") int userId,
                           @RequestParam(value = "date")String date,
                           @RequestParam(value = "userBehavior") int userBehavior,
                           @RequestParam(value = "showName") int showName) {

        Lvalue usd = new Lvalue(userId, showName, date);
        synchronized (list){
            list.addFirst(usd);
            System.out.println("发送请求：" + userId + "\t" + date + "\t" + list.size() );
            System.out.println(date + "\tlist初始化时候\t" + list.size() +  "\t" + list.toString());//--------
        }

        //存放到MySQL数据库中
        Data data = new Data().setUserId(userId).setDate(date).setShowName(showName).setUserBehavior(userBehavior);
        dataService.addData(data);
        return data.toString() + "\t添加成功";
    }
}
