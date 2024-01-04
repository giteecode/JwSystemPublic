package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.CourseSystem;
import com.zxw.jwxt.dto.CourseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2020-01-05
 */
public interface CourseSystemMapper extends BaseMapper<CourseSystem> {

    @Select("SELECT c.`name`,c.`id`,c.`isExam`,n.`name` nname,cs.`name` csname,e.`name` ename,co.`name` collegeName,c.`credit`,c.`total_time`,system.`name` FROM t_course c,t_nature n,t_cstatus cs, t_examway e,t_college co,`course_system` system WHERE  c.nature_id = n.id AND c.`way_id` = e.`id` AND c.cstatus_id = cs.id AND c.college_id = co.id AND system.`id` = c.`system_id` AND c.`status` = '1' AND c.`system_id` = #{systemId}")
    IPage<CourseDTO> findCourseBySystemId(Page page, @Param("systemId") String systemId);
}
