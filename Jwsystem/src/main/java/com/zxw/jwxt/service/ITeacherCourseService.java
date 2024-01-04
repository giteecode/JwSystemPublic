package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.jwxt.domain.TeacherCourse;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.dto.TeacherSchedule;
import com.zxw.jwxt.vo.QueryCourseVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxw
 * @since 2020-01-26
 */
public interface ITeacherCourseService extends IService<TeacherCourse> {

    IPage findApply(QueryCourseVO queryCourseVO, UserRealm realm);

    IPage findApplyByTeacher(QueryCourseVO queryCourseVO, UserRealm realm);

    boolean agree(String id);

    boolean back(String id);

    List<TeacherCourse> findCourseByStudent(String id);

    int[] countDownCourseSection(UserRealm realm, int[][] absentCount);

    IPage findClassCoure(QueryCourseVO courseVO);

    Integer countCourseScore(Integer begin, Integer end, String cid);

    TeacherSchedule countTeacherSchedule(UserRealm realm, String teamId);

    List<CourseDTO> countAbsent(UserRealm realm, String teamId);
}
