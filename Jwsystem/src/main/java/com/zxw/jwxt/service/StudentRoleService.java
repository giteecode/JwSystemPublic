package com.zxw.jwxt.service;

import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.StudentRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.mapper.StudentRoleMapper;
import org.apache.poi.ss.formula.functions.T;
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
public class StudentRoleService {
    @Autowired
    private StudentRoleMapper studentRoleMapper;

    public RS insert(StudentRole studentRole) {
        int insert = studentRoleMapper.insert(studentRole);
        return insert == 0 ? RS.error("学生权限插入失败") : RS.ok("插入成功");
    }
}
