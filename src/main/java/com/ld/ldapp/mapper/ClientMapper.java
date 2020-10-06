package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClientMapper {

    @Select("select count(1) as total,1 as type,2 as cycle from client where userid=#{userId} and record=1 and date_format(record_time,'%Y-%m')=date_format(now(),'%Y-%m')\n" +
            "union \n" +
            "select count(1) as total,1 as type,1 as cycle from client where userid=#{userId} and record=1\n" +
            "UNION\n" +
            "select count(1) as total,1 as type,3 as cycle from client where userid=#{userId} and record=1 and YEARWEEK(date_format(record_time,'%Y-%m-%d')) = YEARWEEK(now())\n" +
            "UNION\n" +
            "select count(1) as total,1 as type,4 as cycle from client where userid=#{userId} and record=1 and to_days(record_time)=to_days(now())\n" +
            "\n" +
            "UNION\n" +
            "\n" +
            "select count(1) as total,2 as type,2 as cycle from client where userid=#{userId} and showed=1 and date_format(record_time,'%Y-%m')=date_format(now(),'%Y-%m')\n" +
            "union \n" +
            "select count(1) as total,2 as type,1 as cycle from client where userid=#{userId} and showed=1\n" +
            "UNION\n" +
            "select count(1) as total,2 as type,3 as cycle from client where userid=#{userId} and showed=1 and YEARWEEK(date_format(record_time,'%Y-%m-%d')) = YEARWEEK(now())\n" +
            "UNION\n" +
            "select count(1) as total,2 as type,4 as cycle from client where userid=#{userId} and showed=1 and to_days(record_time)=to_days(now())\n" +
            "\n" +
            "UNION\n" +
            "\n" +
            "select count(1) as total,3 as type,2 as cycle from client where userid=#{userId} and deal=1 and date_format(record_time,'%Y-%m')=date_format(now(),'%Y-%m')\n" +
            "union \n" +
            "select count(1) as total,3 as type,1 as cycle from client where userid=#{userId} and deal=1\n" +
            "UNION\n" +
            "select count(1) as total,3 as type,3 as cycle from client where userid=#{userId} and deal=1 and YEARWEEK(date_format(record_time,'%Y-%m-%d')) = YEARWEEK(now())\n" +
            "UNION\n" +
            "select count(1) as total,3 as type,4 as cycle from client where userid=#{userId} and deal=1 and to_days(record_time)=to_days(now())\n")
    List<HashMap> getStatistics(Integer userId);

    @Insert("INSERT INTO `client` ('user_name','building_name','store_code',`buildingid`, `clientname`, `clientphone`, `gender`, `showup`, `massage`, `userid`, `record`,`record_time`) \n" +
            "VALUES (#{userName},#{buildingName},#{storeCode},#{buildingid}, #{clientname},#{clientphone}, #{gender}, #{showup}, #{massage}, #{userid}, 1,CURRENT_TIMESTAMP)")
    Integer record(JSONObject param);

    @Select("<script>  select id,name,phone,buildingId,date_format(time,'%Y-%m-%d %H:%i:%s') time from (" +
            "select id,clientname as name,clientphone phone,buildingid buildingId,\n" +
            "<if test=\" type==1 \"> record_time time</if>" +
            "<if test=\" type==2 \"> showed_time time\n</if>" +
            "<if test=\" type==3 \"> deal_time time\n</if>" +
            " from client \n" +
            " WHERE userid=#{userId} \n" +
            "<if test=\" type==1 \"> AND record=1 </if>\n" +
            "<if test=\" type==2 \"> AND showed=1\n</if>" +
            "<if test=\" type==3 \"> AND deal=1</if>" +
            ")t where 1=1 "+
            "<if test=\" cycle==2 \"> AND date_format(time,'%Y-%m')=date_format(now(),'%Y-%m') </if>" +
            "<if test=\" cycle==3 \"> AND YEARWEEK(date_format(time,'%Y-%m-%d')) = YEARWEEK(now()) </if>" +
            "<if test=\" cycle==4 \"> AND to_days(time)=to_days(now()) </if>" +
            " </script>")
    List<HashMap> getRecordList(JSONObject param);



    @Insert("INSERT INTO `customer` (`name`, `gender`, `phone`, `phone2`, `level`, `need_type`, `care_area`, `care_building_id`, `next_follow_time`) " +
            "VALUES (#{name}, #{gender}, #{phone}, #{phone2}, #{level}, #{needType}, #{careArea}, #{careBuildingId}, #{nextFollowTime}) ")
    Integer addCustomer(JSONObject param);

    @Select("SELECT (\n" +
            "SELECT IFNULL(SUM(t.rcs),0) recs from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) rcs,buildingid from client where record=1 and to_days(record_time)+15>=to_days(now())  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) recs,\n" +
            "(SELECT IFNULL(SUM(t.shw),0) shws from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) shw,buildingid from client where record=2 and to_days(showed_time)=to_days(now())  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) shws,\n" +
            "(SELECT IFNULL(SUM(t.smc),0) smcs from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) smc,buildingid from client where showed=1 and showed_mf_date is null  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) smcs,\n" +
            "(SELECT IFNULL(SUM(t.dmc),0) dmcs from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) dmc,buildingid from client where deal=1 and deal_mf_date is null  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) dmcs\n")
    Map staLoc(Integer locId);

    @Select("select \n" +
            "(SELECT IFNULL(SUM(t.rcds),0) rcds from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) rcds,buildingid from client where to_days(deal_time)+#{days}>=to_days(now())  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) deals,\n" +
            "(SELECT IFNULL(SUM(t.rcds),0) rcds from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) rcds,buildingid from client where to_days(showed_time)+#{days}>=to_days(now())  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) shows,\n" +
            "(SELECT IFNULL(SUM(t.rcds),0) rcds from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) rcds,buildingid from client where to_days(cf_record_time)+#{days}>=to_days(now())  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) crcds,\n" +
            "(SELECT IFNULL(SUM(t.rcds),0) rcds from(\n" +
            "select c.*,b.loct_id from building b \n" +
            "INNER JOIN (select count(1) rcds,buildingid from client where to_days(createdate)+#{days}>=to_days(now())  GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}\n" +
            ")t) rcds")
    Map staHistory(Integer locId,Integer days);

    @Select("select c.*,b.`name` from building b \n" +
            "INNER JOIN (select count(1) rcds,buildingid from client GROUP BY buildingid) c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId}")
    List<Map> rank(Integer locId);

    @Select("select t.*,u.`name` uName from (\n" +
            "select c.*,b.loct_id loctId,b.`name` bName from building b \n" +
            "INNER JOIN (\n" +
            "select id,clientname,clientphone,gender, DATE_FORMAT( createdate, '%Y-%m-%d %H:%i:%s' ) createdate,userid,buildingid from client where record=1 AND to_days(createdate)+15>=to_days(now())  \n" +
            ") c \n" +
            "on c.buildingid=b.id where b.loct_id=#{locId})t\n" +
            "INNER JOIN `user` u on t.userid=u.id AND concat_ws('---',u.`name`,t.clientname,t.clientphone) like #{cdt}\n")
    List<Map> cRcds(Integer locId,String cdt);

    @Update("UPDATE client SET record=2,cf_record_time=CURRENT_TIME WHERE id=#{cId}")
    Integer rcdCf(Integer cId);

    @Select("select t.*,u.`name` uName from ( select c.*,b.loct_id loctId,b.`name` bName from building b INNER JOIN \n" +
            "( select id,clientname,clientphone,gender, DATE_FORMAT( createdate, '%Y-%m-%d %H:%i:%s' ) createdate,DATE_FORMAT( showup, '%Y-%m-%d %H:%i:%s' )showup ,userid,buildingid from \n" +
            "client where record=2 AND showed_time IS NULL AND to_days(createdate)+15>=to_days(now()) ) c on c.buildingid=b.id where b.loct_id=#{locId})t \n" +
            "INNER JOIN `user` u on t.userid=u.id AND concat_ws('---',u.`name`,t.clientname,t.clientphone) like #{cdt}\n")
    List<Map> cShws(Integer locId,String cdt);

    @Update("UPDATE client SET showed=1,showed_time=CURRENT_TIME WHERE id=#{cId}")
    Integer shwCf(Integer cId);

    @Select("select t.*,u.`name` uName from \n" +
            "( select c.*,b.loct_id loctId,b.`name` bName from \n" +
            "building b INNER JOIN \n" +
            "( select id,clientname,clientphone,gender, DATE_FORMAT( createdate, '%Y-%m-%d %H:%i:%s' ) createdate,userid,buildingid,IFNULL(deal,0) deal,showed_mf shwMf\n" +
            "from client where showed=1 AND IFNULL(deal,0)=0 AND to_days(createdate)+15>=to_days(now()) ) c on c.buildingid=b.id where b.loct_id=#{locId})t \n" +
            "INNER JOIN `user` u on t.userid=u.id AND concat_ws('---',u.`name`,t.clientname,t.clientphone) like #{cdt}\n")
    List<Map> cDeals(Integer locId,String cdt);

    @Select("select t.*,u.`name` uName from \n" +
            "( select c.*,b.loct_id loctId,b.`name` bName from \n" +
            "building b INNER JOIN \n" +
            "( select id,clientname,clientphone,gender, DATE_FORMAT( createdate, '%Y-%m-%d %H:%i:%s' ) createdate,userid,buildingid,deal_mf dealMf,showed_mf shwMf\n" +
            "from client where IFNULL(deal,0)=1 ) c on c.buildingid=b.id where b.loct_id=#{locId})t \n" +
            "INNER JOIN `user` u on t.userid=u.id AND concat_ws('---',u.`name`,t.clientname,t.clientphone) like #{cdt}\n")
    List<Map> traEds(Integer locId,String cdt);

    @Update("UPDATE client SET deal=1,deal_time=CURRENT_TIME WHERE id=#{cId}")
    Integer traCf(Integer cId);

    @Update("UPDATE client SET showed_mf=#{url},showed_mf_date=CURRENT_TIME WHERE id=#{cId}")
    Integer shwMf(JSONObject param);

    @Update("UPDATE client SET deal_mf=#{url},deal_mf_date=CURRENT_TIME WHERE id=#{cId}")
    Integer traMf(JSONObject param);


    /*过期
    select t.*,u.`name` uName from
( select c.*,b.loct_id loctId,b.`name` bName from
building b INNER JOIN
( select id,clientname,clientphone,gender, DATE_FORMAT( createdate, '%Y-%m-%d %H:%i:%s' ) createdate, DATE_FORMAT( showup, '%Y-%m-%d %H:%i:%s' ) showup,userid,buildingid,showed_mf shwMf,showed
from client where IFNULL(deal,0)=0 AND  to_days(createdate)+15<to_days(now()) ) c on c.buildingid=b.id where b.loct_id<>5)t
INNER JOIN `user` u on t.userid=u.id
     */
    @Select("select t.*,u.`name` uName from\n" +
            "( select c.*,b.loct_id loctId,b.`name` bName from\n" +
            "building b INNER JOIN\n" +
            "( select id,clientname,clientphone,gender, DATE_FORMAT( createdate, '%Y-%m-%d %H:%i:%s' ) createdate, DATE_FORMAT( showup, '%Y-%m-%d %H:%i:%s' ) showup,userid,buildingid,showed_mf shwMf,showed\n" +
            "from client where IFNULL(deal,0)=0 AND  to_days(createdate)+15<to_days(now()) ) c on c.buildingid=b.id where b.loct_id=#{locId})t\n" +
            "INNER JOIN `user` u on t.userid=u.id AND concat_ws('---',u.`name`,t.clientname,t.clientphone) like #{cdt}\n")
    List<Map> otRcd(Integer locId,String cdt);


}
