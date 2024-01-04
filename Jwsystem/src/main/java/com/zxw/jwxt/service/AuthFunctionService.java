package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.AuthFunction;
import com.zxw.jwxt.domain.Menu;
import com.zxw.jwxt.mapper.AuthFunctionMapper;
import com.zxw.jwxt.vo.QueryFunctionVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class AuthFunctionService extends BaseService {

    @Autowired
    private AuthFunctionMapper functionMapper;

    public List<AuthFunction> findAll() {
        List<AuthFunction> list = functionMapper.findAll();
        return list;
    }

    public List<AuthFunction> findListByTeacherid(String tid) {
        List<AuthFunction> list = functionMapper.findListByTeacherid(tid);
        if (list.size() != 0) {
            return list;
        }
        return null;
    }

    public List<AuthFunction> findListByStudentid(String sid) {
        List<AuthFunction> list = functionMapper.findListByStudentid(sid);
        if (list.size() != 0) {
            return list;
        }
        return null;
    }

    public List<Menu> findMenu(String userId) {
        String RadioButtonList1 = (String) SecurityUtils.getSubject().getSession().getAttribute("RadioButtonList1");
        List list = null;
        if ("管理员".equals(RadioButtonList1)) {
            list = functionMapper.findMenuByUserid(userId);
        } else if ("教师".equals(RadioButtonList1)) {
            list = functionMapper.findMenuByTeacherid(userId);
        } else if ("学生".equals(RadioButtonList1)) {
            list = functionMapper.findMenuByStudentid(userId);
        }else if("教务人员".equals(RadioButtonList1)){
            list = functionMapper.findMenuByUserid(userId);
        }
        return list;
    }

    public RS save(AuthFunction model) {
        functionMapper.insert(model);
        return RS.ok();
    }

    public IPage pageQuery(QueryFunctionVO params) {
        IPage iPage = this.BaseQuery(params);
        return iPage;
    }

    public List<Integer> queryFunctionByRole(String id) {
        List<Integer> list = functionMapper.queryFunctionByRole(id);
        return list;
    }

    public IPage BaseQuery(QueryFunctionVO baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper wrapper = getWrapper(baseQueryParam,null);
        IPage iPage = functionMapper.selectPage(page, wrapper);
        return iPage;
    }

    public RS updateFunction(AuthFunction function) {
        int i;
        if (function.getId() != null) {
            i = functionMapper.insert(function);
        } else {
            i = functionMapper.updateById(function);
        }
        return i == 1 ? RS.ok() : RS.error("更新权限失败");
    }

    public RS delete(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid", id);
        queryWrapper.or(true);
        queryWrapper.eq("id", id);
        queryWrapper.orderByDesc("id");
        List<AuthFunction> list = functionMapper.selectList(queryWrapper);
        int i = 0;
        if (list.size() != 0) {
            for (int j = 0; j < list.size(); j++) {
                i = functionMapper.deleteById(list.get(i).getId());
            }
        }
        return i == 0 ? RS.error("删除权限失败") : RS.ok();
    }
}
