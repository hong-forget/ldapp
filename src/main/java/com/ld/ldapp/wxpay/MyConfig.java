package com.ld.ldapp.wxpay;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class MyConfig implements WXPayConfig {


    private byte[] certData;

    public MyConfig() throws Exception {
        String certPath = "c:/mysql/keys/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return "wx6a6bbc33bcc54462";
    }

    public String getMchID() {
        return "1515129701";
    }

//    public String getKey() {
//        return "uwyemxysk20d830sk284m86543ms9832";
//    }

    public String getKey() {
        return "uekjsm89kwki894k5hjdmiuehr86g94m";
    }

//    public String getAppSrt() {
//        return "a25714c9ab53714281ea04111d4b1d2a";
//    }


    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }




}
