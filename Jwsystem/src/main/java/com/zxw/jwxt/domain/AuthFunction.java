package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "AuthFunction对象", description = "")
public class AuthFunction implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private String code;

    private String description;

    private String page;

    private String generatemenu;

    private Integer zindex;

    private String pid;


}
