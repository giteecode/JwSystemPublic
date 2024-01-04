package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zxw
 * @since 2020-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CourseComment对象", description = "")
public class CourseComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "问题1评分")
    private Integer q1;

    private Integer q2;

    private Integer q3;

    private Integer q4;

    private Integer q5;

    private Integer q6;

    private Integer q7;

    private Integer q8;

    private Integer q9;

    private Integer q10;

    @TableField(exist = true, value = "scontent")
    private String scontent;
    @TableField(exist = true, value = "tcontent")
    private String tcontent;

    private String tmId;


}
