package com.zxw.jwxt.vo;

import lombok.Data;

/**
 * @author zxw
 * @date 2019/11/24 14:20
 */
@Data
public class QuerySpecialtyVO extends BaseQueryParam {
    private String id;

    private String name;

    private String collegeId;

    private String status;
    // 学制
    private String time;
    // 授予学位类型
    private String category;

    private String cname;

    private String cstatus;
}
