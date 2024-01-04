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
@ApiModel(value="TCourse对象", description="")
public class TCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String name;
    @ApiModelProperty(value = "学分")
    private BigDecimal credit;
    @ApiModelProperty(value = "课程性质")
    private String natureId;
    @ApiModelProperty(value = "总学时")
    private Integer totalTime;
    @ApiModelProperty(value = "考核方式")
    private String wayId;
    @ApiModelProperty(value = "课程属性")
    private String cstatusId;
    @ApiModelProperty(value = "状态")
    private Integer status = 1;
    @ApiModelProperty(value = "是否考试")
    @TableField("isExam")
    private Integer isExam;
    @ApiModelProperty(value = "开课学院")
    private String collegeId;
    @ApiModelProperty(value = "学年")
    private String yearId;
    @ApiModelProperty(value = "课程体系")
    private String systemId;

}
