package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.Adprepare;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CusMapper {

    @Select("select * from adprepare order by level desc")
    List<Adprepare> findAll();

    @Select("select id,cus_id,type,remark,DATE_FORMAT(time,'%Y-%m-%d %H:%i:%s') time from cus_tuh where cus_id = #{cusId} ")
    List<Map> getCusTuhs(Integer cusId);

    @Select("select * from Prepare where id=#{id}")
    Adprepare findOneById(@Param("id") Integer id);

    @Insert("INSERT INTO `customer` (`name`, `gender`, `phone`, `phone2`, `level`, `need_type`, `care_area`, `care_building_id`, `next_follow_time`, `user_id`,remark,broker_id)" +
            " VALUES (#{name}, #{gender},#{phone}, #{phone2},#{level}, #{needType}, #{careArea}, #{careBuildingId}, #{nextFollowTime}, #{userId},#{remark},#{brokerId}) ")
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=int.class)
    Integer addCus(Map param);

//    @Options

    @Insert("INSERT INTO r_cus_bdg (cus_id,bdg_id) VALUE (#{cId},#{bId})")
    Integer addRCusBdg(Map param);

    @Insert("INSERT INTO `cus_tuh` (`cus_id`, `type`, `remark`) VALUES (#{cusId}, #{type}, #{remark})")
    Integer addCusTuh(Map param);

    @Update("<script> UPDATE customer c\n" +
            "SET c.pre_follow_time = CURRENT_TIMESTAMP\n" +
            "<if test=\" nextFollowTime!=null \">, c.next_follow_time = #{nextFollowTime}\n</if>" +
            "<if test=\" name!=null \">, c.`name`=#{name}\n</if>" +
            "<if test=\" gender!=null \">, c.`gender`=#{gender}\n</if>" +
            "<if test=\" phone!=null \">,  c.phone=#{phone}\n</if>" +
            "<if test=\" level!=null \">,  c.level=#{level}\n</if>" +
            "<if test=\" needType!=null \">,  c.need_type=#{needType}\n</if>" +
            "<if test=\" careArea!=null \">,  c.care_area= #{careArea}\n</if>" +
            "<if test=\" careBuildingId!=null \">,  c.care_building_id=#{careBuildingId}\n</if>" +
            "WHERE\n" +
            "\tc.id = #{cusId} </script>")
    Integer updateCus(Map param);

    @Select("SELECT\n" +
            "\tname,\n" +
            "\tphone,\n" +
            "\tphone2,\n" +
            "\tlevel,\n" +
            "\tstar,\n" +
            "\tgender,\n" +
            "\tneed_type needType,\n" +
            "\tcare_area careArea,\n" +
            "\tcare_building_id careBuildingId,\n" +
            "\tuser_id userId,\n" +
            "\tremark\n" +
            "FROM\n" +
            "\tcustomer where id=#{id}")
    JSONObject getDtl(Integer id);



//    @Select("<script> SELECT\n" +
//            "\tc.id,c.name name,c.`level`,c.phone, b.`name` buildingName,c.pre_follow_time preFollowTime\n" +
//            "FROM\n" +
//            "\tcustomer c \n" +
//            "INNER JOIN building b ON c.care_building_id = b.id\n" +
//            "where broker_id=#{userId} " +
//            "<if test=\" star==1 \">  and c.star=1 </if>" +
//            "<if test=\" level!=null \"> and c.`level`=#{level} </if>\n" +
//            "<if test=\" search!=null \"> AND CONCAT(\n" +
//            "\t\tIFNULL(b.`name`, \"_\"),\n" +
//            "\t\tIFNULL(c.phone, \"_\"),\n" +
//            "\t\tIFNULL(c.phone2, \"_\"),\n" +
//            "\t\tIFNULL(c. NAME, \"_\")\n" +
//            "\t) like #{search}  </if></script>")
    @Select("<script>" +
            "SELECT\n" +
            "\tc.id,\n" +
            "\tc. name name,\n" +
            "\tc.`level`,\n" +
            "\tc.phone,\n" +
            "\tc.star,\n" +
            "\tb.`bName` buildingName,\n" +
            "\tDATE_FORMAT(c.pre_follow_time,'%Y-%m-%d %H:%i:%s') preFollowTime\n" +
            "FROM\n" +
            "\tcustomer c\n" +
            "INNER JOIN (\n" +
            "\tSELECT\n" +
            "\t\tc.id cId,\n" +
            "\t\tGROUP_CONCAT(t. NAME SEPARATOR ',') bName\n" +
            "\tFROM\n" +
            "\t\tcustomer c\n" +
            "\tINNER JOIN (\n" +
            "\t\tSELECT\n" +
            "\t\t\tb.`name`,\n" +
            "\t\t\tb.id,\n" +
            "\t\t\tr.cus_id\n" +
            "\t\tFROM\n" +
            "\t\t\tbuilding b\n" +
            "\t\tINNER JOIN r_cus_bdg r ON r.bdg_id = b.id\n" +
            "\t) t ON t.cus_id = c.id\n" +
            "\tWHERE\n" +
            "\t\tc.broker_id = #{userId}\n" +
            "\tGROUP BY\n" +
            "\t\tcId\n" +
            ") b ON c.id = b.cid\n" +
            "WHERE\n" +
            "\tbroker_id = #{userId}\n" +
            "<if test=\" star==1 \">AND c.star = 1\n</if>" +
            "<if test=\" level!=null \">AND c.`level` = #{level}\n</if>" +
            "<if test=\" search!=null \">AND CONCAT(\n" +
            "\tIFNULL(b.`bName`, \"_\"),\n" +
            "\tIFNULL(c.phone, \"_\"),\n" +
            "\tIFNULL(c.phone2, \"_\"),\n" +
            "\tIFNULL(c. NAME, \"_\")\n" +
            ") LIKE #{search}</if>" +
            "</script>")
    List<HashMap> getCusList(JSONObject param);

    @Select("select n.*,u.chathead,u.`name` from niche n INNER JOIN `user` u on u.id=n.user_id and u.referrer_id=#{userId}")
    List<HashMap> getOpts(JSONObject param);
//
//    @Select("select * from user where wxid=#{wxid}")
//    User findOneByWxid(@Param("wxid") String wxid);
//
//    @Insert("insert into user(name, wxid, storecode, token, storecodetime) values(#{name},#{wxid},#{storecode},#{token},#{storecodetime})")
//    public int userAdd(User user);
//
//    @Update({ "update user set storecode = #{storecode},storecodetime = #{storecodetime} where id = #{id}" })
//    int updateUserStorecode(User user);

    @Update("UPDATE customer c set c.star=#{star} where c.id=#{cusId}")
    Integer cusStar(Map param);

}
