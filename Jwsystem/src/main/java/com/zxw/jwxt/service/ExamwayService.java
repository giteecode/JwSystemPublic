package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TExamway;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.mapper.TExamwayMapper;
import com.zxw.jwxt.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ExamwayService extends BaseService {
    @Autowired
    private TExamwayMapper examwayMapper;

    public IPage pageQuery(BaseQueryParam baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper queryWrapper = getWrapper(baseQueryParam,null);
        IPage iPage = examwayMapper.selectPage(page, queryWrapper);
        return iPage;
    }
}
