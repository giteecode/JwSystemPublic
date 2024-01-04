package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.TeamComment;
import com.zxw.jwxt.dto.CommentDTO;
import com.zxw.jwxt.mapper.TeamCommentMapper;
import com.zxw.jwxt.vo.QueryTeamCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2020-01-04
 */
@Service
public class TeamCommentService extends BaseService {

    @Autowired
    private TeamCommentMapper teamCommentMapper;

    public TeamComment selectById(String id) {
        return teamCommentMapper.selectById(id);
    }

    public RS update(TeamComment teamComment) {
        int i = teamCommentMapper.updateById(teamComment);
        return i == 1 ? RS.ok() : RS.error("操作异常");
    }

    public RS add(TeamComment teamComment) {
        int insert = teamCommentMapper.insert(teamComment);
        return insert == 0 ? RS.error("操作失败") : RS.ok();
    }

    public IPage pageQuery(QueryTeamCommentVO teamCommentVO, String userId) {
        IPage<CommentDTO> iPage = teamCommentMapper.findAll(this.getPage(teamCommentVO), userId, teamCommentVO.getCommentId());
        return iPage;
    }

    public IPage findTeacher(QueryTeamCommentVO teamCommentVO, String userId) {
        IPage<CommentDTO> iPage = teamCommentMapper.findTeacher(this.getPage(teamCommentVO), userId, teamCommentVO.getCommentId());
        return iPage;
    }

    public IPage findStudentComment(QueryTeamCommentVO teamCommentVO, String userId) {
        IPage<CommentDTO> iPage = teamCommentMapper.findStudentComment(this.getPage(teamCommentVO), teamCommentVO.getCommentId(), userId, teamCommentVO.getCid());
        return iPage;
    }

    public List selectAllByCommentId(String id) {
        List list = teamCommentMapper.selectList(this.queryOne("comment_id", id));
        return list;
    }

    public RS deleteById(String commentId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("comment_id", commentId);
        int i = teamCommentMapper.delete(wrapper);
        return i == 1              ? RS.ok() : RS.error("操作失败");
    }

    public Integer countCommentByCourse(String cid, String commentId) {
        Integer count = teamCommentMapper.countCommentByCourse(cid, commentId);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("comment_id", commentId);
        wrapper.eq("cid", cid);
        Integer num = teamCommentMapper.selectCount(wrapper);
        return count == null ? 0 : count / num ;
    }

    public Integer countCourseComment(int i, int i1, String cid) {
        return teamCommentMapper.countCourseComment(i, i1, cid);
    }
}
