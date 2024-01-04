package com.zxw.jwxt.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.TableResponse;
import com.zxw.jwxt.service.TeamCommentService;
import com.zxw.jwxt.vo.QueryTeamCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2023-12-30
 */
@RestController
@RequestMapping("/api/teamComment")
public class TeamCommentController extends BaseController {
    @Autowired
    private TeamCommentService teamCommentService;

    /**
     * 查询学生评价列表
     * @param teamCommentVO
     * @return
     */
    @GetMapping("/pageQuery")
    public ResponseEntity pageQuery(QueryTeamCommentVO teamCommentVO){
        IPage page =  teamCommentService.pageQuery(teamCommentVO,getUserId());
        TableResponse of = TableResponse.of(page);
        return ResponseEntity.ok(of);
    }

    /**
     * 查询教师评价列表
     * @param teamCommentVO
     * @return
     */
    @GetMapping("/findTeacher")
    public ResponseEntity findTeacher(QueryTeamCommentVO teamCommentVO){
        IPage page =  teamCommentService.findTeacher(teamCommentVO,getUserId());
        TableResponse of = TableResponse.of(page);
        return ResponseEntity.ok(of);
    }

    /**
     * 查找该门课程的学生评价
     * @param teamCommentVO
     * @return
     */
    @GetMapping("/findStudentComment")
    public ResponseEntity findStudentComment(QueryTeamCommentVO teamCommentVO){
        IPage page =  teamCommentService.findStudentComment(teamCommentVO,getUserId());
        TableResponse of = TableResponse.of(page);
        return ResponseEntity.ok(of);
    }
}
