package com.zxw.jwxt.service;

import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.RolesMenus;
import com.zxw.jwxt.mapper.RolesMenusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxw
 * @since 2023-12-24
 */
@Service
public class RolesMenusService extends BaseService {
    @Autowired
    private RolesMenusMapper rolesMenusMapper;

    public RS saveMenu(RolesMenus rolesMenus) {
        List<RolesMenus> list = rolesMenusMapper.selectList(this.queryOne("role_id", rolesMenus.getRoleId()));
        if (list.size() != 0) {
            list.forEach(e -> {
                rolesMenusMapper.delete(this.queryOne("role_id",e.getRoleId()));
            });
        }
        for (int i = 0; i < rolesMenus.getMenuIds().size(); i++) {
            rolesMenusMapper.insert(new RolesMenus(rolesMenus.getRoleId(), rolesMenus.getMenuIds().get(i)));
        }
        return RS.ok();
    }
}
