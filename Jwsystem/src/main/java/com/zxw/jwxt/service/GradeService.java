package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.mapper.TGradeMapper;
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
public class GradeService extends BaseService {

    @Autowired
    private TGradeMapper gradeMapper;

    public IPage pageQuery(BaseQueryParam baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper queryWrapper = getWrapper(baseQueryParam,null);
        IPage iPage = gradeMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    public List listajax(BaseQueryParam baseQueryParam) {
        return gradeMapper.selectList(this.getWrapper(baseQueryParam,null));
    }
}
