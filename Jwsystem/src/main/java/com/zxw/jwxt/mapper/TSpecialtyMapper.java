package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.jwxt.domain.TSpecialty;
import com.zxw.jwxt.vo.QuerySpecialtyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Mapper
public interface TSpecialtyMapper extends BaseMapper<TSpecialty> {
    @Select("  SELECT DISTINCT s.`id`,\n" +
            "                        s.`name`,\n" +
            "                        s.`status`,s.`time`,s.`category`,\n" +
            "                        s.`college_id`,\n" +
            "                        c.`name` cname,\n" +
            "                        c.`status`\n" +
            "                                 cstatus,\n" +
            "                        c.`id`   cid\n" +
            "        FROM `t_specialty` s\n" +
            "                 LEFT JOIN `t_college` c ON\n" +
            "            s.`college_id` = c.`id` ORDER BY c.`name`")
    IPage<QuerySpecialtyVO> findAll(Page page);

    @Select("select s.*,c.`name` cname,c.`status` cstatus from `t_specialty` s,`t_college` c ${ew.customSqlSegment} and s.`college_id` = c.`id`")
    IPage<QuerySpecialtyVO> findByJwUser(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    @Update(" update t_specialty\n" +
            "        set status=1\n" +
            "        where id\n" +
            "                  = #{id,jdbcType=VARCHAR}")
    int deleteBatch(String id);
}
