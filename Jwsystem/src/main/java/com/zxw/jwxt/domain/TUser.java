package com.zxw.jwxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "TUser对象", description = "")
public class TUser extends UserRealm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String qx;

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String status;
    @TableField(value = "collegeId")
    private String collegeId;

    @TableField(exist = false)
    private String collegeName;

    @TableField(value = "nickName")
    private String nickName;

    private String phone;

    private String email;

    private String avatar;

    private String sex;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
