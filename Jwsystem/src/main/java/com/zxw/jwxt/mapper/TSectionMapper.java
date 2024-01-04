package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxw.jwxt.domain.TSection;
import com.zxw.jwxt.domain.TStudent;
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
public interface TSectionMapper extends BaseMapper<TSection> {
    @Select("SELECT DISTINCT * FROM `t_student` s LEFT JOIN `t_classes` c ON\n" +
            "\t\ts.`classes_id`=c.`id` WHERE c.`id`=#{value}")
    List<TStudent> findAll(String cid);
}
