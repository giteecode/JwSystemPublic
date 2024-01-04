package com.zxw.jwxt.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author zxw
 * @date 2019/11/7 19:28
 */
@Data
public class BaseQueryParam {
    private Integer offset = 0;
    private Integer limit = -1;
    private String sort;
    private String groupBy;
    private boolean ASC = true;
    private String keyword;
    private String status;
}
