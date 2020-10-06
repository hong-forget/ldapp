package com.ld.ldapp.service.impl;

import com.ld.ldapp.service.PaymentService;

import java.util.Map;

public class PaymentServiceImpl implements PaymentService {


    @Override
    public Map<String, String> payment(String orderNo, double money, String openId) throws Exception {
        return null;
    }

    @Override
    public int notify(Map<String, Object> map) throws Exception {
        return 0;
    }
}
