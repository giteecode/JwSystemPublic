package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.AuthRole;
import com.zxw.jwxt.domain.Menu;
import com.zxw.jwxt.domain.RolesMenus;
import com.zxw.jwxt.service.AuthRoleService;
import com.zxw.jwxt.vo.QueryRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/api/role")
public class AuthRoleController extends BaseController {

    @Autowired
    private AuthRoleService roleService;

    @PostMapping("/add")
    public RS add(@RequestBody AuthRole role) {
        RS rs = roleService.save(role);
        return rs;
    }

    @PutMapping("/update")
    public RS update(String ids, String roleId) {
        RS rs = roleService.update(ids, roleId);
        return rs;
    }

    @PutMapping("/edit")
    public RS edit(@RequestBody AuthRole role) {
        RS rs = roleService.edit(role);
        return rs;
    }

    @DeleteMapping("/delete")
    public RS delete(@RequestParam("roleId") String roleId) {
        RS rs = roleService.deleteRole(roleId);
        return rs;
    }

    @GetMapping("/pageQuery")
    public TableResponse pageQuery(QueryRoleVO roleQueryParam) {
        IPage result = roleService.pageQuery(roleQueryParam);
        TableResponse response = TableResponse.of(result);
        return response;
    }

    @GetMapping("/listajax")
    public List<AuthRole> listajax(QueryRoleVO roleQueryParam) {
//        List<AuthRole> list = roleSerivce.findAll();
//        return list;
        List<AuthRole> list = roleService.listajax(roleQueryParam);
        return list;
    }

    @GetMapping("/findMenuByRole")
    public List<Menu> findMenuByRole(@RequestParam("roleId") String roleId) {
        List<Menu> list = roleService.findMenuByRole(roleId);
        return list;
    }

    @PostMapping("/saveMenu")
    public RS saveMenu(@RequestBody RolesMenus rolesMenus) {
        RS rs = roleService.saveMenu(rolesMenus);
        return rs;
    }
}
