package com.dobby.dobby.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class CommentVO {
    private Long id;
    private Long customerId;
    // getCustomerId 메서드 수정
    public Long getCustomerId() {
        return customerId != null ? customerId : 0L;
    }
    private Long postId;
    private Date writeDate;
    private String content;
    private Long topicId;
}