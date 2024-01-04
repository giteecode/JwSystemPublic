package com.zxw.jwxt.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zxw
 * @date 2019/11/21 13:51:07
 */
@Data
public class QueryClassesVO extends BaseQueryParam {

    private List<String> cIds;

    private String id;

    private String classname;

    private String specialtyId;

    private String collegeId;

    private Integer people;

    private String gradeId;

    private String year;

    // 院系名称
    private String cname;

    // 专业名称
    private String sname;

    // 年级
    private String gname;
}
