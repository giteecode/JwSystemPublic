package com.zxw.jwxt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxw.common.exception.BadRequestException;
import com.zxw.jwxt.domain.Absent;
import com.zxw.jwxt.domain.TUser;
import com.zxw.jwxt.domain.TeacherCourse;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.dto.TeacherSchedule;
import com.zxw.jwxt.mapper.TeacherCourseMapper;
import com.zxw.jwxt.service.ITeacherCourseService;
import com.zxw.jwxt.vo.QueryCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxw
 * @since 2020-01-26
 */
@Service
public class TeacherCourseServiceImpl extends ServiceImpl<TeacherCourseMapper, TeacherCourse> implements ITeacherCourseService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public IPage findApply(QueryCourseVO queryCourseVO, UserRealm realm) {
        Page page = new Page(queryCourseVO.getOffset(), queryCourseVO.getLimit());
        IPage ipage = teacherCourseMapper.findApply(page, realm.getCollegeId());
        return ipage;
    }

    @Override
    public IPage findApplyByTeacher(QueryCourseVO queryCourseVO, UserRealm realm) {
        Page page = new Page(queryCourseVO.getOffset(), queryCourseVO.getLimit());
        IPage ipage = teacherCourseMapper.findApplyByTeacher(page, realm.getId());
        return ipage;
    }

    @Override
    public boolean agree(String id) {
        TeacherCourse teacherCourse = teacherCourseMapper.selectById(id);
        if (teacherCourse != null) {
            teacherCourse.setApply(1);
            teacherCourseMapper.updateById(teacherCourse);
            return true;
        }
        throw new BadRequestException("未查到该门课程");
    }

    @Override
    public boolean back(String id) {
        TeacherCourse teacherCourse = teacherCourseMapper.selectById(id);
        if (teacherCourse != null) {
            teacherCourse.setApply(2);
            teacherCourseMapper.updateById(teacherCourse);
            return true;
        }
        throw new BadRequestException("未查到该门课程");
    }

    @Override
    public List<TeacherCourse> findCourseByStudent(String id) {
        return null;
    }

    @Override
    public int[] countDownCourseSection(UserRealm realm, int[][] absentCount) {
        TUser user = (TUser) realm;
        int[] arr = new int[7];
//        List<Absent> list = teacherCourseMapper.countDownCourseSection(user.getCollegeId(), new Date(), new Date());
        for (int j = 0; j < absentCount[0].length; j++) {
            for (int i = 0; i < absentCount.length; i++) {
                arr[i] = arr[i] + absentCount[i][j];
            }
        }
//        int[] arr = countAbsentSection(list);
        return arr;
    }

    @Override
    public IPage findClassCoure(QueryCourseVO courseVO) {
        Page page = new Page(courseVO.getOffset(), courseVO.getLimit());
        IPage classCourse = teacherCourseMapper.findClassCourse(page, courseVO);
        return classCourse;
    }

    @Override
    public Integer countCourseScore(Integer begin, Integer end, String cid) {
        Integer list = teacherCourseMapper.countCourseScore(begin, end, cid);
        return list;
    }

    @Override
    public TeacherSchedule countTeacherSchedule(UserRealm realm, String teamId) {
        TeacherSchedule teacherSchedule = new TeacherSchedule();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("teacher_id", realm.getId());
        queryWrapper.eq("team_id", teamId);
        Integer count = teacherCourseMapper.selectCount(queryWrapper);
        queryWrapper.eq("end", 2);
        Integer count1 = teacherCourseMapper.selectCount(queryWrapper);
        List<String> list = teacherCourseMapper.countFinishCourseName(realm.getId(), teamId, 2);
        List<String> list1 = teacherCourseMapper.countFinishCourseName(realm.getId(), teamId, 0);
        teacherSchedule.setFinishNum(count1);
        teacherSchedule.setNum(count);
        teacherSchedule.setUnFinishNum(count - count1);
        teacherSchedule.setFinishName(list);
        teacherSchedule.setUnFinishName(list1);
        return teacherSchedule;
    }

    @Override
    public List<CourseDTO> countAbsent(UserRealm realm, String teamId) {
        List<CourseDTO> list = teacherCourseMapper.countAbsent(realm.getId(), teamId);
        return list;
    }

    public int[] countAbsentSection(List<Absent> list) {
        int[] arr = new int[5];
        for (Absent absent : list) {
            if (absent.getSectionId().equals("1") || absent.getSectionId().equals("11") || absent.getSectionId().equals("16") || absent.getSectionId().equals("26") || absent.getSectionId().equals("6") || absent.getSectionId().equals("31") || absent.getSectionId().equals("21")) {
                arr[0]++;
            }
            if (absent.getSectionId().equals("12") || absent.getSectionId().equals("22") || absent.getSectionId().equals("17") || absent.getSectionId().equals("27") || absent.getSectionId().equals("7") || absent.getSectionId().equals("32") || absent.getSectionId().equals("2")) {
                arr[1]++;
            }
            if (absent.getSectionId().equals("3") || absent.getSectionId().equals("13") || absent.getSectionId().equals("18") || absent.getSectionId().equals("28") || absent.getSectionId().equals("8") || absent.getSectionId().equals("33") || absent.getSectionId().equals("23")) {
                arr[2]++;
            }
            if (absent.getSectionId().equals("4") || absent.getSectionId().equals("14") || absent.getSectionId().equals("19") || absent.getSectionId().equals("29") || absent.getSectionId().equals("9") || absent.getSectionId().equals("34") || absent.getSectionId().equals("24")) {
                arr[3]++;
            }
            if (absent.getSectionId().equals("5") || absent.getSectionId().equals("10") || absent.getSectionId().equals("15") || absent.getSectionId().equals("20") || absent.getSectionId().equals("25") || absent.getSectionId().equals("30") || absent.getSectionId().equals("35")) {
                arr[4]++;
            }
        }
        return arr;
    }
}
