package com.ld.ldapp.domain;

import lombok.Data;

@Data
public class ReturnData {


    private String code;
    private Object data;
    private String massage;

    public ReturnData(String massage){

        this.code="102";
        this.massage=massage;
    }

    public ReturnData(Object data){

        this.code="100";
        this.data=data;
        this.massage="成功";
    }

    public ReturnData(){

        this.code="101";
        this.data=null;
        this.massage="数据效验失败";
    }
}
