package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TouristMapper {

    @Select("SELECT * from w_tourist where tourist_key=#{touristKey} ORDER BY id DESC LIMIT 0,1")
    JSONObject findByUnique(String touristKey);

    @Insert("INSERT INTO `w_tourist` (`tourist_key`) VALUES (#{touristKey})")
    Integer addTourist(String touristKey);
}
