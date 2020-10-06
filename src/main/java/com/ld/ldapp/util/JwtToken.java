package com.ld.ldapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class JwtToken {

    public static String sha256(String orig){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(orig.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;

    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }



        public static String generateToken(String key){

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 24 * 60 * 60 * 1000*7);
        String jwtString= Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(key)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, "ldapp-key1")
                .compact();




        return jwtString;
    }

    public static Claims getClaimByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        String[] header = token.split("Bearer");
        token = header[1].trim();
        try {
            return Jwts.parser()
                    .setSigningKey("ldapp-key1")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
           // logger.debug("validate is token error ", e);
            return null;
        }
    }

    public static boolean isTokenExpired(Date expiration) {
        Long l=expiration.getTime() -new Date().getTime();
        return l<1000*60*60*10;
    }

}
