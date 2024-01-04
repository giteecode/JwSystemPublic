package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
@ApiModel(value = "RolesMenus对象", description = "")
@AllArgsConstructor
@NoArgsConstructor
public class RolesMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    private String menuId;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @TableField(exist = false)
    private List<String> menuIds;

    public RolesMenus(String roleId,String menuId){
        this.roleId = roleId;
        this.menuId = menuId;
    }



}
