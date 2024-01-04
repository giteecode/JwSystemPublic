package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TScore;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.dto.ScoreDTO;
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
public interface TScoreMapper extends BaseMapper<TScore> {
    /**
     * 查询出对应学生
     *
     * @param ids
     * @return
     */
    @Select("SELECT DISTINCT s.*,t.`sid`,t.`sname`,t.`sex` FROM `t_score`\n" +
            "\t\ts LEFT JOIN `t_student` t ON s.`Student_id`=t.`sid` WHERE\n" +
            "\t\ts.`course_id`=#{value};")
    List<TScore> findStudentId(String ids);


    /**
     * 查询出对应学生
     *
     * @param ids
     * @return
     */
    @Select("SELECT *,c.`name` cousename FROM (`t_score` s,`t_student` st,`t_section`\n" +
            "\t\tse,`t_week`\n" +
            "\t\tw,`t_nature` n,`t_classes` cl,`t_college` co,`t_specialty`\n" +
            "\t\tsp)\n" +
            "\t\tLEFT JOIN `t_course` c ON\n" +
            "\t\ts.`course_id`=c.`id`\n" +
            "\t\tLEFT JOIN `t_teacher`\n" +
            "\t\tt ON c.`teacher_id`=t.`tid`\n" +
            "\t\tWHERE c.`nature_id`=n.`id` AND\n" +
            "\t\tc.`week_id`=w.`id` AND\n" +
            "\t\tc.`section_id`=se.`id` AND\n" +
            "\t\tst.`classes_id`=cl.`id` AND cl.`specialty_id` =sp.`id` AND\n" +
            "\t\tsp.`college_id` = co.`id` AND s.`Student_id`=st.`sid` AND\n" +
            "\t\tst.`sid`=#{value}")
    List<TScore> findAllCourseByStudentId(String ids);

    /**
     * 查询学生个人成绩
     *
     * @return
     */
    @Select("SELECT tc.`id`,s.`point`,c.`credit`,c.`isExam`,c.`name`,te.`name` tname,s.`score`,cs.`name` csname,c.`total_time`,system.`name` systemName FROM `t_score` s,`t_course` c,`t_teacher` t,`t_team` te,`t_student` ts,`t_week` w,`t_section` se,`t_cstatus` cs,`teacher_course` tc,`course_system` system  WHERE system.`id` = c.`system_id` AND s.`student_id` = ts.`sid` AND tc.`cid` = c.`id` AND  s.`course_id` = tc.`id` AND tc.`team_id` = te.`id` AND tc.`week_id` = w.`id` AND s.`teacher_id` = t.`tid` AND se.`id`= tc.`section_id` AND cs.`id` = c.`cstatus_id` AND ts.`sid` = #{sid} AND s.status='1'")
    IPage<ScoreDTO> findStudentScore(Page page, @Param("sid") String sid);

    /**
     * 添加课程成绩对应页面
     *
     * @param courseId
     * @return
     */
    @Select("SELECT DISTINCT s.*,st.*,c.`classname`,sp.`name`,cl.`name`\n" +
            "\t\tcname FROM `t_score` s LEFT JOIN `t_student` st ON\n" +
            "\t\ts.`Student_id`=st.`sid`\n" +
            "\t\tLEFT JOIN `t_classes` c ON\n" +
            "\t\tst.`classes_id`=c.`id`\n" +
            "\t\tLEFT JOIN `t_specialty` sp ON\n" +
            "\t\tc.`specialty_id`=sp.`id`\n" +
            "\t\tLEFT JOIN\n" +
            "\t\t`t_college` cl ON\n" +
            "\t\tsp.`college_id`=cl.`id` WHERE course_id = #{value}")
    IPage addStudentScore(IPage iPage, String courseId);


    /**
     * 查找已选课程
     * @param sid
     * @return
     */
    @Select("SELECT tc.`id`,s.`point`,se.`section` sse,se.`week` sw,w.`time` wname,tc.`classroom`,c.`credit`,c.`isExam`,c.`name`,te.`name` tname,s.`score`,cs.`name` csname,c.`total_time` FROM `t_score` s,`t_course` c,`t_teacher` t,`t_team` te,`t_student` ts,`t_week` w,`t_section` se,`t_cstatus` cs,`teacher_course` tc  WHERE s.`student_id` = ts.`sid` AND tc.`cid` = c.`id` AND  s.`course_id` = tc.`id` AND tc.`team_id` = te.`id` AND tc.`week_id` = w.`id` AND s.`teacher_id` = t.`tid` AND se.`id`= tc.`section_id` AND cs.`id` = c.`cstatus_id` AND ts.`sid` = #{sid} AND tc.`end` = 0 AND c.system_id = 4")
    List<CourseDTO> findSelectCourseByStudentId(@Param("sid") String sid);

    /**
     * 统计
     * @param sid
     * @return
     */
    @Select("SELECT c.*,s.`point`,se.`section` sse,se.`week` sw,w.`time` wname,tc.`classroom`,te.`name` tname,s.`score`,cs.`name` csname,s.`absent` FROM `t_score` s,`t_course` c,`t_teacher` t,`t_team` te,`t_student` ts,`t_week` w,`t_section` se,`t_cstatus` cs,`teacher_course` tc  WHERE s.`student_id` = ts.`sid` AND tc.`cid` = c.`id` AND  s.`course_id` = tc.`id` AND tc.`team_id` = te.`id` AND tc.`week_id` = w.`id` AND s.`teacher_id` = t.`tid` AND se.`id`= tc.`section_id` AND cs.`id` = c.`cstatus_id` AND ts.`sid` = #{sid} AND s.status='1'")
    List<CourseDTO> findCourseByStudent(@Param("sid") String sid);
}
