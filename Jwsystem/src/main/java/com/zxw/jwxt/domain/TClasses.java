package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TClasses对象", description="")
public class TClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "教室名称")
    private String classname;

    @ApiModelProperty(value = "专业id")
    private String specialtyId;

    @ApiModelProperty(value = "学院id")
    private String collegeId;

    @ApiModelProperty(value = "人数")
    private Integer people;

    @ApiModelProperty(value = "所属年级")
    private String gradeId;

    @ApiModelProperty(value = "入学年份")
    private String year;


}
