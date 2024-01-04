package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "TComments对象", description = "")
public class TComments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    @TableField(value = "team_id")
    private String teamId;

    @TableField(value = "commentType")
    private Integer commentType = 0;

    @TableField(value = "commentBatch")
    private String commentBatch;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "beginTime")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "endTime")
    private Date endTime;
}
