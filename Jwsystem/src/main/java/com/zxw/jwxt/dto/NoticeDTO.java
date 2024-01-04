package com.zxw.jwxt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @PACKAGE_NAME: com.zxw.jwxt.dto
 * @author: Administrator
 * @DATE: 2020/1/7 10:21:48
 **/
@Data
public class NoticeDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String content;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String publisher;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String unId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String sid;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tid;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String username;
}
