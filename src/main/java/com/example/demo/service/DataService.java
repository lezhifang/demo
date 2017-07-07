package com.example.demo.service;

import com.example.demo.dao.DataDAO;
import com.example.demo.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LZF on 2017/7/7.
 */
@Service
public class DataService {
    @Autowired
    private DataDAO dataDAO;


    public int addData(Data data){
        return dataDAO.addData(data);
    }

    public Data selectById(int id){
        return dataDAO.selectById(id);
    }
}
