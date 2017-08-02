package com.example.demo.service;

import com.example.demo.dao.TVshowsOnlineViewerDAO;
import com.example.demo.model.TVshowsOnlineViewer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LZF on 2017/8/2.
 */
public class TVshowsOnlineViewerService {
    @Autowired
    TVshowsOnlineViewerDAO tvshowsOnlineViewerDAO;

    public int addTVshowsOnlineViewer(TVshowsOnlineViewer tvshowsOnlineViewer){
        return tvshowsOnlineViewerDAO.addTVshowsOnlineViewer(tvshowsOnlineViewer);
    }

    public int selectCountByDateAndShowName(String date, int showName){
        return tvshowsOnlineViewerDAO.selectCountByDateAndShowName(date, showName);
    }
}
