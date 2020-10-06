package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BuildManagerMapper {

    @Select("SELECT count(1) total FROM building")
    JSONObject total();

    @Select("<script>SELECT id,`name`,top,hot,m_tag mTag,m_img mImg,city,area,area_block areaBlock,address,price,salsline, DATE_FORMAT(openingtime,'%Y-%m-%d %H:%i:%s') openingtime,DATE_FORMAT(c_date,'%Y-%m-%d %H:%i:%s') cDate,DATE_FORMAT(updatetime,'%Y-%m-%d %H:%i:%s') updatetime,c_username cUsername from building" +
            " where 1=1 " +
            "<if test=\" flag==1 \">AND `hot`=1 </if>" +
            "<if test=\" flag==2 \">AND `top`=1 </if>" +
            "</script>")
    List<Map> list(JSONObject param);

    @Insert("INSERT INTO `building` (" +
            " sale_coordinate,location,`name`, `floorarea`, `tag`, `price`, `address`, `openingtime`,   `area`,  `status`,  `type`, `discounts`, `salsline`, `area_block`,   `city`,  `c_user`, `c_username`, `alias`, `metro`, `fit_up`, `total_price`, `coordinate`,  `price_remark`) VALUES (" +
            "#{saleCoordinate},#{location},#{name}, #{floorarea},#{tag},#{price}, #{address}, #{openingtime}, #{area}, #{status}, #{type}, #{discounts}, #{salsline}, #{areaBlock}, #{city}, #{cUser}, #{cUsername}, #{alias}, #{metro}, #{fitUp}, #{totalPrice}, #{coordinate}, #{price_remark})")
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=int.class)
    Integer add(JSONObject param);

    @Insert("INSERT INTO `property` " +
            "(`building_id`, `period_int`, `property_price`, `property_company`, `cover_arer`, `building_area`, `holds_total`, `parking_total`, `plot_rate`, `greening_rate`, `parking_rate`, `building_type`, `developers`, `developers_short_name`) VALUES (" +
            "#{buildingId}, #{periodInt}, #{propertyPrice}, #{propertyCompany}, #{coverArer}, #{buildingArea}, #{holdsTotal}, #{parkingTotal}, #{plotRate}, #{greeningRate}, #{parkingRate}, #{buildingType}, #{developers}, #{developersShortName})")
    Integer planAdd(JSONObject param);

    @Update("<script>UPDATE `property` SET " +
            "<if test=\" periodInt!=null \"> `period_int`=#{periodInt}, </if>" +
            "<if test=\" propertyPrice!=null \"> `property_price`=#{propertyPrice}, </if>" +
            "<if test=\" propertyCompany!=null \"> `property_company`=#{propertyCompany}, </if>" +
            "<if test=\" holdsTotal!=null \"> `holds_total`=#{holdsTotal}, </if>" +
            "<if test=\" coverArer!=null \"> `cover_arer`=#{coverArer}, </if>" +
            "<if test=\" parkingTotal!=null \">`parking_total`=#{parkingTotal}, </if>" +
            "<if test=\" plotRate!=null \">`plot_rate`=#{plotRate}, </if>" +
            "<if test=\" propertyType!=null \">`property_type`=#{propertyType}, </if>" +
            "<if test=\" buildingArea!=null \">`building_area`=#{buildingArea}, </if>" +
            "<if test=\" periodInt!=null \">`period_int`=#{periodInt},   </if>" +
            "<if test=\" greeningRate!=null \">`greening_rate`=#{greeningRate},  </if>" +
            "<if test=\" developersShortName!=null \">`developers_short_name`=#{developersShortName},  </if>" +
            "<if test=\" developers!=null \">`developers`=#{developers},  </if>" +
            "<if test=\" parkingRate!=null \">`parking_rate`=#{parkingRate},  </if>" +
            "<if test=\" buildingType!=null \">`building_type`=#{buildingType},  </if>" +
            "`u_date`=CURRENT_TIMESTAMP where id=#{id}</script>")
    Integer planUpdate(JSONObject param);

    @Insert("INSERT INTO `b_introduce` (`introduce`, `around`, `traffic`, `building_id`) VALUES (#{introduce}, #{around}, #{traffic}, #{buildingId})")
    Integer introduceAdd(JSONObject param);




    @Update("<script>UPDATE `building` SET " +
            "<if test=\" saleCoordinate!=null \"> `sale_coordinate`=#{saleCoordinate}, </if>" +
            "<if test=\" location!=null \"> `location`=#{location}, </if>" +
            "<if test=\" name!=null \"> `name`=#{name}, </if>" +
            "<if test=\" floorarea!=null \">`floorarea`=#{floorarea}, </if>" +
            "<if test=\" tags!=null \">`tag`=#{tags}, </if>" +
            "<if test=\" price!=null \">`price`=#{price}, </if>" +
            "<if test=\" address!=null \">`address`=#{address}, </if>" +
            "<if test=\" openingtime!=null \">`openingtime`=#{openingtime},   </if>" +
            "<if test=\" area!=null \">`area`=#{area},  </if>" +
            "<if test=\" status!=null \">`status`=#{status},  </if>" +
            "<if test=\" type!=null \">`type`=#{type}, </if>" +
            "<if test=\" discounts!=null \">`discounts`=#{discounts}, </if>" +
            "<if test=\" salsline!=null \">`salsline`=#{salsline}, </if>" +
            "<if test=\" areaBlock!=null \">`area_block`=#{areaBlock},   </if>" +
            "<if test=\" city!=null \">`city`=#{city},  </if>" +
            "<if test=\" cUser!=null \">`c_user`=#{cUser}, </if>" +
            "<if test=\" cUsername!=null \">`c_username`=#{cUsername}, </if>" +
            "<if test=\" alias!=null \">`alias`=#{alias}, </if>" +
            "<if test=\" metro!=null \">`metro`=#{metro}, </if>" +
            "<if test=\" fitUp!=null \">`fit_up`=#{fitUp}, </if>" +
            "<if test=\" totalPrice!=null \">`total_price`=#{totalPrice}, </if>" +
            "<if test=\" coordinate!=null \">`coordinate`=#{coordinate},  </if>" +
            "<if test=\" priceRemark!=null \">`price_remark`=#{priceRemark}, </if>" +
            "`updatetime`=CURRENT_TIMESTAMP where id=#{id}</script>")
    Integer update(JSONObject param);



    @Update("<script>UPDATE `b_introduce` SET " +
            "<if test=\" introduce!=null \"> `introduce`=#{introduce}, </if>" +
            "<if test=\" around!=null \"> `around`=#{around}, </if>" +
            "<if test=\" traffic!=null \"> `traffic`=#{traffic}, </if>" +
            "`building_id`=#{buildingId} where id=#{id}</script>")
    Integer introduceUpdate(JSONObject param);

    @Select("SELECT id, introduce , around, traffic,building_id buildingId  FROM `b_introduce` where building_id=#{buildingId} LIMIT 0, 1")
    Map findIntroduceByBuildingId(JSONObject param);

    @Insert("INSERT INTO `b_pre_sale_license` (`pre_sale`, `grant_date`, `bind_building`, `building_id`) VALUES (#{preSale}, #{grantDate}, #{bindBuilding}, #{buildingId})")
    Integer preSaleLiceseAdd(JSONObject param);

    @Delete("DELETE FROM `b_pre_sale_license` WHERE (`id`=#{id})")
    Integer preSaleLiceseDel(JSONObject param);

    @Select("SELECT id, pre_sale preSale, DATE_FORMAT(grant_date,'%Y-%m-%d %H:%i:%s') grantDate, bind_building bindBuilding,building_id buildingId  FROM `b_pre_sale_license` where building_id=#{buildingId}")
    List<Map> preSaleLiceseFind(JSONObject param);

    @Select("SELECT sale_coordinate saleCoordinate,location,`name`, `floorarea`, `tag`, `price`, `address`,DATE_FORMAT(openingtime,'%Y-%m-%d %H:%i:%s') openingtime,   `area`,  `status`,  `type`, `discounts`, `salsline`, `area_block` areaBlock,   `city`,  `c_user` cUser, `c_username` cUsername, `alias`, `metro`, `fit_up` fitUp, `total_price` totalPrice, `coordinate`,  `price_remark` priceRemark FROM `building` WHERE id =#{id}")
    Map find(JSONObject param);
}
