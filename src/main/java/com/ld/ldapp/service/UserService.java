package com.ld.ldapp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.mapper.ChatMapper;
import com.ld.ldapp.mapper.StoreMapper;
import com.ld.ldapp.mapper.TouristMapper;
import com.ld.ldapp.util.AESUtil;
import com.ld.ldapp.util.HttpClient;
import com.ld.ldapp.util.JwtToken;
import com.ld.ldapp.domain.User;
import com.ld.ldapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    TouristMapper touristMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    ChatMapper chatMapper;

    @Autowired
    BuildingService buildingService;

    public User login(String code, Integer referrer_id,JSONObject param){
//        String sign=JwtToken.sha256(code+referrer_id+"ldApp-001");
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx6a6bbc33bcc54462&secret=a25714c9ab53714281ea04111d4b1d2a&js_code="+code+"&grant_type=authorization_code";

        String mas=HttpClient.doGet(url);
        JSONObject jsonObject= JSONObject.parseObject(mas);

        String openid =jsonObject.getString("openid");
        if(openid==null) return null;
        openid =JwtToken.sha256(openid);
        User user = userMapper.findOneByOpenid(openid);
        if(user==null){
            user=new User();
            user.setReferrerId(referrer_id);
            user.setOpenid(openid);
            user.setName(param.getString("name"));
            user.setChathead(param.getString("chatHead"));
            userMapper.userAdd(user);
            user = userMapper.findOneByOpenid(openid);
        }else{
            user.setReferrerId(referrer_id);
            user.setName(param.getString("name"));
            user.setChathead(param.getString("chatHead"));
            userMapper.userUpdate(user);
        }

        jsonObject.put("key",jsonObject.getString("session_key"));
        jsonObject.put("userId",user.getId());
        userMapper.keyAdd(jsonObject);
        return user;
    }

    public User getUserById(Integer id){
        User user = userMapper.findOneById(id);
        return user;
    }

    public User beBroker(JSONObject param){

        String storecode=param.getString("storecode");
        List<Map> list=storeMapper.findAll(storecode);
        if(list==null||list.isEmpty()){
            return null;
        }
        User user=new User();
        user.setId(param.getInteger("userId"));
        user.setStorecode(storecode);
        user.setPhone(param.getString("phone"));

        userMapper.updateUserStorecode(user);


        Integer rs=userMapper.findRole(list.get(0).get("id"),user.getId());

        if(rs==0){
            JSONObject uRoles=new JSONObject();
            uRoles.put("s_id",list.get(0).get("id"));
            uRoles.put("s_code",user.getStorecode());
            uRoles.put("user_id",user.getId());
            uRoles.put("role",1);
            userMapper.addRole(uRoles);
        }


        user=userMapper.findOneById(user.getId());
        return user;
    }

    public List<User> buildingBroker(Integer building_id){
        List<User> list= userMapper.findByBuildingId(building_id);
        return list;
    }


    public Integer useMsgs(Integer id){


        return chatMapper.getUseNewMsgTotal(id);
    }

    public User beLvp(JSONObject param) {
        String storecode=param.getString("storecode");

        List<Map> list=storeMapper.findAll(storecode);
        if(list==null||list.isEmpty()){
            return null;
        }

        String buildingCode=param.getString("lvpCode");
        Integer bid=param.getInteger("b_id");
        List<Map> list1=userMapper.findBlvpCode(bid,storecode);
        if(list1==null||list1.isEmpty()){
            return null;
        }



        User user=new User();
        user.setId(param.getInteger("userId"));
        user.setStorecode(storecode);
        user.setPhone(param.getString("phone"));
        user.setLvb(1);

        userMapper.updateUserStorecode(user);

        Integer rs=userMapper.findRole1(list.get(0).get("id"),user.getId(),bid);

        if(rs==0){
            JSONObject uRoles=new JSONObject();
            uRoles.put("s_id",list.get(0).get("id"));
            uRoles.put("s_code",user.getStorecode());
            uRoles.put("user_id",user.getId());
            uRoles.put("role",2);
            uRoles.put("bid",bid);
            userMapper.addRole1(uRoles);
        }

        user=userMapper.findOneById(user.getId());
        return user;
    }

    public Integer lvpBind(JSONObject param) {

        User user=userMapper.findOneById(param.getInteger("loctId"));
        if(user!=null&&user.getRole()==2){
            JSONArray buds=param.getJSONArray("buds");
            Integer i=0;

            for (Object bud : buds) {
                Integer bid=(Integer) bud;
                i=i+buildingService.bindLvp(param.getInteger("loctId"),bid);
            }
            return i;
        }
        return 0;
    }

    public User dcy(JSONObject param) {
        User user=userMapper.findOneById(param.getInteger("userId"));
        JSONObject keyObj=userMapper.keyfind(param.getInteger("userId"));
        JSONObject info= JSON.parseObject(AESUtil.decryptData(param.getString("encryptData"), keyObj.getString("key"), param.getString("iv")));
        user.setPhone(info.getString("phoneNumber"));
        userMapper.userUpdate(user);
        return user;
    }

    public JSONObject tourist(String touristKey) {

        JSONObject tourist=touristMapper.findByUnique(touristKey);
        if(tourist==null){
            touristMapper.addTourist(touristKey);
            tourist=touristMapper.findByUnique(touristKey);
        }
        String token=JwtToken.generateToken(tourist.toJSONString());
        JSONObject rtn=new JSONObject();
        rtn.put("token",token);
        return rtn;
    }
}
