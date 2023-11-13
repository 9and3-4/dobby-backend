package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CompanyPostVO {
    private String id;
    private String customerId;
    private String topicId;
    private String title;
    private String content;
    private Timestamp writeDate;
    private int viewCount;
    private int likeCount;

}
