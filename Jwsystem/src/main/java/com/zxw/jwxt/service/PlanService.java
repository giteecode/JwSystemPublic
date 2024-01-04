package com.zxw.jwxt.service;

import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.Plan;
import com.zxw.jwxt.dto.PlanDTO;
import com.zxw.jwxt.mapper.PlanMapper;
import com.zxw.jwxt.vo.QueryPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class PlanService extends BaseService {

    @Autowired
    private PlanMapper planMapper;

    public List<PlanDTO> listajax(QueryPlanVO planVO) {
        List list = planMapper.listajax(planVO.getSpecialtyId());
        return list;
    }

    public RS add(Plan plan) {
        plan.setCreateTime(new Date());
        int insert = planMapper.insert(plan);
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }

    public RS edit(Plan plan) {
        int insert = planMapper.updateById(plan);
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }

    public RS delete(String id) {
        int insert = planMapper.deleteById(id);
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }
}
