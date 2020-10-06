package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface LogMapper {

    @Insert("INSERT INTO `w_request_log` (`servlet_path`, `u_id`, `u_type`) VALUES (#{servletPath}, #{id}, #{uType})")
    void addReqestLog(JSONObject info);

    @Select("select \n" +
            "(select COUNT(1) from (\n" +
            "SELECT CONCAT_WS('-',r.u_id,r.u_type),COUNT(1) from w_request_log r \n" +
            "where to_days(r.c_date)=to_days(now()) GROUP BY CONCAT_WS('-',r.u_id,r.u_type))t1) todays,\n" +
            "\n" +
            "(select COUNT(1) from (\n" +
            "SELECT CONCAT_WS('-',r.u_id,r.u_type),COUNT(1) from w_request_log r \n" +
            "where to_days(r.c_date)+1=to_days(now()) GROUP BY CONCAT_WS('-',r.u_id,r.u_type))t2) yestodays,\n" +
            "\n" +
            "(select COUNT(1) from (\n" +
            "SELECT CONCAT_WS('-',r.u_id,r.u_type),COUNT(1) from w_request_log r \n" +
            "where to_days(r.c_date)+30>=to_days(now()) GROUP BY CONCAT_WS('-',r.u_id,r.u_type))t3) monthly")
    JSONObject statistics();
}
