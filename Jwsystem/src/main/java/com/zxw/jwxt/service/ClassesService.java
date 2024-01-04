package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.TClasses;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.mapper.TClassesMapper;
import com.zxw.jwxt.vo.QueryClassesVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ClassesService extends BaseService {
    @Autowired
    private TClassesMapper classesMapper;

    public RS save(TClasses classes) {
        return classesMapper.insert(classes) == 0 ? RS.error("保存失败") : RS.ok("保存成功");
    }

    public IPage pageQuery(QueryClassesVO queryClassesVO, UserRealm realm) {
        IPage<QueryClassesVO> iPage = null;
        Page page = getPage(queryClassesVO);
        Map<String, Object> map = new HashMap<>();
        map.put("cs.`college_id`", realm.getCollegeId());
        map.put("cs.`specialty_id`", queryClassesVO.getSpecialtyId());
        map.put("cs.`grade_id`", queryClassesVO.getGradeId());
        if (StringUtils.isNotEmpty(realm.getCollegeId())) {
            iPage = classesMapper.findByParams(page, this.getWrapper(queryClassesVO,null, map));
        } else {
            iPage = classesMapper.findAll(page);
        }
        return iPage;
    }

    public RS deleteBatch(QueryClassesVO classesVO) {
        return RS.ok();
    }

    public List findClassesByGrade(QueryClassesVO classesVO, UserRealm realm) {
        List list = classesMapper.findClassesByGrade(realm.getCollegeId(),classesVO.getGradeId());
        return list;
    }

    public TClasses findById(String id) {
        TClasses tClasses = classesMapper.selectById(id);
        return tClasses;
    }

    public List findBySpecialty(QueryClassesVO classesVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(classesVO.getGradeId())){
            queryWrapper.eq("grade_id", classesVO.getGradeId());
        }
        if(StringUtils.isNotEmpty(classesVO.getSpecialtyId())){
            queryWrapper.eq("specialty_id", classesVO.getSpecialtyId());
        }
        List list = classesMapper.selectList(queryWrapper);
        return list;
    }
}
