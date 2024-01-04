package com.zxw.jwxt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author zxw
 * @date 2020/1/6 19:54
 */
@Data
public class CourseCommentDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q1;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q2;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q3;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q4;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q5;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q6;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q7;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q8;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q9;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer q10;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String scontent;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tcontent;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tmId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String remark;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String courseName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String csname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String coname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String systemName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer totalTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer credit;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer point;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String systemId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String courseId;
}
