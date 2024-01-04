package com.zxw.jwxt.mapper;

import com.zxw.jwxt.domain.TTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface TTeacherMapper extends BaseMapper<TTeacher> {
    @Select(" SELECT *\n" +
            "        FROM `t_teacher` t\n" +
            "                 LEFT JOIN `t_college` c ON t.`college_id` = c.`id`")
    List<TTeacher> findAll();
}
