package com.zxw.jwxt.service;

import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.TeacherRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.mapper.TeacherRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2023-11-07
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class TeacherRoleService {
    @Autowired
    private TeacherRoleMapper teacherRoleMapper;

    public RS insertRole(TeacherRole teacherRole) {
        return teacherRoleMapper.insert(teacherRole) == 0 ? RS.error("教师权限插入失败") : RS.ok();
    }

}
