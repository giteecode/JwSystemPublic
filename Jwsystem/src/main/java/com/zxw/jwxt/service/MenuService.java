package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.Menu;
import com.zxw.jwxt.mapper.MenuMapper;
import com.zxw.jwxt.vo.BaseQueryParam;
import com.zxw.jwxt.vo.QueryFunctionVO;
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
public class MenuService extends BaseService {

    @Autowired
    private MenuMapper menuMapper;

    public IPage pageQuery(QueryFunctionVO functionQueryParam) {
        IPage iPage = this.BaseQuery(functionQueryParam);
        return iPage;
    }

    public IPage BaseQuery(QueryFunctionVO functionQueryParam) {
        Page page = getPage(functionQueryParam);
        QueryWrapper queryWrapper = getWrapper(functionQueryParam, null);
        IPage iPage = menuMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    public RS add(Menu menu) {
        int insert = menuMapper.insert(menu);
        return insert == 1 ? RS.ok() : RS.error("添加权限失败");
    }

    public List<Menu> listajax(BaseQueryParam baseQueryParam) {
        return menuMapper.selectList(this.getWrapper(baseQueryParam, null));
    }

    public RS delete(Long menuId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid", menuId);
        queryWrapper.or(true);
        queryWrapper.eq("id", menuId);
        queryWrapper.orderByDesc("id");
        List<Menu> list = menuMapper.selectList(queryWrapper);
        int i = 0;
        if (list.size() != 0) {
            for (int j = 0; j < list.size(); j++) {
                i = menuMapper.deleteById(list.get(i).getId());
            }
        }
        return i == 0 ? RS.error("删除权限失败") : RS.ok();
    }

    public RS edit(Menu menu) {
        int i = menuMapper.updateById(menu);
        return i == 1 ? RS.ok() : RS.error("权限修改失败");
    }
}
