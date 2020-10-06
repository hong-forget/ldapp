package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.util.HttpClient;
import com.ld.ldapp.wxpay.MyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class WxPayControl {


    @Autowired
    MyConfig myConfig;

    @PostMapping("/wx/nty")
    public ReturnData nty(@RequestBody JSONObject param){
        System.out.println(param);

        return null;
    }

    @PostMapping("/wx/topay")
    public ReturnData toPay(@RequestBody JSONObject param){


        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(myConfig.getAppID());


        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx6a6bbc33bcc54462&secret=a25714c9ab53714281ea04111d4b1d2a&js_code="+param.getString("code")+"&grant_type=authorization_code";

        String mas= HttpClient.doGet(url);
        JSONObject jsonObject= JSONObject.parseObject(mas);

        String openid =jsonObject.getString("openid");
        if(openid==null) return new ReturnData();

        log.info("=====>>>>> logger()");

        WXPay wxpay = new WXPay(myConfig);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", uuid);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("openid",openid);
        data.put("spbill_create_ip", "114.215.85.111");
        data.put("notify_url", "https://www.czldzx.com/ldapp/wx/nty");
        data.put("trade_type", "JSAPI");  // 此处指定为扫码支付

        try {
            Map resultMap=new HashMap();
            Map<String, String> rMap = wxpay.unifiedOrder(data);
            System.out.println("统一下单接口返回: " + rMap);
            String return_code = (String) rMap.get("return_code");
            String result_code = (String) rMap.get("result_code");
            String nonceStr = WXPayUtil.generateNonceStr();
            resultMap.put("nonceStr", nonceStr);
            Long timeStamp = System.currentTimeMillis() / 1000;
            if ("SUCCESS".equals(return_code) && return_code.equals(result_code)) {
                String prepayid = rMap.get("prepay_id");
                resultMap.put("package", "prepay_id="+prepayid);
                resultMap.put("signType", "MD5");
                //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                resultMap.put("timeStamp", timeStamp + "");
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                resultMap.put("appId",myConfig.getAppID());

                String sign = WXPayUtil.generateSignature(resultMap, myConfig.getKey());
                resultMap.put("paySign", sign);
                System.out.println("生成的签名paySign : "+ sign);
                return new ReturnData(resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ReturnData();
    }



}
