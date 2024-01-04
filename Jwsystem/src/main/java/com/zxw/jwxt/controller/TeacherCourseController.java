package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.TeacherCourse;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.service.ITeacherCourseService;
import com.zxw.jwxt.service.ScoreService;
import com.zxw.jwxt.service.TeamService;
import com.zxw.jwxt.service.WeekService;
import com.zxw.jwxt.vo.QueryCourseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-01-26
 */
@RestController
@RequestMapping("/api/teacherCourse")
public class TeacherCourseController extends BaseController {
    @Autowired
    private ITeacherCourseService teacherCourseService;
    @Autowired
    private WeekService weekService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ScoreService scoreService;

    /**
     * 查找审核列表
     *
     * @param queryCourseVO
     * @return
     */
    @GetMapping("/findApply")
    public ResponseEntity findApply(QueryCourseVO queryCourseVO) {
        IPage page = teacherCourseService.findApply(queryCourseVO, getRealm());
        TableResponse of = TableResponse.of(page);
        return ResponseEntity.ok(of);
    }

    /**
     * 教师-查找审核列表
     *
     * @param queryCourseVO
     * @return
     */
    @GetMapping("/findApplyByTeacher")
    public ResponseEntity findApplyByTeacher(QueryCourseVO queryCourseVO) {
        IPage page = teacherCourseService.findApplyByTeacher(queryCourseVO, getRealm());
        TableResponse of = TableResponse.of(page);
        return ResponseEntity.ok(of);
    }

    /**
     * 查找班级下的课程
     *
     * @param courseVO
     * @return
     */
    @GetMapping("/findClassCoure")
    public ResponseEntity findClassCoure(QueryCourseVO courseVO) {
        IPage<CourseDTO> list = teacherCourseService.findClassCoure(courseVO);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody TeacherCourse teacherCourse) {
        if (!getRealm().getQx().equals("教务人员")) {
            teacherCourse.setTeacherId(getUserId());
        }
        if (StringUtils.isNotEmpty(teacherCourse.getTeamId())) {
            teacherCourse.setTeamId(teacherCourse.getTeamId());
        } else {
            teacherCourse.setTeamId(teamService.findOne().getId());
        }
        if (StringUtils.isNotEmpty(teacherCourse.getClassesId()) && teacherCourse.getIsClasses() == 1) {
            teacherCourse.setApply(1);
        }
        boolean b = teacherCourseService.save(teacherCourse);
        if (StringUtils.isNotEmpty(teacherCourse.getClassesId()) && teacherCourse.getIsClasses() == 1) {
            scoreService.saveCourse(teacherCourse.getClassesId(), teacherCourse.getId(), teacherCourse.getTeacherId());
        }
        return ResponseEntity.ok(b);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody TeacherCourse teacherCourse) {
        boolean b = teacherCourseService.updateById(teacherCourse);
        return ResponseEntity.ok(b);
    }

    @PutMapping("/agree")
    public ResponseEntity agree(@RequestParam("id") String id) {
        boolean b = teacherCourseService.agree(id);
        return ResponseEntity.ok(b);
    }

    @PutMapping("/back")
    public ResponseEntity back(@RequestParam("id") String id) {
        boolean b = teacherCourseService.back(id);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping
    public ResponseEntity delete(String id) {
        boolean b = teacherCourseService.removeById(id);
        return ResponseEntity.ok(b);
    }


}
