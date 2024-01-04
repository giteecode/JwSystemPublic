package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zxw
 * @date 2019/11/7 22:01
 */
@Data
public class UserRealm {
    @TableField(exist = false)
    private String id;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String role;
    @TableField(exist = false)
    private String salt;
    @TableField(exist = false)
    private String qx;
    @TableField(exist = false)
    private String collegeId;
    @TableField(exist = false)
    private String token;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */
    @TableField(exist = false)
    private Set<String> roles = new HashSet<>();
    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @TableField(exist = false)
    private Set<String> perms = new HashSet<>();

    @TableField(exist = false)
    private List<AuthFunction> menuList;

}
