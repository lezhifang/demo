package com.example.demo.service;

import com.example.demo.dao.WatchTVNumDAO;
import com.example.demo.model.WatchTVNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LZF on 2017/7/18.
 */
@Service
public class WatchTVNumService {
    @Autowired
    private WatchTVNumDAO watchTVNumDAO;

    public int addWatchTVNum(WatchTVNum watchTVNum){
        return watchTVNumDAO.addWatchTVNum(watchTVNum);
    }

    public WatchTVNum selectWatchTVNumByTime(String time){
        return watchTVNumDAO.selectWatchTVNumByTime(time);
    }
}
