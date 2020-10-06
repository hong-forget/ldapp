package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface PhotoAlbumMapepr {

    @Insert("INSERT INTO `b_photoalbum` (`renderings`, `design`, `demo`, `scene`, `location`, `vedio`, `persale`,building_id) VALUES (#{renderings}, #{design}, #{demo}, #{scene}, #{location}, #{vedio}, #{persale},#{buildingId})")
    Integer add(JSONObject param);

    @Update("UPDATE `b_photoalbum` SET `renderings`=#{renderings}, `design`=#{design}, `demo`=#{demo}, `scene`=#{scene}, `location`=#{location}, `vedio`=#{vedio}, `persale`=#{persale} WHERE (`id`=#{id})")
    Integer update(JSONObject param);

    @Update("UPDATE `building` SET `imgs`=#{thumbnail} WHERE (`id`=#{buildingId})")
    Integer setThumbnail(JSONObject param);

    @Select("SELECT `id`,`renderings`, `design`, `demo`, `scene`, `location`, `vedio`, `persale`,building_id buildingId FROM `b_photoalbum` WHERE building_id=#{buildingId} LIMIT 0, 1")
    Map photoalbumFind(JSONObject param);
}
