package com.ld.ldapp.mapper;

import com.ld.ldapp.domain.Adprepare;
import com.ld.ldapp.domain.Prepare;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PrepaerMapper {

    @Select("select * from prepare order by level desc")
    List<Prepare> findAll();


    @Select("select * from prepare where id=#{id}")
    Adprepare findOneById(@Param("id") Integer id);
//
//    @Select("select * from user where wxid=#{wxid}")
//    User findOneByWxid(@Param("wxid") String wxid);
//
    @Insert("insert into prepare(buildingid, clientname, clientphone, gender, showup, massage, userid) values(#{buildingid},#{clientname},#{clientphone},#{gender},#{showup},#{massage},#{userid})")
    public int prepareAdd(Prepare prepare);
//
//    @Update({ "update user set storecode = #{storecode},storecodetime = #{storecodetime} where id = #{id}" })
//    int updateUserStorecode(User user);


}
