package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.mapper.TSectionMapper;
import com.zxw.jwxt.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SectionService extends BaseService {

    @Autowired
    private TSectionMapper sectionMapper;

    public IPage pageQuery(BaseQueryParam baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper queryWrapper = getWrapper(baseQueryParam,null);
        IPage iPage = sectionMapper.selectPage(page, queryWrapper);
        return iPage;
    }
}
