package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AdvertisementVO {
    private String id;
    private String companyId;
    private String image;
    private Timestamp startDate;
    private Timestamp endDate;
    private String adFee;
}
