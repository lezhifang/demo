package com.example.demo.controller;

import com.example.demo.model.Data;
import com.example.demo.service.DataService;
import com.example.demo.tools.JedisAdapter;
import com.example.demo.tools.RedisKeyTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LZF on 2017/7/7.
 */
@Controller
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataService dataService;
    @Autowired
    private JedisAdapter jedisAdapter;

    @RequestMapping(path = {"/addData"}, method = {RequestMethod.GET})
    @ResponseBody
    public String addData(Model model,
                           @RequestParam(value = "userId") int userId,
                           @RequestParam(value = "date")String date,
                           @RequestParam(value = "userBehavior") int userBehavior,
                           @RequestParam(value = "showName") int showName) {

        //使用List存放每秒钟内发送请求信息的用户id，设置添加过期时间，时间至少大于5个心跳周期
        String key = RedisKeyTool.getEverymillsSendrequestUserid(date);
        jedisAdapter.lpush(key, String.valueOf(userId));
        jedisAdapter.expire(key, 60 * 60 * 24);//设置有效期为24小时

        //存放到MySQL数据库中
        Data data = new Data().setUserId(userId).setDate(date).setShowName(showName).setUserBehavior(userBehavior);
        dataService.addData(data);
        return data.toString() + "\t添加成功";
    }
}
