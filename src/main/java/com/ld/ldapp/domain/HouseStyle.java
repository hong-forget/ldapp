package com.ld.ldapp.domain;

import lombok.Data;

@Data
public class HouseStyle {

    private Integer id;
    private String styleName;
    private Integer houseArea;
    private Integer unitPrice;
    private Integer totalPrice;
    private String img;
    private Integer buildingId;

    private String orientation;
    private Integer onsell;
}
