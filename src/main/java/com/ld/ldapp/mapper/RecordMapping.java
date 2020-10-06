package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RecordMapping {
    @Select("<script>SELECT c.*,s.storename,s.angecy_name from client c LEFT JOIN stores s on c.store_code=s.storecode where 1=1" +
            "<if test=\" type==1 \"> AND c.record=1 </if>\n" +
            "<if test=\" type==2 \"> AND c.showed=1\n</if>" +
            "<if test=\" type==3 \"> AND c.deal=1</if>" +
            "</script>")
    List<Map> list(JSONObject param);
}
