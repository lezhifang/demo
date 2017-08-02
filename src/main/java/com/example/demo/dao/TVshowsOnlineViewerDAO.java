package com.example.demo.dao;

import com.example.demo.model.TVshowsOnlineViewer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by LZF on 2017/8/2.
 */
@Mapper
public interface TVshowsOnlineViewerDAO {
    // 注意空格
    String TABLE_NAME = " TVshowsOnlineViewer ";
    String INSERT_FIELDS = " date, show_name, count ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{date},#{showName},#{count})"})
    int addTVshowsOnlineViewer(TVshowsOnlineViewer tvshowsOnlineViewer);

    @Select({"select count "," from ",TABLE_NAME," where date=#{date} and show_name=#{showName}"})
    int selectCountByDateAndShowName(String date, int showName);
}
