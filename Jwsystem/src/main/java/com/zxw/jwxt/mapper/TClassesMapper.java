package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TClasses;
import com.zxw.jwxt.dto.ClassesDTO;
import com.zxw.jwxt.vo.QueryClassesVO;
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
public interface TClassesMapper extends BaseMapper<TClasses> {

    @Select("select cs.*,s.name sname,c.name cname,g.name gname from `t_classes` cs,`t_specialty` s,`t_college` c,`t_grade` g where cs.`college_id` = c.`id` and cs.`specialty_id` = s.`id` and g.`id` = cs.`grade_id`")
    IPage<QueryClassesVO> findAll(Page page);

    @Select("select cs.*,s.name sname,c.name cname,g.name gname from `t_classes` cs,`t_specialty` s,`t_college` c,`t_grade` g ${ew.customSqlSegment} and cs.`college_id` = c.`id` and cs.`specialty_id` = s.`id` and g.`id` = cs.`grade_id`")
    IPage<QueryClassesVO> findByParams(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select c.* from t_classes c,t_grade g where c.`grade_id` = g.`id` and c.`college_id` = #{collegeId} and g.`id` = #{gradeId}")
    List<ClassesDTO> findClassesByGrade(@Param("collegeId") String collegeId, @Param("gradeId") String gradeId);

    @Select("select c.* from t_classes c,t_grade g where c.`grade_id` = g.`id` and c.`college_id` = #{collegeId} and g.`id` = #{gradeId}")
    void findBySpecialty(@Param("specialtyId") String specialtyId, @Param("gradeId") String gradeId);
}
