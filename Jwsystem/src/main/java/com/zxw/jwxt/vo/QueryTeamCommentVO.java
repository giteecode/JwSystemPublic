package com.zxw.jwxt.vo;

import lombok.Data;

/**
 * @PACKAGE_NAME: com.zxw.jwxt.vo
 * @author: Administrator
 * @DATE: 2020/1/4 18:10:38
 **/
@Data
public class QueryTeamCommentVO extends BaseQueryParam{
    private String cid;
    private String sid;
    private String tid;
    private String teamId;
    private String commentId;
}
