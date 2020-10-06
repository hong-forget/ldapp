package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.PhotoAlbumMapepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoAlbumController {

    @Autowired
    PhotoAlbumMapepr photoAlbumMapepr;

    @PostMapping("/photoalbum/add")
    public ReturnData photoalbumAdd(@RequestBody JSONObject param){

        if(param.getInteger("id")==null){
            return new ReturnData(photoAlbumMapepr.add(param));
        }else{
            return new ReturnData(photoAlbumMapepr.update(param));
        }
    }

    @PostMapping("/photoalbum/setthumbnail")
    public ReturnData setThumbnail(@RequestBody JSONObject param){

        return new ReturnData(photoAlbumMapepr.setThumbnail(param));

    }

    @PostMapping("/photoalbum/find")
    public ReturnData photoalbumFind(@RequestBody JSONObject param){

        return new ReturnData(photoAlbumMapepr.photoalbumFind(param));

    }


}
