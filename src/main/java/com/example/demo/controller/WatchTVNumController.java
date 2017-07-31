package com.example.demo.controller;

import com.example.demo.dao.DataDAO;
import com.example.demo.model.WatchTVNum;
import com.example.demo.service.DataService;
import com.example.demo.service.WatchTVNumService;
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
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by LZF on 2017/7/18.
 */
@Controller
public class WatchTVNumController {
    private static final Logger logger = LoggerFactory.getLogger(WatchTVNumController.class);

    @Autowired
    private WatchTVNumService watchTVNumService;
    @Autowired
    private JedisAdapter jedisAdapter;
    @Autowired
    private DataService dataService;

    @RequestMapping(path = {"/initOnlineUserNum"}, method = {RequestMethod.GET})
    @ResponseBody
    public String initOnlineUserNum(Model model, @RequestParam(value = "date") String date)  {
        //将MySQL数据库中当前时间前5个心跳周期的数据添加到HashMap中
        String hkey = RedisKeyTool.getNowWatchTVNumKey();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -50);//计算5个心跳周期的起始时间
        String startDate = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
        List<Integer> userIdList = dataService.selectUserIdBydate(startDate);
        Iterator<Integer> it = userIdList.iterator();
        while(it.hasNext()){
            String userIdStr = String.valueOf(it.next());
            if(jedisAdapter.hexists(hkey, userIdStr)){//已存在该用户Id
                String count = jedisAdapter.hget(hkey, userIdStr);//获取用户Id在线期间发送的请求数（字符串类型）
                jedisAdapter.hset(hkey, userIdStr, String.valueOf(Integer.parseInt(count) + 1));//在线期间发送请求数+1
            }else{//不存在该用户Id   添加并将在线人数加1    可以考虑一下两条语句使用事物一起执行
                jedisAdapter.hset(hkey, userIdStr, "1");
            }
        }

        String key = RedisKeyTool.getOnlineUserNum();
        long len = jedisAdapter.hlen(hkey);
        jedisAdapter.set(key, String.valueOf(len));//初始化当前时间看电视人数
        return "初始化成功";
    }

    @RequestMapping(path = {"/nowWatchTVNum"}, method = {RequestMethod.GET})
    public String nowWatchTVNum(Model model, @RequestParam(value = "date") String date)  {
        String hkey = RedisKeyTool.getNowWatchTVNumKey();
        String key = RedisKeyTool.getOnlineUserNum();//存放当前时间看电视人数
        String lkey_current = RedisKeyTool.getEverymillsSendrequestUserid(date);

        //使用Redis中HashMap做缓存 存放用户在线期间发送的总请求数 用于实时统计当前看电视人数计算；
        //添加date时间用户请求
        int len_current = (int)jedisAdapter.llen(lkey_current);
        List<String> userIdList = jedisAdapter.lrange(lkey_current, 0, len_current);
        Iterator<String> it = userIdList.iterator();
        while(it.hasNext()){
            String userIdStr = it.next();
            if(jedisAdapter.hexists(hkey, userIdStr)){//已存在该用户Id
                String count = jedisAdapter.hget(hkey, userIdStr);//获取用户Id在线期间发送的请求数（字符串类型）
                jedisAdapter.hset(hkey, userIdStr, String.valueOf(Integer.parseInt(count) + 1));//在线期间发送请求数+1
            }else{//不存在该用户Id   添加并将在线人数加1    可以考虑一下两条语句使用事物一起执行
                jedisAdapter.hset(hkey, userIdStr, "1");
                jedisAdapter.incr(key);
            }
        }

        //减去date时间前50s(5个周期)起始时间内用户发送请求
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -50);//计算5个心跳周期的起始时间
        String start = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
        String lkey_before = RedisKeyTool.getEverymillsSendrequestUserid(start);
        if(jedisAdapter.exists(lkey_before)){
            int len_before = (int)jedisAdapter.llen(lkey_before);
            List<String> userIdList_before = jedisAdapter.lrange(lkey_before, 0, len_before);
            Iterator<String> it_before = userIdList_before.iterator();
            while(it_before.hasNext()){
                String userIdStr = it_before.next();
                if(jedisAdapter.hexists(hkey, userIdStr)){//已存在该用户Id
                    String cunt = jedisAdapter.hget(hkey, userIdStr);//获取用户Id在线期间发送的请求数（字符串类型）

                    if(Integer.parseInt(cunt) - 1 > 0){
                        jedisAdapter.hset(hkey, userIdStr, String.valueOf(Integer.parseInt(cunt) - 1));//在线期间发送请求数-1
                    }else{
                        jedisAdapter.hdel(hkey, userIdStr);
                        jedisAdapter.decr(key);
                    }
                }
            }
        }

        int userNumber = Integer.parseInt(jedisAdapter.get(key));
        model.addAttribute("date", date);
        model.addAttribute("userNumber", userNumber);
        model.addAttribute("start", start);

        //存入数据库中
        WatchTVNum watchTVNum = new WatchTVNum().setDate(date).setUserNumber(userNumber);
        watchTVNumService.addWatchTVNum(watchTVNum);

        return "nowWatchTVNum";
    }
}