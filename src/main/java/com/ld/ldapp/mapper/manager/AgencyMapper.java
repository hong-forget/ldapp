package com.ld.ldapp.mapper.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AgencyMapper {

    @Select("SELECT * FROM `stores`")
    List<Map> list(JSONObject param);

    @Insert("INSERT INTO `stores` (address,`storecode`, `storename`, `agent_id`, `angecy_name`, `director_id`, `director_name`, `director_phone`, `emps`, `status`, `angecy_code`) VALUES (#{address},#{storecode}, #{storename}, #{agent_id}, #{angecy_name}, #{director_id}, #{director_name}, #{director_phone}, #{emps}, #{status}, #{angecy_code})")
    Integer add(JSONObject param);

    @Update("<script>UPDATE `stores` " +
            "<trim prefix=\"SET\" suffixOverrides=\",\" suffix=\" WHERE (`id`=#{id}) \">" +
            "<if test=\" storecode!=null \"> `storecode`=#{storecode}, </if>" +
            "<if test=\" storename!=null \"> `storename`=#{storename}, </if>" +
            "<if test=\" agent_id!=null \"> `agent_id`=#{agent_id}, </if>" +
            "<if test=\" angecy_name!=null \"> `angecy_name`=#{angecy_name}, </if>" +
            "<if test=\" director_id!=null \"> `director_id`=#{director_id}, </if>" +
            "<if test=\" director_name!=null \"> `director_name`=#{director_name}, </if>" +
            "<if test=\" director_phone!=null \"> `director_phone`=#{director_phone}, </if>" +
            "<if test=\" status!=null \"> `status`=#{status}, </if>" +
            "<if test=\" angecy_code!=null \"> `angecy_code`=#{angecy_code}, </if>" +
            "<if test=\" address!=null \"> `address`=#{address}, </if>" +
            "</trim></script>")
    Integer mdify(JSONObject param);

    @Select("SELECT * FROM `stores` WHERE id=#{id} LIMIT 0, 1")
    Map read(JSONObject param);

    @Select("select t.records,u.* from  \n" +
            "`user` u LEFT JOIN \n" +
            "(select c.userid,c.user_name,count(1) records from `client` c where c.store_code=#{storeCode} GROUP BY c.userid,c.user_name)t\n" +
            "\n" +
            " on u.id=t.userid where u.storecode=#{storeCode}\n")
    List<Map> realtor(JSONObject param);

    @Select("<script>SELECT count(1) `count` FROM client c WHERE 1=1" +

            "<if test=\" type==1 \"> AND record=1 </if>\n" +
            "<if test=\" type==2 \"> AND showed=1\n</if>" +
            "<if test=\" type==3 \"> AND deal=1</if>" +

            "<if test=\" cycle==2 \"> AND date_format(createdate,'%Y-%m')=date_format(now(),'%Y-%m') </if>" +
            "<if test=\" cycle==3 \"> AND YEARWEEK(date_format(c.createdate,'%Y-%m-%d')) = YEARWEEK(now())</if>" +
            "<if test=\" cycle==4 \"> AND to_days(c.createdate)=to_days(now())</if>" +
            "<if test=\" cycle==5 \"> AND to_days(c.createdate)+1=to_days(now())</if>" +
            "</script>")
    Map statistic(JSONObject param);
}
