package com.ld.ldapp.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.RecordMapping;
import com.ld.ldapp.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RecordController {

    @Autowired
    RecordMapping recordMapping;
    @PostMapping("/record/list")
    public ReturnData list(@RequestBody(required = false)JSONObject param){
        MyUtil.setPage(param);

        return new ReturnData(new PageInfo<>(recordMapping.list(param)));
    }
}
