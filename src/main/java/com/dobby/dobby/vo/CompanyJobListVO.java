package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CompanyJobListVO {
    // 기업 마이페이지 내 채용 공고 리스트

    private String id;
    private String companyId;
    private String title;
    private String description;
    private String qualification;
    private Timestamp deadLine;
    private String image;
    private String isEnabled;

}
