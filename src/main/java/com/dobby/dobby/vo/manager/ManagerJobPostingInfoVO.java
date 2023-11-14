package com.dobby.dobby.vo.manager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerJobPostingInfoVO {
    private String jobPostingId;
    private String companyId;
    private String companyName;
    private String title;
    private String description;
    private String qualification;
    private String deadline;
    private String image;
    private int isEnabled;
}
