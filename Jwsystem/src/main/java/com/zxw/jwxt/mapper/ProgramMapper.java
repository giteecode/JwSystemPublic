package com.zxw.jwxt.mapper;

import com.zxw.jwxt.domain.Program;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2020-01-27
 */
public interface ProgramMapper extends BaseMapper<Program> {

    @Select("SELECT p.* FROM `program` p,`t_specialty` s WHERE p.`specialty_id` = s.`id` and p.`specialty_id` = #{specialtyId}")
    Program findProgram(@Param("specialtyId") String specialtyId);
}
