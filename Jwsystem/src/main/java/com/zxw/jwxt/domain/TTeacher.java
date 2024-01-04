package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TTeacher对象", description = "")
public class TTeacher extends UserRealm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师编号")
    @TableId(value = "tid", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "tname")
    private String username;

    @ApiModelProperty(value = "性别")
    private String tsex;

    @ApiModelProperty(value = "年龄")
    private String tage;

    @ApiModelProperty(value = "状态：0表示在职，1表示辞职，2表示离职")
    private String status;

    @ApiModelProperty(value = "所属院系")
    private String collegeId;

    @ApiModelProperty(value = "权限")
    private String qx;

    @ApiModelProperty(value = "工作日期")
    @TableField("beginTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty(value = "薪水")
    private Double salary;

    @ApiModelProperty(value = "政治面貌")
    @TableField("politicalStatus")
    private String politicalStatus;

    @ApiModelProperty(value = "家庭住址")
    private String address;

    @ApiModelProperty(value = "身份证号")
    private String idcard;

    @Override
    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setQx(String qx) {
        this.qx = qx;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
