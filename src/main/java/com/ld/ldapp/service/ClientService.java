package com.ld.ldapp.service;

import com.ld.ldapp.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientMapper clientMapper;

    public List<HashMap> getStatistics(Integer userId){
       return clientMapper.getStatistics(userId);
    }
}
