package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.Banner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface BannerMapper {

    @Select("select id,img,title,`level` from banner")
    List<Banner> findAll();

    @Select("select * from banner where id=#{id}")
    Banner findOneById(@Param("id") Integer id);

    @Select("SELECT * FROM `banner`")
    List<Map> list(JSONObject param);

    @Update("UPDATE `banner` SET `img`=#{img}, `context`=#{img},  `title`=#{title}, `date`=#{date}, `auth`=#{auth}, `city`=#{city}, `udate`=CURRENT_TIMESTAMP WHERE (`id`=#{id})")
    Integer update(JSONObject param);

    @Insert("INSERT INTO `banner` (`img`, `context`, `title`, `date`, `auth`, `city`, `uid`, `uname`) VALUES " +
            "(#{img}, #{context}, #{title}, #{date}, #{auth}, #{city}, #{uid}, #{uname})")
    Integer add(JSONObject param);
}
