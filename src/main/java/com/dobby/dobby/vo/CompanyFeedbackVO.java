package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CompanyFeedbackVO {
    // 기업 리뷰 내 현직자들의 회사 리뷰
    private String id;
    private String companyId;
    private int rating;
    private String Content;
    private Timestamp writeDate;
}
