package com.ld.ldapp.domain;

import lombok.Data;

@Data
public class Prepare {

    private Integer id;

    //楼盘id
    private Integer buildingid;
    //客户姓名
    private String clientname;
    //客户电话
    private String clientphone;
    //客户性别
    private String gender;
    //预计到访时间
    private String showup;
    //留言
    private String massage;

    private Integer userid;


    public boolean verify(){
        if(buildingid==null)
            return false;
        if(clientname==null)
            return false;
        if(clientphone==null)
            return false;
        if(gender==null)
            return false;
        if(showup==null)
            return false;
        if(massage==null)
            return false;
        return true;
    }

}
