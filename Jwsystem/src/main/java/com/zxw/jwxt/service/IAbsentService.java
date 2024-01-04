package com.zxw.jwxt.service;

import com.zxw.jwxt.domain.Absent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.domain.UserRealm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxw
 * @since 2020-01-29
 */
public interface IAbsentService extends IService<Absent> {

    int[][] countStudentAbsent(String userId);

    int[][] countStudentByJW(UserRealm realm);
}
