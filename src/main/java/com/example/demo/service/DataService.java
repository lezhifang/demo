package com.example.demo.service;

import com.example.demo.dao.DataDAO;
import com.example.demo.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LZF on 2017/7/7.
 */
@Service
public class DataService {
    @Autowired
    public DataDAO dataDAO;

    public int addData(Data data){
        return dataDAO.addData(data);
    }

    public List<Data> selectUserIdBydate(String start, String end){
        return dataDAO.selectUserIdBydate(start, end);
    }

}
