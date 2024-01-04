package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.PlanCourse;
import com.zxw.jwxt.domain.TClasses;
import com.zxw.jwxt.domain.TStudent;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.dto.PlanDTO;
import com.zxw.jwxt.mapper.PlanCourseMapper;
import com.zxw.jwxt.vo.QueryPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PlanCourseService extends BaseService {
    @Autowired
    private PlanCourseMapper planCourseMapper;
    @Autowired
    private ClassesService classesService;


    public IPage pageQuery(QueryPlanVO planVO) {
        IPage iPage = planCourseMapper.findAll(this.getPage(planVO), planVO.getPlanId());
        return iPage;
    }

    public RS add(QueryPlanVO planVO) {
        int insert = 0;
        for (int i = 0; i < planVO.getCids().length; i++) {
            PlanCourse planCourse = new PlanCourse();
            planCourse.setPlanId(planVO.getPlanId());
            planCourse.setCourseId(planVO.getCids()[i]);
            insert = planCourseMapper.insert(planCourse);
        }
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }

    public RS edit(PlanCourse plan) {
        int insert = planCourseMapper.updateById(plan);
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }

    public RS delete(String id) {
        int insert = planCourseMapper.deleteById(id);
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }

    public Object[] findStudentPlan(QueryPlanVO planVO, UserRealm realm) {
        if(realm.getQx().equals("学生")){
            TStudent student = (TStudent) realm;
            TClasses classes = classesService.findById(student.getClassesId());
            planVO.setSpecialtyId(classes.getSpecialtyId());
        }
        List<PlanDTO> list = planCourseMapper.findStudentPlan(planVO.getSpecialtyId());
        Object[] objects = new Object[8];
        List l1 = new ArrayList();
        List l2 = new ArrayList();
        List l3 = new ArrayList();
        List l4 = new ArrayList();
        List l5 = new ArrayList();
        List l6 = new ArrayList();
        List l7 = new ArrayList();
        List l8 = new ArrayList();
        list.forEach(e -> {
            switch (e.getId()) {
                case "1":
                    l1.add(e);
                    break;
                case "2":
                    l2.add(e);
                    break;
                case "3":
                    l3.add(e);
                    break;
                case "4":
                    l4.add(e);
                    break;
                case "5":
                    l5.add(e);
                    break;
                case "6":
                    l6.add(e);
                    break;
                case "7":
                    l7.add(e);
                    break;
                case "8":
                    l8.add(e);
                    break;
            }
        });
        objects[0] = l1;
        objects[1] = l2;
        objects[2] = l3;
        objects[3] = l4;
        objects[4] = l5;
        objects[5] = l6;
        objects[6] = l7;
        objects[7] = l8;
        return objects;
    }
}
