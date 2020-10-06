package com.ld.ldapp.domain;

import lombok.Data;

@Data
public class Property {

    private Integer id;
    private Integer buildingId;
    private Integer periodInt;
    private Integer propertyType;
    private Integer propertyPrice;
    private String propertyCompany;
    private Integer coverArer;
    private String buildingArea;
    private Integer holdsTotal;
    private Integer parkingTotal;
    private Float plotRate;
    private Float greeningRate	;

}
