package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TWeek;
import com.zxw.jwxt.mapper.TWeekMapper;
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
public class WeekService extends BaseService {

    @Autowired
    private TWeekMapper weekMapper;

    public IPage pageQuery(BaseQueryParam baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper queryWrapper = getWrapper(baseQueryParam, null);
        IPage iPage = weekMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    public TWeek findOne() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("id");
        List<TWeek> list = weekMapper.selectList(queryWrapper);
        return list == null ? null : list.get(0);
    }
}
