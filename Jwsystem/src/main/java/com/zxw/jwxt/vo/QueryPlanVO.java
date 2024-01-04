package com.zxw.jwxt.vo;

import lombok.Data;

/**
 * @author zxw
 * @date 2020/1/5 16:47
 */
@Data
public class QueryPlanVO extends BaseQueryParam {
    private String specialtyId;
    private String planId;
    private String[] cids;
    private String yearId;
}
