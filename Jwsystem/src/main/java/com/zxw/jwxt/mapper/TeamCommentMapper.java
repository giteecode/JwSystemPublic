package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TeamComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxw.jwxt.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2020-01-04
 */
@Mapper
public interface TeamCommentMapper extends BaseMapper<TeamComment> {

    @Select("SELECT course.`id` cid,teacher.`tname` teacherName,course.`name` courseName,c.`commentType`,tc.`id`,tcc.`id` tcid,tc.`remark`,tc.`status`  FROM `team_comment` tc, `t_comments` c, `t_teacher` teacher, `t_course` course, `teacher_course` tcc WHERE tc.`comment_id` = c.`id` AND tcc.`cid` = course.`id` AND tcc.`id` = tc.`cid` AND tc.`tid` = teacher.tid AND tc.sid = #{sid} AND tc.`comment_id` = #{commentId}")
    IPage<CommentDTO> findAll(Page page, @Param("sid") String sid, @Param("commentId") String commentId);

    /**
     * 查找课程评价
     *
     * @param page
     * @param teacherId
     * @param commentId
     * @return
     */
    @Select("SELECT tc.`cid` cid,tc.`id` tcid,course.`name` courseName,c.`commentType`,c.`id`  FROM `t_comments` c, `t_course` course, `teacher_course` tc WHERE tc.`cid` = course.`id` AND tc.`team_id` = c.`team_id` AND tc.`teacher_id` = #{teacherId} AND c.`id` = #{commentId}")
    IPage<CommentDTO> findTeacher(Page page, @Param("teacherId") String teacherId, @Param("commentId") String commentId);

    /**
     * @param page
     * @param id        评价id
     * @param teacherId 教师id
     * @param courseId  课程id
     * @return
     */
    @Select("SELECT s.`sid`,tcc.`id` cid,tc.remark,tc.`status`, tc.`id`,s.`sname` FROM  `team_comment` tc,`t_student` s,`t_course` course,`teacher_course` tcc WHERE tc.`sid` = s.`sid` AND tc.`comment_id` = #{id} AND tc.`tid` = #{teacherId} AND tcc.`cid` = course.`id` AND tcc.`id` = tc.`cid` AND tcc.`id` = #{courseId}")
    IPage<CommentDTO> findStudentComment(Page page, @Param("id") String id, @Param("teacherId") String teacherId, @Param("courseId") String courseId);

    /**
     * 统计课程评价
     *
     * @param cid
     * @param commentId
     * @return
     */
    @Select("SELECT SUM(remark) FROM `team_comment` WHERE cid=#{cid} AND comment_id = #{commentId}")
    Integer countCommentByCourse(@Param("cid") String cid, @Param("commentId") String commentId);

    @Select("select count(*) from `team_comment` tc where tc.remark between #{begin} and #{end} and tc.`cid`=#{cid}")
    Integer countCourseComment(@Param("begin") Integer begin, @Param("end") Integer end, @Param("cid") String cid);
}
