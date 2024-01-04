package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Data
@ApiModel(value = "TStudent对象", description = "")
public class TStudent extends UserRealm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学号")
    @TableId(value = "sid",type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "sname")
    private String username;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "户籍")
    private String scity;

    @ApiModelProperty(value = "权限")
    private String qx;

    @ApiModelProperty(value = "考勤")
    private Integer absent;

    @ApiModelProperty(value = "班级id")
    private String classesId;

    @ApiModelProperty(value = "学年id")
    private String gradeId;

    @ApiModelProperty(value = "入学日期")
    @TableField("beginTime")
    private Date beginTime;

    @ApiModelProperty(value = "离校日期")
    @TableField("endTime")
    private Date endTime;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "身份证")
    private String idcard;

    @ApiModelProperty(value = "家庭地址")
    private String address;

    @ApiModelProperty(value = "政治面貌")
    @TableField("politicalStatus")
    private String politicalStatus;

}
