package com.zxw.jwxt.service;

import com.zxw.common.exception.BadRequestException;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.CourseComment;
import com.zxw.jwxt.domain.TeamComment;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.dto.CourseCommentDTO;
import com.zxw.jwxt.mapper.CourseCommentMapper;
import com.zxw.jwxt.vo.QueryCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2020-01-15
 */
@Service
public class CourseCommentService extends BaseService {

    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Autowired
    private TeamCommentService teamCommentService;

    public RS add(CourseComment courseComment) {
        int i = courseCommentMapper.insert(courseComment);
        TeamComment teamComment = teamCommentService.selectById(courseComment.getTmId());
        if (teamComment != null) {
            teamComment.setRemark(getRemark(courseComment));
            teamComment.setStatus(1);
            teamCommentService.update(teamComment);
            return i == 1 ? RS.ok() : RS.error("添加失败");
        }
        throw new BadRequestException("操作异常，请联系管理员");
    }

    public CourseCommentDTO queryCourseComment(QueryCommentVO commentVO, UserRealm realm) {
        CourseCommentDTO courseCommentDTO = courseCommentMapper.queryCourseComment(commentVO.getCid(), commentVO.getTcId(), realm.getQx().equals("学生") ? realm.getId() : commentVO.getSid());
        return courseCommentDTO;
    }

    private Integer getRemark(CourseComment courseComment) {
        return (courseComment.getQ1() + courseComment.getQ2() + courseComment.getQ3() + courseComment.getQ4() + courseComment.getQ5() + courseComment.getQ6() + courseComment.getQ7() + courseComment.getQ8() + courseComment.getQ9() + courseComment.getQ10()) * 2;
    }

    public CourseCommentDTO queryStudentComment(QueryCommentVO commentVO) {
        CourseCommentDTO courseCommentDTO = courseCommentMapper.queryCourseComment(commentVO.getCid(), commentVO.getTcId(), commentVO.getSid());
        return courseCommentDTO;
    }

    public RS reply(CourseComment courseComment) {
        int i = courseCommentMapper.updateById(courseComment);
        return i == 1 ? RS.ok() : RS.error("添加失败");
    }
}
