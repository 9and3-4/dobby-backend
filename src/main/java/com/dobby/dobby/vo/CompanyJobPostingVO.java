package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CompanyJobPostingVO {
    private String id;
    private String companyId;
    private String title;
    private String description;
    private String qualification;
    private Timestamp deadline;
    private String image;
}
