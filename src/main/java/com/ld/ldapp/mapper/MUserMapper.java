package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface MUserMapper {

    @Select("<script>SELECT id,role,phone,name,phone400,storecode,chathead,satisfaction,servers,star,age,exp_year,svc_area,lvb FROM `user` " +
            "<trim prefix=\"WHERE\" prefixOverrides=\"AND\" >" +
            "<if test=\" role!=null \"> AND role=#{role}</if>" +
            "<if test=\" storecode!=null \"> AND storecode=#{storecode}</if>" +
            "</trim></script>")
    List<Map> realtor(JSONObject param);

    @Insert("INSERT INTO `b_realtor` (`user_id`, `building_id`, `index`) VALUES (#{userId}, #{buildingId}, #{index})")
    Integer realtorAdd(JSONObject param);

    @Select("SELECT IFNULL(MAX(`index`),0) `index`,building_id buildingId FROM `b_realtor` where building_id=#{buildingId}")
    Integer getIndex(JSONObject param);

    @Select("select b.id,u.role,b.realtor,b.user_id userId,b.building_id buildingId,b.index,u.`name`,u.phone,u.phone400 from b_realtor b \n" +
            "INNER JOIN `user` u on u.id=b.user_id\n" +
            "where b.building_id=#{buildingId} ORDER BY `index`")
    List<Map> realtorFind(JSONObject param);

    @Update("<script>UPDATE `b_realtor` SET <if test=\" operat==1 \"> `realtor`=1 </if><if test=\" operat==2 \"> `realtor`=0 </if> WHERE (`id`=#{id})</script>")
    Integer realtorUpdateStatus(JSONObject param);

    @Update("UPDATE `b_realtor` SET `index`=#{orgIndex} WHERE (`id`=#{id})")
    Integer realtorUpdateIndex(JSONObject param);

    @Update("UPDATE `b_realtor` SET `index`=#{index} WHERE (`building_id`=#{buildingId} AND `index`=#{orgIndex})")
    Integer realtorUpdateOrgIndex(JSONObject param);

    @Delete("DELETE FROM `b_realtor` WHERE (`id`=#{id})")
    Integer realtorDel(JSONObject param);

    @Select("select t.*,b.`name` bName FROM (\n" +
            "SELECT r.*,u.`name` FROM `u_role` r INNER JOIN `user` u ON r.user_id=u.id where r.role=#{role})t\n" +
            "LEFT JOIN building b ON b.id=t.b_id\n" +
            "\n")
    List<Map> agentList(JSONObject param);

    @Update("<script>UPDATE `user` SET " +
            "<if test=\" status==1 \">  role=(select role from u_role where id=#{id})</if>" +
            "<if test=\" status==2 \">  role=0</if>" +
            " WHERE (`id`=(select user_id from u_role where id=#{id}))</script>")
    Integer agentSet(JSONObject param);

    @Update("<script>UPDATE `u_role` SET `status`= #{status} WHERE (`id`=#{id})</script>")
    Integer agentSetRole(JSONObject param);

    @Select(" SELECT COUNT(1) FROM `m_user` u where u.username=#{username} AND u.`password`=#{password}")
    Integer login(JSONObject param);

    @Update("<script>UPDATE `m_user` u SET `password`= #{newpwd} WHERE (u.username=#{username} AND u.`password`=#{password})</script>")
    Integer reSetPwd(JSONObject param);
}
