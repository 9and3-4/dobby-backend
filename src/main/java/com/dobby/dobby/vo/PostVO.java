package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PostVO {
    private String id;
    private String customerId;
    private String topicId;
    private String title;
    private String content;
    private String nickName;
    private Timestamp writeDate;
    private int viewCount;
    private int likeCount;
}
