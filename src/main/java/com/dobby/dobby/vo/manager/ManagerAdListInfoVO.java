package com.dobby.dobby.vo.manager;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ManagerAdListInfoVO {
    private String advertisementID;
    private String companyId;
    private String companyName;
    private String image;
    private Timestamp startDate;
    private Timestamp endDate;
    private String adFee;
    private int isEnabled;
}
