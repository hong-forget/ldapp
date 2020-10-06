package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.*;
import com.ld.ldapp.domain.Property;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BuildingMapper {

    @Select("select * from building")
    List<Building> findAll();

    @Select("SELECT b.name,b.id,b.commissions,b.loct_id loctId from building b")
    List<Map> getNames();

    @Select("select * from building where id=#{id}")
    Building findOneById(@Param("id") Integer id);

    @Select("<script> " +
            "SELECT *" +
            " FROM " +
            " (SELECT b.*,s.`status` saleStatus,s.phone_style from building b INNER JOIN b_sale_info s on b.id=s.b_id)t " +
            " WHERE saleStatus=1 " +
            "<if test=\" name!=null \"> AND NAME LIKE #{name} </if>" +
            "<if test=\" lowPrice!=null \"> AND price BETWEEN #{lowPrice} AND #{highPrice} </if>" +
            "<if test=\" status!=null \"> AND status = #{status} </if>" +
            "<if test=\" type!=null \"> AND type = #{type} </if>" +
            "<if test=\" area!=null \"> AND area = #{area} </if>" +
            "<if test=\" sort==2 \"> order by id desc </if> " +
            "<if test=\" sort==3 \"> order by price </if> " +
            "<if test=\" sort==4 \"> order by id desc </if> " +
            "</script>")
    List<Building> findList1(BuildingParam buildingParam);


    @Select("select * from house_style where building_id=#{buildingId}")
    List<HouseStyle> getHouseStyleList(Integer buildingId);

    @Select("select * from property where building_id=#{buildingId} LIMIT 0, 1")
    Property getPropertyByBuildingId(Integer buildingId);

    @Insert("INSERT INTO `cus_adv` (`type`, `name`, `phone`, `user_id`, `building_id`) VALUES (#{type}, #{name}, #{phone}, #{userId}, #{buildingId})")
    Integer addAdvance(JSONObject param);

    @Select("select count(1) from cus_adv where building_id=#{buildingId} and type=#{type}")
    Integer countAdv(JSONObject param);

    @Insert("insert into mapbub(content,longitude,latitude,type) VALUES (#{content},#{longitude},#{latitude},#{type})")
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=int.class)
    Integer addBub(JSONObject param);

    @Insert("insert into r_bub_tar(bub_id,tar_id) VALUES (#{id},#{tarId})")
    Integer addRBub(JSONObject param);

    @Select("SELECT b.*,r.tar_id as tarId from mapbub b INNER JOIN r_bub_tar r on b.id=r.bub_id where r.bub_id=#{tarId} and b.type=#{type}")
    List<Map> getBubs(JSONObject param);

    @Update("UPDATE building\n" +
            "SET loct_id = #{loctId} \n" +
            " where id = #{bId}")
    Integer bindLvp(Integer loctId, Integer bId);

    @Select("<script> select city,title,ad,DATE_FORMAT(`cdate`,'%Y-%m-%d %H:%i:%s') cdate,DATE_FORMAT(`udate`,'%Y-%m-%d %H:%i:%s') udate,id,`b_id` bId, `b_name` bName, `context`, `auth`, DATE_FORMAT(`date`,'%Y-%m-%d %H:%i:%s') date, `img`,u_id uId from `b_news` WHERE 1=1 " +
            "<if test=\" bId!=null \"> AND b_id = #{bId} </if>" +
            "<if test=\" uId!=null \"> AND u_id = #{uId} </if>" +
            "<if test=\" cdt!=null \"> AND concat_ws('-',context,`auth`,`b_name`) like #{cdt} </if>" +
            "</script> ")
    List<JSONObject> getNews(JSONObject param);

    @Insert("INSERT INTO `b_news` (`city`,`title`,`b_id`, `b_name`, `context`, `auth`, `date`, `img`, `u_id`) VALUES (#{city},#{title},#{bId},#{bName},#{context},#{auth},#{date},#{img},#{uId})")
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=int.class)
    Integer addNews(JSONObject param);

    @Update("<script>UPDATE `b_news` SET " +
            "<if test=\" bId!=null \">  b_id = #{bId} ,</if>" +
            "<if test=\" bName!=null \">  b_name = #{bName} ,</if>" +
            "<if test=\" context!=null \">  context = #{context} ,</if>" +
            "<if test=\" date!=null \">  date = #{date} ,</if>" +
            "<if test=\" img!=null \">  img = #{img} ,</if>" +
            "<if test=\" uId!=null \">  u_id = #{uId} ,</if>" +
            "<if test=\" city!=null \">  city = #{city} ,</if>" +
            "<if test=\" ad!=null \">  ad = #{ad} ,</if>" +

            "`udate`=CURRENT_TIMESTAMP WHERE (`id`=#{id})</script>")
    Integer updateNew(JSONObject param);

    @Select("select id,city,title,ad,`b_id` bId, `b_name` bName, `context`, `auth`, DATE_FORMAT(`date`,'%Y-%m-%d %H:%i:%s') date,DATE_FORMAT(`udate`,'%Y-%m-%d %H:%i:%s') udate,DATE_FORMAT(`cdate`,'%Y-%m-%d %H:%i:%s') cdate, `img` from `b_news` where id=#{id}")
    JSONObject findOneNewsById(Integer id);


}
