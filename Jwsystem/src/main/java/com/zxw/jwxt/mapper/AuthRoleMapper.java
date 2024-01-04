package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxw.jwxt.domain.AuthRole;
import com.zxw.jwxt.domain.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Mapper
public interface AuthRoleMapper extends BaseMapper<AuthRole> {
    @Insert("INSERT INTO `role_function` VALUES (#{0},#{1})")
    int RoleinsertFunction(String roleId, String functionId);

    @Select("SELECT rf.`function_id` FROM `auth_role` ar,`role_function` rf WHERE ar.`id` = rf.`role_id` AND ar.`id`=#{id};")
    List<String> queryFunctionByRole(String roleId);

    @Delete("delete from role_function where function_id = #{id}")
    void deleteRoleFunction(@Param("id") String id);

    @Insert("insert into role_function values(#{functionId},#{roleId})")
    void insertFunction(@Param("functionId") String functionId, @Param("roleId") String roleId);

    @Delete("delete from auth_role where id = #{roleId}")
    void deleteRole(@Param("roleId") String roleId);

    @Delete("delete from role_function where role_id = #{roleId}")
    void deleteFunction(@Param("roleId") String roleId);

    @Select("SELECT * FROM `roles_menus` rm,`menu` m WHERE rm.`menu_id` = m.`id` AND rm.`role_id` = #{roleId}")
    List<Menu> findMenuByRole(@Param("roleId") String roleId);
}
