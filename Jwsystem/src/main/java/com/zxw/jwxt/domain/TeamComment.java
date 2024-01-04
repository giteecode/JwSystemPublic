package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxw
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TeamComment对象", description="")
public class TeamComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "课程编号")
    private String cid;

    @ApiModelProperty(value = "学生编号")
    private String sid;

    @ApiModelProperty(value = "教师编号")
    private String tid;

    @ApiModelProperty(value = "评分")
    private Integer remark;

    @ApiModelProperty(value = "已评")
    private Integer status;

    @ApiModelProperty(value = "评价id")
    private String commentId;


}
