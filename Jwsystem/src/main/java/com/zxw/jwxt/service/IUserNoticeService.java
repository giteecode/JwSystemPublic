package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.domain.UserNotice;
import com.zxw.jwxt.domain.UserRealm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxw
 * @since 2020-01-31
 */
public interface IUserNoticeService extends IService<UserNotice> {

    List findNoticeByJW(UserRealm realm);

    List findNoticeByStudent(UserRealm realm);

    List findNoticeByTeacher(UserRealm realm);

    boolean save(UserNotice userNotice, UserRealm realm, String type);
}
