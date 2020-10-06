package com.ld.ldapp.mapper.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface SaleInfoMapper {

    @Select("select t.*,c.commissions from \n" +
            "(select s.*,b.address,b.id bId,b.`name` from b_sale_Info s RIGHT JOIN building b on s.b_id=b.id)t\n" +
            " LEFT JOIN \n" +
            "( SELECT COUNT(1) commissions,b_id FROM b_commission GROUP BY b_id)c \n" +
            "ON c.b_id=t.b_id")
    List<Map> list(JSONObject param);

    @Insert("INSERT INTO `b_sale_Info` " +
            "(`b_id`, `phone_style`, `time_out`, `validity`, `status`, `record_explan`) VALUES " +
            "(#{b_id}, #{phone_style}, #{time_out}, #{validity}, #{status}, #{record_explan})")
    Integer add(JSONObject param);

    @Update("<script> UPDATE `b_sale_Info`" +
            "<trim prefix=\"SET\" suffixOverrides=\",\" suffix=\" WHERE (`id`=#{id}) \">" +
            "<if test=\" b_id!=null \"> `b_id`=#{b_id}, </if>" +
            "<if test=\" phone_style!=null \"> `phone_style`=#{phone_style}, </if>" +
            "<if test=\" time_out!=null \"> `time_out`=#{time_out}, </if>" +
            "<if test=\" validity!=null \"> `validity`=#{validity}, </if>" +
            "<if test=\" status!=null \"> `status`=#{status}, </if>" +
            "<if test=\" record_explan!=null \"> `record_explan`=#{record_explan}, </if>" +
            "</trim></script>")
    Integer modify(JSONObject param);
}
