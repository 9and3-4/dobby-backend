package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
public class CompanyAdListVO {
    private String id;
    private String companyId;
    private String image;
    private Timestamp startDate;
    private Timestamp endDate;
    private String adfee;
}
