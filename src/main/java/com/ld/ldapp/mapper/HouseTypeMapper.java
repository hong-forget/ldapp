package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface HouseTypeMapper {

    @Select("select id,DATE_FORMAT(c_date,'%Y-%m-%d %H:%i:%s') cdate,`area`, `feature`, `feature_explain` featureExplain,`style_name` styleName, `house_area` houseArea, `unit_price` unitPrice,`total_price` totalPrice,`img`, `building_id` buildingId, `orientation`, `onsell` from house_style where `building_id`=#{buildingId}")
    List<Map> find(JSONObject param);

    @Insert("INSERT INTO `house_style` " +
            "(`area`, `feature`, `feature_explain`,`style_name`, `house_area`, `unit_price`,`total_price`,`img`, `building_id`, `orientation`, `onsell`) VALUES " +
            "(#{area}, #{feature}, #{featureExplain},#{styleName}, #{houseArea}, #{unitPrice}, #{totalPrice},#{img}, #{buildingId}, #{orientation}, #{onsell})")
    Integer add(JSONObject param);

    @Update("<script>UPDATE `house_style` SET " +
            "<if test=\" area!=null \"> `area`=#{area}, </if>" +
            "<if test=\" feature!=null \"> `feature`=#{feature}, </if>" +
            "<if test=\" featureExplain!=null \"> `feature_explain`=#{featureExplain}, </if>" +
            "<if test=\" styleName!=null \"> `style_name`=#{styleName}, </if>" +
            "<if test=\" houseArea!=null \"> `house_area`=#{houseArea}, </if>" +
            "<if test=\" unitPrice!=null \"> `unit_price`=#{unitPrice}, </if>" +
            "<if test=\" totalPrice!=null \"> `total_price`=#{totalPrice}, </if>" +
            "<if test=\" img!=null \"> `img`=#{img}, </if>" +
            "<if test=\" orientation!=null \"> `orientation`=#{orientation}, </if>" +
            "<if test=\" onsell!=null \"> `onsell`=#{onsell}, </if>" +
            "`building_id`=#{buildingId} where id=#{id}</script>")
    Integer udpate(JSONObject param);

    @Delete("DELETE FROM `house_style` WHERE (`id`=#{id})")
    Integer del(JSONObject param);
}
