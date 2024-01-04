package com.zxw.jwxt.vo;

import lombok.Data;

/**
 * @author zxw
 * @date 2020/2/1 22:09
 */
@Data
public class QueryNoticeVO {
    private String title;
    private Integer status;
    private String publisher;
    private String content;
    private String htmlContent;
    private Integer user;
}
