package com.zxw.jwxt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxw.System.systemenum.PersonType;
import com.zxw.jwxt.domain.*;
import com.zxw.jwxt.dto.NoticeDTO;
import com.zxw.jwxt.mapper.UserNoticeMapper;
import com.zxw.jwxt.service.IUserNoticeService;
import com.zxw.jwxt.service.StudentService;
import com.zxw.jwxt.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxw
 * @since 2020-01-31
 */
@Service
public class UserNoticeServiceImpl extends ServiceImpl<UserNoticeMapper, UserNotice> implements IUserNoticeService {
    @Autowired
    private UserNoticeMapper userNoticeMapper;
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public List<NoticeDTO> findNoticeByJW(UserRealm realm) {
        TUser user = (TUser) realm;
        List<NoticeDTO> list = userNoticeMapper.findNoticeByJW(user.getCollegeId());
        return list;
    }

    @Override
    public List<NoticeDTO> findNoticeByStudent(UserRealm realm) {
        List<NoticeDTO> list = userNoticeMapper.findNoticeByStudent(realm.getId());
        return list;
    }

    @Override
    public List<NoticeDTO> findNoticeByTeacher(UserRealm realm) {
        List<NoticeDTO> list = userNoticeMapper.findNoticeByTeacher(realm.getId());
        return list;
    }

    @Override
    public boolean save(UserNotice userNotice, UserRealm realm, String type) {
        List<TStudent> studentList = studentService.findByCollege(realm.getCollegeId());
        List<TTeacher> teacherList = teacherService.findByCollege(realm.getCollegeId());
        if (type.equals(PersonType.STUDENT)) {
            studentList.forEach(e -> {
                userNotice.setSid(e.getId());
                userNoticeMapper.insert(userNotice);
            });
        } else if (type.equals(PersonType.TEACHER)) {
            teacherList.forEach(e -> {
                userNotice.setTid(e.getId());
                userNoticeMapper.insert(userNotice);
            });
        } else {

        }
        return true;
    }
}
