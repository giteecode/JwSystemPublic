package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @since 2023-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "是否外链")
    private Boolean iFrame;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "组件")
    // 组件路径
    private String component;

    @ApiModelProperty(value = "上级菜单ID")
    private Long pid;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    private String path;

    // 是否缓存
    private Boolean cache;

    // 是否隐藏
    private Boolean hidden;

    // 组件名称
    private String componentName;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp createTime;

    // 权限
    private String permission;
    // 0：目录，1：页面
    private Integer type;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<Menu> children;


}
