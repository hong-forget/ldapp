package com.ld.ldapp.mapper.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface NewsMapper {


    @Select("SELECT * FROM `b_news`")
    List<Map> find(JSONObject param);
}
