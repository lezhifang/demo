package com.example.demo.controller;

import com.example.demo.model.Data;
import com.example.demo.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by LZF on 2017/7/7.
 */
@Controller
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    DataService dataService;

    @RequestMapping(path = {"/addData"}, method = {RequestMethod.GET})
    public String register(Model model,
                          //@RequestParam(value = "id") int id,
                           @RequestParam(value = "date")String date,
                           @RequestParam(value = "userBehavior") int userBehavior,
                           @RequestParam(value = "showName", defaultValue = "null") String showName) {

        Data data = new Data();
        data.setDate(date);
        data.setShowName(showName);
        data.setUserBehavior(userBehavior);
        dataService.addData(data);
        model.addAttribute("data", data);
        return "result";
    }

}
