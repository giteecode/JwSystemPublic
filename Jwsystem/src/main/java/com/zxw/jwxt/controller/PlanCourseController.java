package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.exception.BadRequestException;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.PlanCourse;
import com.zxw.jwxt.service.PlanCourseService;
import com.zxw.jwxt.vo.QueryPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-01-05
 */
@RestController
@RequestMapping("/api/planCourse")
public class PlanCourseController extends BaseController {

    @Autowired
    private PlanCourseService planCourseService;

    @GetMapping("/pageQuery")
    public ResponseEntity pageQuery(QueryPlanVO planVO){
        IPage iPage = planCourseService.pageQuery(planVO);
        return ResponseEntity.ok(iPage);
    }

    @GetMapping("/findStudentPlan")
    public ResponseEntity findStudentPlan(QueryPlanVO planVO){
        Object[] plan = planCourseService.findStudentPlan(planVO,getRealm());
        return ResponseEntity.ok(plan);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody QueryPlanVO plan) {
        RS rs = planCourseService.add(plan);
        if (rs.get("status").equals("1")) {
            return ResponseEntity.ok(rs);
        }
        throw new BadRequestException("添加失败");
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody PlanCourse plan) {
        RS rs = planCourseService.edit(plan);
        if (rs.get("status").equals("1")) {
            return ResponseEntity.ok(rs);
        }
        throw new BadRequestException("修改失败");
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") String id) {
        RS rs = planCourseService.delete(id);
        if (rs.get("status").equals("1")) {
            return ResponseEntity.ok(rs);
        }
        throw new BadRequestException("删除失败");
    }

}
