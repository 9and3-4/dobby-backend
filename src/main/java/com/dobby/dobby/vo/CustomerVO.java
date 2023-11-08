package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CustomerVO {
    private String id;
    private String companyId;
    private String name;
    private String nickName;
    private String contactNumber;
    private String email;
    private String password;
    private String role;
    private String isActive;
}
