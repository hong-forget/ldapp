package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface PlanMapper {


    @Select("SELECT id, building_id buildingId, period_int periodInt, property_type propertyType, property_price propertyPrice, property_company propertyCompany, cover_arer coverArer, building_area buildingArea, holds_total holdsTotal, parking_total parkingTotal, plot_rate plotRate, greening_rate greeningRate, parking_rate parkingRate, building_type buildingType, developers, developers_short_name developersShortName FROM `property` where building_id=#{buildingId} LIMIT 0, 1")
    Map findByBuildingId(JSONObject param);
}
