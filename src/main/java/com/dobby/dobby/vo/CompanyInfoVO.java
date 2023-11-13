package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CompanyInfoVO {
    // 메뉴 : 기업 소개, 리뷰, 게시글, 채용, 즐겨찾기
    // 필요한 테이블 : COMPANY, REVIEW, POST, JOBPOSTING, FAVORITE

    // 1. 기업 정보 확인 하는 게시판

    private String id;
    private String name;
    private String sizeScale;
    private String ceo;
    private String contactNumber;
    private String url;
    private String businessCategory;
    private String address;
    private String foundedYear;
    private String staffSize;
    private String annualIncome;
    private String profile;
    private String logo;


}


