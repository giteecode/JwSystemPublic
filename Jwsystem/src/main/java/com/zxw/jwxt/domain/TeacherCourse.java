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
 * @since 2020-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TeacherCourse对象", description="")
public class TeacherCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "学期")
    private String teamId;

    @ApiModelProperty(value = "教师编号")
    private String teacherId;

    @ApiModelProperty(value = "周时")
    private String weekId;

    @ApiModelProperty(value = "课时")
    private String sectionId;

    @ApiModelProperty(value = "是否结课")
    private Integer end = 0;

    @ApiModelProperty(value = "是否可以评论")
    private Integer comment = 0;

    @ApiModelProperty(value = "课程编号")
    private String cid;

    @ApiModelProperty(value = "已选人数")
    private Integer people = 0;

    @ApiModelProperty(value = "总人数")
    private Integer totalPeople;

    @ApiModelProperty(value = "上课教室")
    private String classroom;

    private Integer status = 1;

    @ApiModelProperty(value = "审核状态")
    private Integer apply = 0;

    @ApiModelProperty(value = "班级id")
    private String classesId;

    @ApiModelProperty(value = "是否绑定班级")
    private Integer isClasses = 0;
}
