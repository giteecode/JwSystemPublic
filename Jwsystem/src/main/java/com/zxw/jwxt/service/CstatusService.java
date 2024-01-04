package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TCstatus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.mapper.TCstatusMapper;
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
public class CstatusService extends BaseService {

    @Autowired
    private TCstatusMapper cstatusMapper;

    public IPage pageQuery(BaseQueryParam baseQueryParam) {
        Page page = getPage(baseQueryParam);
        QueryWrapper wrapper = getWrapper(baseQueryParam,null);
        return cstatusMapper.selectPage(page, wrapper);
    }
}
