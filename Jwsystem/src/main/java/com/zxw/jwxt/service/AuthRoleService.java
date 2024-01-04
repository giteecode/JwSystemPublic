package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.AuthRole;
import com.zxw.jwxt.domain.Menu;
import com.zxw.jwxt.domain.RolesMenus;
import com.zxw.jwxt.mapper.AuthRoleMapper;
import com.zxw.jwxt.vo.QueryRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class AuthRoleService extends BaseService {

    @Autowired
    private AuthRoleMapper roleMapper;

    @Autowired
    private RolesMenusService rolesMenusService;

    public IPage pageQuery(QueryRoleVO roleQueryParam) {
        IPage iPage = this.BaseQuery(roleQueryParam);
        return iPage;
    }

    public RS save(AuthRole role) {
        int insert = roleMapper.insert(role);
        return insert == 1 ? RS.ok() : RS.error("角色添加失败");
    }

    public RS update(String ids, String roleId) {
        List<String> list = roleMapper.queryFunctionByRole(roleId);
        for (int i = 0; i < list.size(); i++) {
            roleMapper.deleteRoleFunction(list.get(i));
        }
        String[] id = ids.split(",");
        for (String functionId : id) {
            roleMapper.insertFunction(roleId, functionId);
        }
        return RS.ok();
    }

    public RS deleteRole(String roleId) {
        roleMapper.deleteFunction(roleId);
        roleMapper.deleteRole(roleId);
        return RS.ok();
    }

    public IPage BaseQuery(QueryRoleVO baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper queryWrapper = getWrapper(baseQueryParam, null);
        IPage iPage = roleMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    public List<AuthRole> listajax(QueryRoleVO roleQueryParam) {
        return roleMapper.selectList(this.getWrapper(roleQueryParam, null));
    }

    public List<Menu> findMenuByRole(String roleId) {
        List<Menu> list = roleMapper.findMenuByRole(roleId);
        return list;
    }

    public RS edit(AuthRole role) {
        int i = roleMapper.updateById(role);
        return i == 0 ? RS.ok() : RS.error("角色修改失败");
    }

    public RS saveMenu(RolesMenus rolesMenus) {
        return rolesMenusService.saveMenu(rolesMenus);
    }
}
