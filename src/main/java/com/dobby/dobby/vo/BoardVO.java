package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class BoardVO {
    private String major;
    private String sub;
    private Long boardId;
    private String userId;
    private String customerId;
    private Long topicId;
    private String title;
    private String content;
    private String img;
    private Date writeDate;
}