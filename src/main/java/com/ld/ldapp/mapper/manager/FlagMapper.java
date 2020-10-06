package com.ld.ldapp.mapper.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Update;

public interface FlagMapper {

    @Update("<script>UPDATE `building` " +
            "<trim prefix=\"SET\" suffixOverrides=\",\" suffix=\" WHERE (`id`=#{id}) \">" +
            "<if test=\" hot!=null \"> `hot`=#{hot}, </if>" +
            "<if test=\" top!=null \"> `top`=#{top}, </if>" +
            "</trim></script>")
    Integer flagSet(JSONObject param);
}
