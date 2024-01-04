package com.zxw.jwxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxw.jwxt.domain.UserNotice;
import com.zxw.jwxt.dto.NoticeDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zxw
 * @since 2020-01-31
 */
public interface UserNoticeMapper extends BaseMapper<UserNotice> {

    @Select("select n.*,u.`username` from `notice` n,`t_user` u where u.`id` = n.`publisher` and u.`collegeId` = #{collegeId}")
    List<NoticeDTO> findNoticeByJW(@Param("collegeId") String id);

    @Select("SELECT n.* FROM `user_notice` un,`notice` n WHERE n.`id` = un.`notice_id` AND un.`sid` = #{userId}")
    List<NoticeDTO> findNoticeByStudent(@Param("userId") String id);

    @Select("select n.*,u.`username` from `notice` n,`t_user` u where u.`id` = n.`publisher`")
    List<NoticeDTO> findNoticeByTeacher(@Param("userId") String id);
}
