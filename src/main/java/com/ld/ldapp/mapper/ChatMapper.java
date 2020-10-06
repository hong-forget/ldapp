package com.ld.ldapp.mapper;

import com.ld.ldapp.domain.Chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ChatMapper {

    @Select("select * from chat where id=#{id}")
    Chat findById(Integer id);

    @Select("select count(1) from chat where to_id=#{toId} and readed is null")
    Integer unReadTotal(Integer toId);

    @Select({ "select t.id id,t.from_id fromId,t.to_id toId,t.context,DATE_FORMAT(t.time,'%Y-%m-%d %H:%i:%s') time,t.u_id as uId,t.type from (" +
            "select * from chat t1 where to_id = #{toId} and  from_id=#{fromId} UNION \n" +
            "select * from chat t2 where to_id = #{fromId} and from_id= #{toId} " +
            ")t ORDER BY t.id desc" })
    List<HashMap> readChat(Integer fromId, Integer toId);

    @Update({ "update chat set readed = 1 where to_id = #{toId} and  from_id=#{fromId}" })
    Integer updateChat(Integer fromId, Integer toId);

    @Insert("insert into chat(id,from_id, to_id, context ,type ,u_id) values(#{id}, #{fromId} , #{toId} , #{context}, #{type},#{uid} )")
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=int.class)
    Integer addChat(Map m);


    @Select("SELECT COUNT(1) as total from chat where to_id=#{uid} and readed is null")
    Integer getUseNewMsgTotal(Integer uid);

    @Select("SELECT IF (t1.total > 0, t1.total, 0) total, t3. name, t3.chathead, t3.from_id id, DATE_FORMAT( t3.time, '%Y-%m-%d %H:%i:%s' ) lastTime, IF ( t3.type = 'img', 'img', t3.context ) lastContext FROM ( SELECT count(1) AS total, from_id FROM chat c " +
            "WHERE to_id = #{uid} AND readed IS NULL GROUP BY c.from_id ) t1 RIGHT JOIN ( SELECT r2.*, r1.chathead, r1.`name` FROM ( SELECT um.*, u.`name`, u.chathead FROM ( SELECT MAX(r.id) maxid, r.from_id, r.to_id FROM chat r " +
            "WHERE r.to_id = #{uid} GROUP BY r.from_id, r.to_id ) um INNER JOIN `user` u ON um.from_id = u.id ) r1 INNER JOIN chat r2 ON r1.maxid = r2.id ) t3 ON t3.from_id = t1.from_id\n")
    List<HashMap> getUseMsgs(Integer uid);
}
