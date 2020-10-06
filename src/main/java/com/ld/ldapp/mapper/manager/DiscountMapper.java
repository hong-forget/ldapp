package com.ld.ldapp.mapper.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface DiscountMapper {

    @Insert("INSERT INTO `b_discount` (`title`, `content`, `explain`, `quota`,building_id) VALUES (#{title}, #{content}, #{explain}, #{quota},#{buildingId})")
    Integer add(JSONObject param);

    @Update("UPDATE `building` SET `discounts`=#{title} WHERE (`id`=#{buildingId})")
    Integer updateBuilding(JSONObject param);

    @Update("UPDATE `b_discount` SET `title`=#{title}, `content`=#{content}, `explain`=#{explain}, `quota`=#{quota} WHERE (`id`=#{id})")
    Integer update(JSONObject param);

    @Select("SELECT `id`, `title`, `content`, `explain`, `quota`, `building_id` buildingId FROM `b_discount` WHERE `building_id`=#{buildingId} LIMIT 0, 1")
    Map find(JSONObject param);
}
