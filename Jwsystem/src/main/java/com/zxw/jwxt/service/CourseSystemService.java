package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.mapper.CourseSystemMapper;
import com.zxw.jwxt.vo.BaseQueryParam;
import com.zxw.jwxt.vo.QueryCourseSystemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2020-01-05
 */
@Service
public class CourseSystemService extends BaseService {
    @Autowired
    private CourseSystemMapper courseSystemMapper;

    public List listajax(BaseQueryParam baseQueryParam) {
        List list = courseSystemMapper.selectList(this.getWrapper(baseQueryParam, null));
        return list;
    }

    public IPage findCourseBySystemId(QueryCourseSystemVO courseSystemVO) {
        IPage<CourseDTO> iPage = courseSystemMapper.findCourseBySystemId(this.getPage(courseSystemVO),courseSystemVO.getSystemId());
        return iPage;
    }
}
