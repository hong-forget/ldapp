package com.ld.ldapp.domain;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class User {


  private Integer id;
  private String storecode;
  private String chathead;
  private Integer role;
  private String phone;
  private String name;


  private String openid;
  private String createtime;
  private Integer referrerId;
  private Integer servers;
  private Integer star;
  private Integer satisfaction;

  private Integer age;
  private Integer expYear;
  private Integer svcArea;
  private Integer lvb;



  public JSONObject loginReturn(){
    JSONObject jsonObject=new JSONObject();

    jsonObject.put("id",this.getId());
    jsonObject.put("storecode",this.getStorecode());
    jsonObject.put("chathead",this.getChathead());
    jsonObject.put("role",this.getRole());
    jsonObject.put("phone",this.getPhone());
    jsonObject.put("name",this.getName());

    return jsonObject;

  }

  public JSONObject eferrerReturn(){
    JSONObject jsonObject=new JSONObject();

    jsonObject.put("id",this.getId());
    jsonObject.put("chathead",this.getChathead());
    jsonObject.put("phone",this.getPhone());
    jsonObject.put("name",this.getName());
    jsonObject.put("servers",this.getServers());
    jsonObject.put("satisfaction",this.getSatisfaction());
    jsonObject.put("age",this.getAge());
    jsonObject.put("expYear",this.getExpYear());
    jsonObject.put("svcArea",this.getSvcArea());

    return jsonObject;
  }

}
