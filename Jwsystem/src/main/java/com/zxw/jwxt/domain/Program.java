package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Program对象", description="")
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private String htmlName;

    private String yearId;

    private String specialtyId;

    private Date createTime;

    private Date updateTime;

    private Integer status = 1;


}
