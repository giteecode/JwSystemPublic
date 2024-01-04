package com.zxw.jwxt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @PACKAGE_NAME: com.zxw.jwxt.dto
 * @author: Administrator
 * @DATE: 2020/1/7 10:21:48
 **/
@Data
public class ClassesDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String classname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String specialtyId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String collegeId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer people;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String gradeId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String year;
}
