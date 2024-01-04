package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.domain.TTeacher;
import com.zxw.jwxt.service.TeacherService;
import com.zxw.jwxt.vo.QueryCourseVO;
import com.zxw.jwxt.vo.QueryTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/teacher")
public class TTeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 分页查询
     *
     * @param teacherVO
     * @return
     */
    @GetMapping("/pageQuery")
    public TableResponse pageQuery(QueryTeacherVO teacherVO) {
        IPage result = teacherService.pageQuery(teacherVO);
        TableResponse reponse = TableResponse.of(result);
        return reponse;
    }

    @GetMapping("/teacherByCollege")
    public List<TTeacher> teacherByCollege(String id) {
        List<TTeacher> list = teacherService.findListNoStatusAndCollege(id);
        return list;
    }

    /**
     * 添加用户
     */
    @PostMapping
    public ResponseEntity add(@RequestBody TTeacher teacher) {
        RS rs = teacherService.save(teacher);
        return ResponseEntity.ok(rs);
    }

    /**
     * 添加用户
     */
    @PutMapping
    public ResponseEntity edit(@RequestBody TTeacher tTeacher) {
        RS rs = teacherService.edit(tTeacher);
        return ResponseEntity.ok(rs);
    }

    /**
     * 添加用户
     */
    @DeleteMapping
    public ResponseEntity delete(String tid) {
        RS rs = teacherService.delete(tid);
        return ResponseEntity.ok(rs);
    }

    /**
     * 查找个人信息
     *
     * @return
     */
    @GetMapping("/findById")
    public ResponseEntity findInfo() {
        TTeacher teacher = teacherService.findInfo(getUserId());
        return ResponseEntity.ok(teacher);
    }

    /**
     * 查找课程表
     *
     * @param queryCourseVO
     * @return
     */
    @GetMapping("/findSchedule")
    public ResponseEntity findSchedule(QueryCourseVO queryCourseVO) {
        Object[][] schedule = teacherService.findSchedule(queryCourseVO, getUserId());
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/listajax")
    public ResponseEntity listajax() {
        List list = teacherService.listajax(getRealm());
        return ResponseEntity.ok(list);
    }

}
