package com.ld.ldapp.domain;

import lombok.Data;

@Data
public class Chat {
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private String context;
    private String time;
    private Integer readed;
    private Integer uId;
    private String type;

}
