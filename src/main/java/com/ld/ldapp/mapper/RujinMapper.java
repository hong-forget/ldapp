package com.ld.ldapp.mapper;

import com.ld.ldapp.domain.Rujin;
import org.apache.ibatis.annotations.*;

public interface RujinMapper {



    @Insert("insert into rujin(name, img, account, sum) values(#{name},#{img},#{account},#{sum})")
    public int rujinAdd(Rujin rujin);



}
