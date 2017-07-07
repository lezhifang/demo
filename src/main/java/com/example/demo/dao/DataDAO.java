package com.example.demo.dao;

import com.example.demo.model.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by LZF on 2017/7/7.
 */

@Mapper
public interface DataDAO {
    // 注意空格
    String TABLE_NAME = " data ";
    String INSERT_FIELDS = " date, user_behavior, show_name ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{date},#{userBehavior},#{showName})"})
    int addData(Data data);

    @Select({"select ",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    Data selectById(int id);
}
