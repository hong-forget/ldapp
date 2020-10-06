package com.ld.ldapp.service;

import java.util.Map;

public interface PaymentService  {

    Map<String,String> payment(String orderNo, double money, String openId) throws Exception;

    int notify(Map<String,Object> map) throws Exception;

}
