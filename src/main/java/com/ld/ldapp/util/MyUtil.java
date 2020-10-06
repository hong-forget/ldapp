package com.ld.ldapp.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.Interceptor.AdminInterceptor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

    public static String dataTime(Date data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String timeStr=sdf.format(data);
            return timeStr;
        }catch (Exception e){
            return null;
        }


    }


    public static String renameFile(String filePath, String fileName) {
        SimpleDateFormat fmdate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String oldFileName = filePath+"/"+fileName;
        File oldFile = new File(oldFileName);
        String newFileName = filePath+"/"+fileName.split("\\.")[0]+fmdate.format(new Date())+"."+fileName.split("\\.")[1];
        File newFile = new File(newFileName);
        if (oldFile.exists() && oldFile.isFile()) {
            oldFile.renameTo(newFile);
        }
        return newFileName;
    }

    public static void setPage(JSONObject param){
        if(param==null) param=new JSONObject();
        Integer pageNum=param.getInteger("pageNum");
        Integer pageSize=param.getInteger("pageSize");
        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);
    }

    public static JSONObject localVal(){
        return AdminInterceptor.localVar.get();
    }
}
