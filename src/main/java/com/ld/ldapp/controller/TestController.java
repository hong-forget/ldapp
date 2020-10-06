package com.ld.ldapp.controller;

import com.ld.ldapp.domain.Rujin;
import com.ld.ldapp.domain.User;
import com.ld.ldapp.mapper.RujinMapper;
import com.ld.ldapp.properties.AliyunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
@ResponseBody
public class TestController {

    @Autowired
    private User user;

    @Autowired
    private RujinMapper rujinMapper;


    @Resource(name="user1")
    private User user1;

    @GetMapping("/test")
    public String test(Integer id,String sign){
        return "asd"+id+sign;
    }

    @GetMapping("/user")
    public User user(){
        return user1;
    }

    @Value("${picPath}")
    private String picPath;

    @GetMapping("/picPath")
    public String picPath(){
        return picPath;
    }

    @Autowired
    private AliyunProperties properties;

    @GetMapping("aliyun")
    public AliyunProperties aliyun(){
        return properties;
    }

    @PostMapping("/uploadServlet")
    public String index(Rujin rujin){
        System.out.println(rujin);
        boolean flag=false;
        try {
            rujinMapper.rujinAdd(rujin);
        }catch (Exception e){
            flag=true;
        }


        if(flag){
            return "0";
        }

        return "1";
    }

    @PostMapping("/uploadFile")
    public String index(String c,String b,MultipartFile a,@RequestHeader("Authorization") String Authorization) throws IOException {
        System.out.println(Authorization);

        File file = new File(picPath+a.getOriginalFilename());
        a.transferTo(file);
        boolean flag=false;
        try {

        }catch (Exception e){
            flag=true;
        }


        if(flag){
            return "0";
        }

        return "1";
    }

}
