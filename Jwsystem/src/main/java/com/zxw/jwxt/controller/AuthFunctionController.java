package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.MenuMeta;
import com.zxw.common.pojo.MenuNode;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.AuthFunction;
import com.zxw.jwxt.domain.Menu;
import com.zxw.jwxt.service.AuthFunctionService;
import com.zxw.jwxt.service.AuthRoleService;
import com.zxw.jwxt.service.MenuService;
import com.zxw.jwxt.vo.QueryFunctionVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/api/function")
public class AuthFunctionController extends BaseController {
    @Autowired
    private AuthFunctionService functionSerivce;

    @Autowired
    private MenuService menuService;
    @Autowired
    private AuthRoleService roleService;

    /**
     * 权限列表
     *
     * @param functionQueryParam
     * @return
     * @throws IOException
     */
    @GetMapping("/pageQuery")
    public List<Menu> pageQuery(QueryFunctionVO functionQueryParam) {
        List<Menu> menuNodes = new ArrayList<>();
        List<Menu> list = null;
        IPage iPage = menuService.pageQuery(functionQueryParam);
        if (functionQueryParam.getRoleId() != null) {
            list = roleService.findMenuByRole(functionQueryParam.getRoleId());
        } else {
            list = iPage.getRecords();
        }
        list.forEach(menu -> {
            if (menu.getPid() == 0) {
                menu.setChildren(new ArrayList<>());
                menuNodes.add(menu);
            }
        });
        List<Menu> nodes = this.generateTree(menuNodes, list);
        return nodes;
    }

    /**
     * 权限列表
     *
     * @return
     */
    @GetMapping("/listajax")
    public List<AuthFunction> listajax() {
        List<AuthFunction> list = functionSerivce.findAll();
        return list;
    }

    /**
     * 查询角色对应的权限
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/queryFunctionByRole")
    public List<Integer> queryFunctionByRole(String id) {
        List<Integer> list = functionSerivce.queryFunctionByRole(id);
        return list;
    }

    /**
     * 添加权限
     *
     * @param function
     * @return
     */
    @PostMapping("/add")
    public RS add(@RequestBody AuthFunction function) {
        RS rs = functionSerivce.save(function);
        return rs;
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public RS delete(String id) {
        RS rs = functionSerivce.delete(id);
        return rs;
    }

    /**
     * 更新权限
     *
     * @param function
     */
    @PostMapping("/update")
    public RS update(AuthFunction function) {
        RS rs = functionSerivce.updateFunction(function);
        return rs;
    }

    /**
     * 生成菜单
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/menu")
    public List findMenu() {
        List<MenuNode> menuNodes = new ArrayList<>();
        List<Menu> list = functionSerivce.findMenu(getUserId());
        list.forEach(menu -> {
            if (menu.getPid() == 0) {
                MenuNode menuNode = new MenuNode();
                menuNode.setId(menu.getId());
                menuNode.setName(ObjectUtils.isNotEmpty(menu.getComponentName()) ? menu.getComponentName() : menu.getName());
                menuNode.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
                menuNode.setHidden(menu.getHidden());
                menuNode.setPath("/" + menu.getPath());
                menuNode.setAlwaysShow(true);
                menuNode.setPid(menu.getPid());
                menuNode.setMeta(new MenuMeta(menu.getName(), menu.getIcon()));
                menuNode.setRedirect("noredirect");
                menuNode.setChildren(new ArrayList<>());
                menuNodes.add(menuNode);
            }
        });
        List<MenuNode> nodes = this.generateMenu(menuNodes, list);
        return nodes;
    }

    private List<MenuNode> generateMenu(List<MenuNode> menuNodes, List<Menu> list) {
        List<MenuNode> collect = menuNodes.stream().map((e) -> {
            for (int i = 0; i < list.size(); i++) {
                if (!"".equals(list.get(i).getPid()) && list.get(i).getPid() != 0) {
                    if (list.get(i).getPid().equals(e.getId())) {
                        MenuNode menuNode = new MenuNode();
                        menuNode.setId(list.get(i).getId());
                        menuNode.setName(ObjectUtils.isNotEmpty(list.get(i).getComponentName()) ? list.get(i).getComponentName() : list.get(i).getName());
                        menuNode.setComponent(list.get(i).getComponent());
                        menuNode.setHidden(list.get(i).getHidden());
                        menuNode.setPath(list.get(i).getPid() == 0 ? "/" : list.get(i).getPath());
                        menuNode.setPid(list.get(i).getPid());
//                        menuNode.setAlwaysShow(true);
//                        menuNode.setRedirect("noredirect");
//                        menuNode.setChildren(null);
                        menuNode.setMeta(new MenuMeta(list.get(i).getName(), list.get(i).getIcon()));
                        e.getChildren().add(menuNode);
                    }
                    // 递归进入子菜单
                    if (e.getChildren() != null) {
                        generateMenu(e.getChildren(), list);
                    }
                }
            }
            return e;
        }).collect(Collectors.toList());
        return collect;
    }

    private List<Menu> generateTree(List<Menu> menuNodes, List<Menu> list) {
        List<Menu> collect = menuNodes.stream().map((e) -> {
            for (int i = 0; i < list.size(); i++) {
                if (!"".equals(list.get(i).getPid()) && list.get(i).getPid() != 0) {
                    if (list.get(i).getPid().equals(e.getId())) {
                        e.getChildren().add(list.get(i));
                    }
                    // 递归进入子菜单
                    if (e.getChildren() != null) {
                        generateTree(e.getChildren(), list);
                    }
                }
            }
            return e;
        }).collect(Collectors.toList());
        return collect;
    }

}
