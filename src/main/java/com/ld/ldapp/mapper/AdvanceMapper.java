package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface AdvanceMapper {

    @Select("select count(1) from (\n" +
            "select count(0),CONCAT_WS('-',a.phone,a.building_id,a.type) from cus_adv a GROUP BY CONCAT_WS('-',a.phone,a.building_id,a.type)\n" +
            ")t")
    Integer advStatistics();

    @Select("<script> select a.id,a.type,a.name,a.phone,a.user_id userId,a.building_id buildingId,a.`status`,a.del from cus_adv a\n" +
            "where del=1 \n" +
            "<if test=\" buildingId!=null \"> and a.building_id=#{buildingId} </if>" +
            "<if test=\" status!=null \"> and a.status=#{status} </if>" +
            "</script>")
    List<Map> advList(JSONObject param);

    @Update("UPDATE `cus_adv` SET `status`=#{status} where id=#{id}")
    Integer updateStatus(Integer status, Integer id);

    @Update("UPDATE `cus_adv` SET `del`=1 where id=#{id}")
    Integer delStatus(Integer id);
}
