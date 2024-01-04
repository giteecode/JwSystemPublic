package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.TSpecialty;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.mapper.TSpecialtyMapper;
import com.zxw.jwxt.vo.QuerySpecialtyVO;
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
public class SpecialtyService extends BaseService {
    @Autowired
    private TSpecialtyMapper specialtyMapper;

    public RS add(TSpecialty model) {
        return specialtyMapper.insert(model) == 0 ? RS.error("插入失败") : RS.ok("插入成功");
    }

    /**
     * 数组id
     */
    public RS update(TSpecialty model) {
        return specialtyMapper.updateById(model) == 0 ? RS.error("更新失败") : RS.ok("更新成功");
    }

    /**
     * 数组id
     */
    public TSpecialty findById(String id) {
        return specialtyMapper.selectById(id);
    }

    /**
     * 作废专业信息
     *
     * @param ids
     * @return
     */
    public RS deleteBatch(String ids) {
        TSpecialty tSpecialty = specialtyMapper.selectById(ids);
        if (tSpecialty.getStatus().equals("1")) {
            tSpecialty.setStatus("0");
        } else {
            tSpecialty.setStatus("1");
        }
        return specialtyMapper.updateById(tSpecialty) == 0 ? RS.error("作废失败") : RS.ok();
    }

    public IPage pageQuery(QuerySpecialtyVO baseQueryParam, UserRealm realm) {
        Page<QuerySpecialtyVO> page = getPage(baseQueryParam);
        IPage<QuerySpecialtyVO> iPage = null;
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> keyword = new HashMap<>();
        if (StringUtils.isNotEmpty(baseQueryParam.getKeyword())) {
            keyword.put("s.`name`", baseQueryParam.getKeyword());
        }
        map.put("s.`college_id`", realm.getCollegeId());
        if (StringUtils.isNotEmpty(realm.getCollegeId())) {
            iPage = specialtyMapper.findByJwUser(page, getWrapper(baseQueryParam, keyword, map));
        } else {
            iPage = specialtyMapper.findAll(page);
        }
        return iPage;
    }

    public RS redoSpecialty(String id) {
        TSpecialty tSpecialty = specialtyMapper.selectById(id);
        if (tSpecialty.getStatus().equals("0")) {
            tSpecialty.setStatus("1");
            specialtyMapper.updateById(tSpecialty);
        }
        return RS.ok();
    }

    public RS saveOrUpdateSpeciatly(TSpecialty specialty) {
        int count;
        TSpecialty tSpecialty = specialtyMapper.selectById(specialty.getId());
        if (tSpecialty != null) {
            count = specialtyMapper.updateById(specialty);
        } else {
            count = specialtyMapper.insert(specialty);
        }
        return count == 0 ? RS.error("更新或修改失败") : RS.ok();
    }

    public List listajax(QuerySpecialtyVO querySpecialtyVO, UserRealm realm) {
        Map<String, Object> map = new HashMap<>();
        if (realm.getCollegeId() != null) {
            map.put("college_id", realm.getCollegeId());
        }
        return specialtyMapper.selectList(getWrapper(querySpecialtyVO, null, map));
    }
}
