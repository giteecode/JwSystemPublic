package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Mapper
public interface TCourseMapper extends BaseMapper<TCourse> {
    @Select("SELECT c.`name`,tc.`id`,tc.`teacher_id` tid,w.time wname,n.`name` nname,s.`week` sw,s.section sse,cs.`name` csname,e.`name` ename,t.`name` tname,co.`name` collegeName,teacher.`tname` teacherName,c.`credit`,tc.`people`,tc.`total_people`,tc.`classroom`,c.`total_time`,tc.`end` FROM t_course c,t_nature n,t_week w,t_section s,t_cstatus cs, t_examway e,t_team t,t_college co,t_teacher teacher,`teacher_course` tc WHERE tc.week_id = w.id AND tc.`cid` = c.`id` AND c.way_id = e.id AND tc.team_id = t.id AND tc.section_id = s.id AND c.nature_id = n.id AND c.cstatus_id = cs.id AND c.college_id = co.id AND tc.teacher_id = teacher.tid AND tc.`status` = '1' and tc.`end` = 0 and tc.`apply` = 1")
    IPage<CourseDTO> findAll(Page page);

    @Select("SELECT c.`name`,tc.`id`,teacher.`tid`,w.time wname,s.`week` sw,s.section sse,cs.`name` csname,e.`name` ename,t.`name` tname,co.`name` collegeName,teacher.`tname` teacherName,c.`credit`,c.`total_time`,system.`name` systemName,tc.`end` FROM t_course c,t_week w,t_section s,t_cstatus cs, t_examway e,t_team t,t_college co,t_teacher teacher,`teacher_course` tc,`course_system` system WHERE tc.`cid` = c.`id` AND tc.week_id = w.id AND c.way_id = e.id AND tc.team_id = t.id AND tc.section_id = s.id AND c.cstatus_id = cs.id AND  c.`system_id` = system.`id` AND teacher.`college_id` = co.`id` AND tc.`teacher_id` = teacher.tid AND tc.`status` = '1' AND tc.`end` = 1 AND teacher.`college_id` = #{collegeId} AND tc.`apply` = 1")
    IPage<CourseDTO> courseApply(Page page, @Param("collegeId") String collegeId);

    @Select("SELECT s.`id` sectionId,t.`id` teamId,tc.`id`,t.`id` teamId,c.*,c.`id` cid,tc.`end`,tc.`classroom`,tc.`people`,w.time wname,n.`name` nname,s.`week` sw,s.section sse,cs.`name` csname,e.`name` ename,t.`name` tname,co.`name` collegeName FROM t_course c,t_nature n,t_week w,t_section s,t_cstatus cs, t_examway e,t_team t,t_college co,t_teacher teacher,`teacher_course` tc WHERE tc.week_id = w.id AND tc.`cid` = c.`id` AND c.way_id = e.id AND tc.team_id = t.id AND tc.section_id = s.id AND c.nature_id = n.id AND c.cstatus_id = cs.id AND c.college_id = co.id AND tc.teacher_id = teacher.tid AND tc.teacher_id = #{tid} and tc.`apply` = 1")
    IPage<CourseDTO> findCourseByteacherId(Page page, @Param("tid") String tid);

    @Select("select c.*,tc.`classroom`,w.time wname,n.`name` nname,s.`week` sw,s.section sse,cs.`name` csname,e.`name` ename,t.`name` tname,co.`name` collegeName from t_course c,t_nature n,t_week w,t_section s,t_cstatus cs, t_examway e,t_team t,t_college co,t_teacher teacher,`teacher_course` tc WHERE tc.week_id = w.id and c.way_id = e.id and tc.team_id = t.id and tc.section_id = s.id and c.nature_id = n.id and c.cstatus_id = cs.id and c.college_id = co.id and tc.teacher_id = teacher.tid and tc.`cid` = c.`id` and tc.teacher_id = #{tid} and tc.team_id = #{teamId} and tc.`apply` = 1")
    List<CourseDTO> findScheduleByTeacher(@Param("tid") String tid, @Param("teamId") String teamId);

    @Select("SELECT st.`sname`,st.`sid`,s.`course_id` cid,s.`teacher_id` tid,c.`classname` cname,sp.`name` spname,tc.`name` tcname,g.`name` gname,s.`absent`,s.`attendance`,s.`usually`,s.`exam`,s.`score` FROM `t_score` s,`t_student` st,`t_classes` c,`t_specialty` sp,`t_college` tc,`t_grade` g,`teacher_course` tcc WHERE  tcc.`id` = s.`course_id` AND s.`student_id` = st.`sid` AND c.`college_id` = tc.`id` AND c.`specialty_id` = sp.`id` AND c.`grade_id` = g.`id` AND st.`classes_id` = c.`id`AND tcc.`id` = #{id}")
    IPage<StudentDTO> findStudentByCourseId(Page page, @Param("id") String id);

    @Select("SELECT tc.`id`,tc.`classroom`,c.`name` courseName,tc.`classroom`,t.`tname` teacherName,te.`name` tname,w.`time` wname,se.`section` sse,se.`week` sw FROM `t_score` s,`t_course` c,`t_teacher` t,`t_team` te,`t_student` ts,`t_week` w,`t_section` se,`teacher_course` tc WHERE s.`student_id` = ts.`sid` AND s.`course_id` = tc.`id` AND tc.`cid` = c.`id`  AND tc.`team_id` = te.`id` AND tc.`week_id` = w.`id` AND tc.`teacher_id` = t.`tid` AND se.`id`= tc.`section_id` AND ts.`sid` = #{sid} AND tc.team_id = #{teamId} and tc.`apply` = 1")
    List<CourseDTO> findScheduleByStudent(@Param("sid") String userId, @Param("teamId") String teamId);

    @Select("SELECT c.`name`,COUNT(*) FROM `absent` ab,`teacher_course` tc,`t_course` c,`t_team` t WHERE tc.`team_id` = t.`id` AND c.`id` = tc.`cid` AND ab.`cid` = tc.`id` AND c.`college_id` = #{collegeId} AND  t.`team` = #{teamId} AND (c.`system_id` = 2 OR c.`system_id` = 6) GROUP BY c.`id` LIMIT 0,5")
    List<CourseDTO> countDownCourseRate(@Param("collegeId") String collegeId, @Param("teamId") String teamId);
}
