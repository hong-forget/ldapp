package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.Adprepare;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface NicheMapper {

    @Select("select * from adprepare order by level desc")
    List<Adprepare> findAll();


    @Select("select * from Prepare where id=#{id}")
    Adprepare findOneById(@Param("id") Integer id);

    @Insert("INSERT INTO `niche` (`user_id`, `operat`, `keep_time`, `operat_id`, `share_id`) VALUES (#{userId}, #{operat}, #{keepTime}, #{operatId}, #{shareId}) ")
    Integer addopt(JSONObject param);

//    @Select("<script>" +
//            "SELECT t.*,\n" +
//            "IF(@p=rick,\n" +
//            "    CASE \n" +
//            "        WHEN @s=id THEN @r\n" +
//            "        WHEN @s:=id THEN @r:=@r+1\n" +
//            "    END,\n" +
//            "  @r:=1 ) AS numrank,\n" +
//            "@p:=rick,\n" +
//            "@s:=id\n" +
//            "FROM (select n.*,CONCAT(IFNULL(operat,\"_\"),IFNULL(operat_id, \"_\")) rick from niche n) t,(SELECT @p:=NULL,@s:=NULL,@r:=0)r\n" +
//            "\n" +
//            "<if test=\" userId!=null \" >where user_id=#{userId}\n</if>" +
//            "ORDER BY rick,id " +
//            "</script>")
    @Select("<script>SELECT DATE_FORMAT(n1.create_time,'%Y-%m-%d %H:%i:%s') create_time,n1.keep_time,n1.operat,n1.operat_id,n1.user_id,n1.id," +
            "(SELECT\n" +
            "\t\t\tcount(1)\n" +
            "\t\tFROM\n" +
            "\t\t\tniche n2\n" +
            "\t\tWHERE\n" +
            "\t\t\tCONCAT(\n" +
            "\t\t\t\tIFNULL(n1.operat, \"_\"),\n" +
            "\t\t\t\tIFNULL(n1.operat_id, \"_\"),\n" +
            "\t\t\t\tIFNULL(n1.user_id, \"_\")\n" +
            "\t\t\t) = CONCAT(\n" +
            "\t\t\t\tIFNULL(n2.operat, \"_\"),\n" +
            "\t\t\t\tIFNULL(n2.operat_id, \"_\"),\n" +
            "\t\t\t\tIFNULL(n2.user_id, \"_\")\n" +
            "\t\t\t) \n" +
            "\t\tAND n1.create_time >= n2.create_time\n" +
            "\t) numrank\n" +
            "FROM\n" +
            "\tniche n1 where 1=1 " +
            "<if test=\" userId!=null \" > and user_id=#{userId}\n</if>" +
            "<if test=\" shareId!=null \" > and share_id=#{shareId}\n</if></script>")
    List<HashMap> getOpts(JSONObject param);

    @Select("select n1.*,(\n" +
            "SELECT\n" +
            "\t\t\tcount(1)\n" +
            "\t\tFROM\n" +
            "\t\t\tniche n2\n" +
            "\t\tWHERE\n" +
            "\t\t\tCONCAT(\n" +
            "\t\t\t\tIFNULL(n1.operat, \"_\"),\n" +
            "\t\t\t\tIFNULL(n1.operat_id, \"_\"),\n" +
            "\t\t\t\tIFNULL(n1.user_id, \"_\")\n" +
            "\t\t\t) = CONCAT(\n" +
            "\t\t\t\tIFNULL(n2.operat, \"_\"),\n" +
            "\t\t\t\tIFNULL(n2.operat_id, \"_\"),\n" +
            "\t\t\t\tIFNULL(n2.user_id, \"_\")\n" +
            "\t\t\t) \n" +
            "\t\tAND n1.id >= n2.id) numrank\n" +
            " from (\n" +
            "\n" +
            "select n3.user_id,n3.operat,n3.operat_id ,DATE_FORMAT(n3.create_time,'%Y-%m-%d %H:%i:%s') create_time ,MAX(id) id\n" +
            " from niche n3 where 1=1 and share_id=#{shareId} GROUP BY user_id,operat,operat_id )n1")
    List<HashMap> getOpt(JSONObject param);
//
//    @Select("select * from user where wxid=#{wxid}")
//    User findOneByWxid(@Param("wxid") String wxid);
//
//    @Insert("insert into user(name, wxid, storecode, token, storecodetime) values(#{name},#{wxid},#{storecode},#{token},#{storecodetime})")
//    public int userAdd(User user);
//
//    @Update({ "update user set storecode = #{storecode},storecodetime = #{storecodetime} where id = #{id}" })
//    int updateUserStorecode(User user);


}
