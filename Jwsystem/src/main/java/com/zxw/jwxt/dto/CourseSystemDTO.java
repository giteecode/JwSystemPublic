package com.zxw.jwxt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author zxw
 * @date 2020/1/6 19:54
 */
@Data
public class CourseSystemDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String teamId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nname;
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
