package com.zxw.jwxt.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zxw
 * @date 2019/11/21 13:51:07
 */
@Data
public class QueryStudentVO extends BaseQueryParam {
    List<String> studentIds;
    String id;
    // 课程id
    private String cid;
    // 成绩id
    private String sid;
    private String password;
    private String sname;
    private String sex;
    private String scity;
    private String qx;
    private Integer absent;
    private String classesId;
    private String gradeId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String phone;
    private String idcard;
    private String address;
    private String politicalStatus;
    private String classname;
    private String teamId;
}
