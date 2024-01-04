package com.zxw.jwxt.mapper.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.vo.QueryCourseVO;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zxw
 * @date 2020/4/12 20:26
 */
public class CourseProvider {

    public String findCourseByClass(Page page, QueryCourseVO courseVO) {
        StringBuilder sb = new StringBuilder("SELECT c.`name`,c.`id`,tc.`total_people`,tc.`classroom`,t.`name` tname,teacher.`tname` teacherName,s.`section` sse,s.`week` sw,w.`time` wname,c.`credit`,c.`total_time` FROM `teacher_course` tc,`t_team` t,`t_section` s,`t_week` w,`t_teacher` teacher,`t_course` c WHERE tc.`cid` = c.`id` AND tc.`teacher_id` = teacher.`tid` AND tc.`team_id` = t.`id` AND tc.`section_id` = s.`id` AND tc.`week_id` = w.`id` AND tc.`classes_id` = " + courseVO.getClassesId());
        if (StringUtils.isNotEmpty(courseVO.getTeamId())) {
            sb.append(" and c.`team_id` =" + courseVO.getTeamId());
        }

        return sb.toString();
    }
}
