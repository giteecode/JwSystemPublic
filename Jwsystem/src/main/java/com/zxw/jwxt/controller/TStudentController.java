package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.TStudent;
import com.zxw.jwxt.service.StudentService;
import com.zxw.jwxt.vo.QueryStudentVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/api/student")
public class TStudentController extends BaseController {
    @Autowired
    private StudentService studentService;

    /**
     * 查询出学生列表
     *
     * @param queryStudentVO
     * @return
     * @throws IOException
     */
    @GetMapping("/pageQuery")
    public TableResponse pageQuery(QueryStudentVO queryStudentVO) throws IOException {
        IPage result = studentService.pageQuery(queryStudentVO);
        TableResponse reponse = TableResponse.of(result);
        return reponse;
    }

    /**
     * 查询该班级的所有学生
     *
     * @param queryStudentVO
     * @return
     */
    @GetMapping("/findStudentByclass")
    public TableResponse findStudentByclass(QueryStudentVO queryStudentVO) {
        IPage result = studentService.findStudentByclass(queryStudentVO);
        TableResponse reponse = TableResponse.of(result);
        return reponse;
    }

    /**
     * 查询个人信息
     */
    @GetMapping("/findInfo")
    public ResponseEntity findInfo(){
        TStudent student = studentService.findInfo(getUserId());
        return ResponseEntity.ok(student);
    }

    /**
     * 查询课表
     * @param queryCourseVO
     * @return
     */
    @GetMapping("/findSchedule")
    public ResponseEntity findSchedule(QueryStudentVO queryCourseVO){
        Object[][] schedule = studentService.findSchedule(queryCourseVO, getUserId());
        return ResponseEntity.ok(schedule);
    }

    /**
     * 查询个人成绩
     *
     * @param request
     * @return
     */
    @GetMapping("/PersonScorePage")
    public String PersonScorePage(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        TStudent student = (TStudent) subject.getPrincipal();
        request.setAttribute("PersonStudent", student);
        return "/student/PersonScore";
    }
}
