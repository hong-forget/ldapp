package com.ld.ldapp.controller;

import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.Adprepare;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.AdPrepaerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdController {




    @Autowired
    private AdPrepaerMapper adPrepaerMapper;






    @PostMapping("/ad/prepare")
    public ReturnData prepare(Integer pagesize){

        PageHelper.startPage(0, pagesize);
        List<Adprepare> list=adPrepaerMapper.findAll();

        return new ReturnData(list);
    }

    @PostMapping("/ad/main")
    public ReturnData main(Integer id){


       Adprepare adprepare=adPrepaerMapper.findOneById(id);

        return new ReturnData(adprepare);
    }






}
