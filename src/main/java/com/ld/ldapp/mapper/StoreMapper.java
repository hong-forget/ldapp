package com.ld.ldapp.mapper;

import com.ld.ldapp.domain.Store;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface StoreMapper {

    @Select("select * from stores where storecode=#{storecode}")
    List<Map> findAll(String storecode);
}
