package com.ld.ldapp.mapper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findOneById(@Param("id") Integer id);

    @Select("select * from user where wxid=#{wxid}")
    User findOneByWxid(@Param("wxid") String wxid);

    @Insert("insert into user(name, openid, referrer_id, chathead) values( #{name} , #{openid} , #{referrerId},#{chathead} )")
    public int userAdd(User user);

    @Update({ "<script>update user set storecode = #{storecode},<if test=\" lvb!=null \"> role = 0,lvb=#{lvb} ,</if> <if test=\" lvb==null \"> role = 0, </if>phone = #{phone},satisfaction=5,servers=FLOOR(RAND() * 500)+499,storecodetime = CURRENT_TIMESTAMP where id = #{id}</script>" })
    int updateUserStorecode(User user);

    @Select("select * from user u INNER JOIN  r_user_building r where u.id=r.user_id and r.building_id=#{building_id}")
    List<User> findByBuildingId(Integer building_id);


//    @Select("<script>select * from user <if test=\"id !=null \">where id = #{id} </if></script>")


    @Select("select * from user where openid=#{openid}")
    User findOneByOpenid(String openid);

    @Select("SELECT u.* FROM(SELECT count(1) AS total,from_id FROM chat c WHERE to_id = 6 AND readed IS NULL GROUP BY c.from_id) t " +
            "INNER JOIN `user` u ON t.from_id = u.id")
    JSONArray findMsgById(Integer id);

    @Update({ "<script>update user set " +
            "<if test=\" referrerId!=null \"> referrer_id = #{referrerId} ,</if> " +
            "<if test=\" name!=null \"> name = #{name}, </if>" +
            "<if test=\" chathead!=null \"> chathead = #{chathead}, </if>" +
            "<if test=\" phone!=null \"> phone = #{phone}, </if>" +
            "token = null where id = #{id}</script>" })
    Integer userUpdate(User user);


    @Insert("INSERT INTO `wx_key` (`user_id`, `key`) VALUES ( #{userId} , #{key} )")
    public int keyAdd(JSONObject param);

    @Select("SELECT * from wx_key where user_id=#{userId} ORDER BY id DESC LIMIT 0,1")
    public JSONObject keyfind(Integer userId);

    @Select("SELECT count(1) FROM u_role r where r.s_id=#{sid} AND user_id=#{uid} AND role=1 \n")
    Integer findRole(Object sid, Integer uid);

    @Insert("INSERT INTO `u_role` (`user_id`, `role`, `s_id`, `s_code`) VALUES (#{user_id}, #{role}, #{s_id}, #{s_code})")
    void addRole(JSONObject uRoles);

    @Select("select * from building where id=#{id} and lvp_code=#{lvpCode}")
    List<Map> findBlvpCode(Integer id, String lvpCode);

    @Select("SELECT count(1) FROM u_role r where r.s_id=#{sid} AND user_id=#{uid} AND b_id=#{bid} AND role=2 \n")
    Integer findRole1(Object sid, Integer uid,Integer bid);

    @Insert("INSERT INTO `u_role` (`user_id`, `role`, `s_id`, `s_code`,b_id) VALUES (#{user_id}, #{role}, #{s_id}, #{s_code},#{bid})")
    void addRole1(JSONObject uRoles);
}
