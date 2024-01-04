package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.Absent;
import com.zxw.jwxt.domain.TeacherCourse;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.mapper.provider.CourseProvider;
import com.zxw.jwxt.vo.QueryCourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2020-01-26
 */
@Mapper
public interface TeacherCourseMapper extends BaseMapper<TeacherCourse> {

    @Select("SELECT c.`name`,tc.`id`,tc.`total_people`,tc.`classroom`,t.`tname`,s.`section` sse,s.`id` sectionId,w.`id` weekId,c.`id` cid, s.`week` sw,w.`time` wname,tc.`apply`  FROM `teacher_course` tc,`t_teacher` t,`t_course` c,`t_college` co,`t_section` s,`t_week` w,`t_team` te WHERE tc.`team_id` = te.`id` AND tc.`section_id` = s.`id` AND tc.`week_id` = w.`id` AND tc.`teacher_id` = t.`tid` AND tc.`cid` = c.`id` AND t.`college_id` = co.`id` AND tc.`apply` = 0 and co.`id` = #{collegeId}")
    IPage<CourseDTO> findApply(Page page, @Param("collegeId") String collegeId);

    @Select("SELECT c.`name`,tc.`total_people`,tc.`classroom`,t.`tname`,s.`section` sse,s.`week` sw,w.`time` wname,tc.`apply` FROM `teacher_course` tc,`t_teacher` t,`t_course` c,`t_college` co,`t_section` s,`t_week` w,`t_team` te WHERE tc.`team_id` = te.`id` AND tc.`section_id` = s.`id` AND tc.`week_id` = w.`id` AND tc.`teacher_id` = t.`tid` AND tc.`cid` = c.`id` AND t.`college_id` = co.`id` AND t.`tid`=#{id} and tc.`is_classes` = 0")
    IPage<CourseDTO> findApplyByTeacher(Page page, @Param("id") String id);

    @Select("SELECT ab.* FROM `absent` ab,`teacher_course` tc,`t_course` c,`t_team` t WHERE ab.`cid` = tc.`id` AND (c.`system_id` = '2' OR c.`system_id` = '6') AND tc.`cid` = c.`id` AND tc.`team_id` = t.`id` AND c.`college_id` = #{collegeId} AND ab.`create_time` BETWEEN #{beginDate} AND #{endDate}")
    List<Absent> countDownCourseSection(@Param("collegeId") String collegeId, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    //    @Select("SELECT c.`name`,tc.`total_people`,tc.`classroom`,t.`name` tname,s.`section` sse,s.`week` sw,w.`time` wname,c.`credit`,c.`total_time` FROM `teacher_course` tc,`t_team` t,`t_section` s,`t_week` w,`t_teacher` teacher,`t_course` c,teacher.`tname` teacherName WHERE tc.`cid` = c.`id` AND tc.`teacher_id` = teacher.`tid` AND tc.`team_id` = t.`id` AND tc.`section_id` = s.`id` AND tc.`week_id` = w.`id` AND tc.`classes_id` = #{classId}")
    @SelectProvider(type = CourseProvider.class, method = "findCourseByClass")
    IPage<CourseDTO> findClassCourse(Page page, QueryCourseVO courseVO);

    @Select("select count(*) from `teacher_course` tc,`t_score` s where tc.`id` = s.`course_id` and tc.`teacher_id` = s.`teacher_id` and s.`score` between #{begin} and #{end} and tc.`id`=#{cid};")
    Integer countCourseScore(@Param("begin") Integer begin, @Param("end") Integer end, @Param("cid") String cid);

    @Select("select course.`name` from teacher_course tc,t_course course where tc.cid = course.id and tc.team_id = #{teamId} and tc.teacher_id = #{id} and tc.`end` = #{end}")
    List<String> countFinishCourseName(@Param("id") String id, @Param("teamId") String teamId, @Param("end") Integer end);

    @Select("select c.`name` name,sum(s.absent) value from teacher_course tc,t_score s,t_course c where tc.teacher_id = s.teacher_id and tc.id = s.course_id and tc.team_id = #{teamId} and tc.teacher_id = #{id} and tc.cid = c.id GROUP BY tc.id HAVING sum(s.absent >0) limit 0,5")
    List<CourseDTO> countAbsent(@Param("id") String id, @Param("teamId") String teamId);
}
