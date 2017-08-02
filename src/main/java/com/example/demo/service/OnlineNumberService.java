package com.example.demo.service;

import com.example.demo.dao.OnlineNumberDAO;
import com.example.demo.model.OnlineNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LZF on 2017/7/18.
 */
@Service
public class OnlineNumberService {
    @Autowired
    private OnlineNumberDAO watchTVNumDAO;

    public int addWatchTVNum(OnlineNumber watchTVNum){
        return watchTVNumDAO.addWatchTVNum(watchTVNum);
    }

    public OnlineNumber selectWatchTVNumByTime(String time){
        return watchTVNumDAO.selectWatchTVNumByTime(time);
    }
}
