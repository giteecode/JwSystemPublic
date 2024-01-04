package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "TSpecialty对象", description = "")
public class TSpecialty implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.INPUT)
    private String id;

    private String name;

    private String collegeId;

    private String status;
    // 学制
    private String time;
    // 授予学位类型
    private String category;

    @TableField(exist = false, fill = FieldFill.DEFAULT)
    private TCollege college;
    @TableField(exist = false, fill = FieldFill.DEFAULT)
    private String cname;
    @TableField(exist = false, fill = FieldFill.DEFAULT)
    private String cstatus;

}
