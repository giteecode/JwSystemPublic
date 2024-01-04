package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TTeam;
import com.zxw.jwxt.mapper.TTeamMapper;
import com.zxw.jwxt.vo.BaseQueryParam;
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
public class TeamService extends BaseService {
    @Autowired
    private TTeamMapper teamMapper;

    public IPage pageQuery(BaseQueryParam baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper queryWrapper = getWrapper(baseQueryParam, null);
        IPage iPage = teamMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    public TTeam findOne() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("id");
        List<TTeam> list = teamMapper.selectList(queryWrapper);
        return list == null ? null : list.get(0);
    }
}
