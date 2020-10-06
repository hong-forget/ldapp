package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AreaMapper {


    @Insert("INSERT INTO `m_area` (`parent_id`, `name`) VALUES (#{parentId}, #{name})")
    Integer add(JSONObject param);

    @Select("Select id,parent_id parentId,name from m_area where parent_id=#{id}")
    List<Map> findChildren(Integer id);

    @Delete("DELETE FROM `m_area` WHERE (`id`=#{id})")
    Integer del(Integer id);
}
