package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.TComments;
import com.zxw.jwxt.domain.TeacherCourse;
import com.zxw.jwxt.domain.TeamComment;
import com.zxw.jwxt.dto.CommentDTO;
import com.zxw.jwxt.dto.StudentDTO;
import com.zxw.jwxt.mapper.TCommentsMapper;
import com.zxw.jwxt.vo.QueryCommentVO;
import com.zxw.jwxt.vo.QueryCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Service
public class CommentsService extends BaseService {

    @Autowired
    private TCommentsMapper commentsMapper;

    @Autowired
    private TeamCommentService teamCommentService;

    @Autowired
    private CourseService courseService;

    public IPage pageQuery(QueryCommentVO commentVO) {
        IPage<CommentDTO> iPage = commentsMapper.findAll(this.getPage(commentVO));
        return iPage;
    }

    public RS add(TComments tComments) {
        int insert = commentsMapper.insert(tComments);
        List<TeacherCourse> list = courseService.selectAll(tComments.getTeamId());
        list.forEach(e -> {
            QueryCourseVO vo = new QueryCourseVO();
            vo.setId(e.getId());
            IPage page = courseService.findStudentByCourseId(vo);
            List<StudentDTO> records = page.getRecords();
            records.forEach(e1 -> {
                TeamComment teamComment = new TeamComment();
                teamComment.setStatus(0);
                teamComment.setCid(e.getId());
                teamComment.setSid(e1.getSid());
                teamComment.setTid(e1.getTid());
                teamComment.setCommentId(tComments.getId());
                teamCommentService.add(teamComment);
            });
        });
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }

    public RS delete(String id) {
        int i = commentsMapper.deleteById(id);
        List<TeamComment> list = teamCommentService.selectAllByCommentId(id);
        list.forEach(e -> {
            teamCommentService.deleteById(e.getCommentId());
        });
        return i == 0 ? RS.ok() : RS.error("操作失败");
    }

    public RS edit(TComments tComments) {
        int insert = commentsMapper.updateById(tComments);
        return insert == 1 ? RS.ok() : RS.error("操作失败");
    }
}
