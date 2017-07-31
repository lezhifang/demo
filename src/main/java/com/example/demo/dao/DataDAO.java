package com.example.demo.dao;

import com.example.demo.model.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by LZF on 2017/7/15.
 */
@Mapper
public interface DataDAO {
    // 注意空格
    String TABLE_NAME = " data ";
    String INSERT_FIELDS = " date, user_behavior, show_name ";
    String SELECT_FIELDS = " user_id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", SELECT_FIELDS,
            ") values (#{userId},#{date},#{userBehavior},#{showName})"})
    int addData(Data data);

//    @Select({"select ", INSERT_FIELDS,",count(user_id) as user_id from ", TABLE_NAME, "group by show_name"})
//    List<Data> getEveryshowNameCount();
    @Select({"select user_id from ", TABLE_NAME, "where date > #{date}"})
    List<Integer> selectUserIdBydate(String date);
}
