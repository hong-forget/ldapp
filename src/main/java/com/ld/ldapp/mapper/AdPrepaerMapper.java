package com.ld.ldapp.mapper;

import com.ld.ldapp.domain.Adprepare;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdPrepaerMapper {

    @Select("select * from adprepare order by level desc")
    List<Adprepare> findAll();


    @Select("select * from adprepare where id=#{id}")
    Adprepare findOneById(@Param("id") Integer id);
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
