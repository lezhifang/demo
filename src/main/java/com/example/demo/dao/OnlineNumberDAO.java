package com.example.demo.dao;

import com.example.demo.model.OnlineNumber;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/**
 * Created by LZF on 2017/7/18.
 */
@Mapper
public interface OnlineNumberDAO {

    // 注意空格
    String TABLE_NAME = " onlineNumber ";
    String INSERT_FIELDS = " date, user_number ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{date},#{userNumber})"})
    int addWatchTVNum(OnlineNumber watchTVNum);

    @Select({"select ",INSERT_FIELDS,"from",TABLE_NAME,"where date=#{date}"})
    OnlineNumber selectWatchTVNumByTime(String date);

}
