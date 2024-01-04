package com.zxw.jwxt.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TScore对象", description = "")
public class TScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseId;

    private String studentId;

    private String teacherId;
    @ApiModelProperty("考勤分")
    private Integer attendance;
    @ApiModelProperty("平时分")
    private Integer usually;
    @ApiModelProperty("期末分")
    private Integer exam;
    @ApiModelProperty("总成绩")
    private Integer score;
    @ApiModelProperty("绩点")
    private BigDecimal point;

    private Integer absent;

    @ApiModelProperty("是否已评分")
    private Integer status;

}
