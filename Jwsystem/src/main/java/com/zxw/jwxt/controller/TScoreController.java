package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.exception.BadRequestException;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.Absent;
import com.zxw.jwxt.domain.TScore;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.service.*;
import com.zxw.jwxt.vo.QueryScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/api/score")
public class TScoreController extends BaseController {
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ITeacherCourseService teacherCourseService;

    @Autowired
    private IAbsentService absentService;


    /**
     * 选课实现
     *
     * @param scoreVO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody QueryScoreVO scoreVO) {
        // 判断是否已经选修
        RS rs = scoreService.save(scoreVO, getUserId());
        return ResponseEntity.ok(rs);
    }


    /**
     * 查找学生
     *
     * @param studentId
     */
    @GetMapping("/findStudent")
    public List<TScore> findStudent(String studentId) throws IOException {
        List<TScore> list = scoreService.queryByStudentId(studentId);
        return list;
    }

    /**
     * 添加成绩
     * @param scoreVO
     * @return
     */
    @PostMapping("/addScore")
    public ResponseEntity addStudentScore(@RequestBody QueryScoreVO scoreVO) {
        RS rs = scoreService.addScore(scoreVO);
        if (rs.get("status").equals("1")) {
            return ResponseEntity.ok(rs);
        }
        throw new BadRequestException("添加成绩失败");
    }

    /**
     * 查找选修的课程
     */
    @GetMapping("/findSelectCourseByStudentId")
    public ResponseEntity findSelectCourseByStudentId(QueryScoreVO scoreVO) {
        List<CourseDTO> list = scoreService.findSelectCourseByStudentId(getUserId());
        return ResponseEntity.ok(list);
    }

    /**
     * 查询学生成绩
     *
     * @return
     */
    @GetMapping("/findStudentScore")
    public IPage findStudentScore(QueryScoreVO scoreVO) {
        IPage list = scoreService.findStudentScore(scoreVO,getUserId());
        return list;
    }

    /**
     * 添加缺勤
     *
     * @return
     */
    @PostMapping("/addAbsent")
    public ResponseEntity addAbsent(@RequestBody QueryScoreVO queryScoreVO) {
        RS rs = scoreService.addAbsent(queryScoreVO);
        RS update = studentService.updateAbsent(queryScoreVO.getSid());
        Absent absent = new Absent();
        absent.setCid(queryScoreVO.getCid());
        absent.setSectionId(queryScoreVO.getSectionId());
        absent.setTeamId(queryScoreVO.getTeamId());
        absent.setCreateTime(new Date());
        absent.setSid(queryScoreVO.getSid());
        absent.setTid(getUserId());
        boolean save = absentService.save(absent);
        if (rs.get("status").equals("1") && update.get("status").equals("1")) {
            return ResponseEntity.ok(rs);
        }
        throw new BadRequestException("考勤更新失败");
    }

    /**
     * 选课退选
     *
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity delete(QueryScoreVO scoreVO) {
        RS rs = scoreService.delete(getUserId(), scoreVO.getCid());
        if (rs.get("status").equals("1")) {
            RS delete = courseService.deletePeople(scoreVO.getCid());
            return ResponseEntity.ok(rs);
        }
        throw new BadRequestException("退选失败");
    }
}
