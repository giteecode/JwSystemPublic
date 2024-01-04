package com.zxw.jwxt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxw.common.pojo.RS;
import com.zxw.jwxt.domain.TTeacher;
import com.zxw.jwxt.domain.TeacherRole;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.dto.CourseDTO;
import com.zxw.jwxt.dto.ScheduleDTO;
import com.zxw.jwxt.mapper.TTeacherMapper;
import com.zxw.jwxt.vo.QueryCourseVO;
import com.zxw.jwxt.vo.QueryTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
public class TeacherService extends BaseService {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TTeacherMapper teacherMapper;

    @Autowired
    private TeacherRoleService teacherRoleService;

    public TTeacher findByUsername(String username) {
        TTeacher tTeacher = teacherMapper.selectById(username);
        return tTeacher;
    }

    public IPage pageQuery(QueryTeacherVO teacherVO) {
        Page page = getPage(teacherVO);
        QueryWrapper wrapper = getWrapper(teacherVO, null);
        IPage iPage = teacherMapper.selectPage(page, wrapper);
        return iPage;
    }

    public List<TTeacher> findListNoStatusAndCollege(String id) {
//        List<TTeacher> list = teacherMapper.findListNoStatusAndCollege(id);
//        return list;
        return null;
    }

    public RS save(TTeacher model) {
        TeacherRole record = new TeacherRole();
        record.setRoleId("6b4fef6e4ecb11e8bf5d34de1af4e65a");
        teacherMapper.insert(model);
        record.setTeacherId(model.getId());
        teacherRoleService.insertRole(record);
        return RS.ok();
    }

    public TTeacher findInfo(String tid) {
        TTeacher teacher = teacherMapper.selectById(tid);
        return teacher;
    }

    public Object[][] findSchedule(QueryCourseVO queryCourseVO, String userId) {
        Object[][] arr = new Object[5][7];
        List<CourseDTO> list = courseService.findScheduleByTeacher(userId, queryCourseVO.getTeamId());
        list.forEach(e -> {
            ScheduleDTO scheduleDTO = new ScheduleDTO(e.getName(), e.getWname(), e.getNname(), e.getClassroom(), null);
            switch (e.getSse()) {
                case "1-2节":
                    parseSchedule(arr, e, scheduleDTO, 0);
                    break;
                case "3-4节":
                    parseSchedule(arr, e, scheduleDTO, 1);
                    break;
                case "5-6节":
                    parseSchedule(arr, e, scheduleDTO, 2);
                    break;
                case "7-8节":
                    parseSchedule(arr, e, scheduleDTO, 3);
                    break;
                case "9-10节":
                    parseSchedule(arr, e, scheduleDTO, 4);
                    break;
            }
        });
        return arr;
    }

    private void parseSchedule(Object[][] arr, CourseDTO e, ScheduleDTO scheduleDTO, int i) {
        switch (e.getSw()) {
            case "周一":
                List<ScheduleDTO> o1 = (List<ScheduleDTO>) arr[i][0];
                if (o1 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][0] = list;
                } else {
                    o1.add(scheduleDTO);
                }
                break;
            case "周二":
                List<ScheduleDTO> o2 = (List<ScheduleDTO>) arr[i][1];
                if (o2 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][1] = list;
                } else {
                    o2.add(scheduleDTO);
                }
                break;
            case "周三":
                List<ScheduleDTO> o3 = (List<ScheduleDTO>) arr[i][2];
                if (o3 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][2] = list;
                } else {
                    o3.add(scheduleDTO);
                }
                break;
            case "周四":
                List<ScheduleDTO> o4 = (List<ScheduleDTO>) arr[i][3];
                if (o4 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][3] = list;
                } else {
                    o4.add(scheduleDTO);
                }
                break;
            case "周五":
                List<ScheduleDTO> o5 = (List<ScheduleDTO>) arr[i][4];
                if (o5 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][4] = list;
                } else {
                    o5.add(scheduleDTO);
                }
                break;
            case "周六":
                List<ScheduleDTO> o6 = (List<ScheduleDTO>) arr[i][5];
                if (o6 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][5] = list;
                } else {
                    o6.add(scheduleDTO);
                }
                break;
            case "周日":
                List<ScheduleDTO> o7 = (List<ScheduleDTO>) arr[i][6];
                if (o7 == null) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(scheduleDTO);
                    arr[i][6] = list;
                } else {
                    o7.add(scheduleDTO);
                }
                break;
        }
    }


    public List listajax(UserRealm realm) {
        List list = teacherMapper.selectList(this.queryOne("college_id", realm.getCollegeId()));
        return list;
    }

    public RS edit(TTeacher tTeacher) {
        int id = teacherMapper.update(tTeacher, this.queryOne("id", tTeacher.getId()));
        return id == 1 ? RS.ok() : RS.error("修改失败");
    }

    public RS delete(String tid) {
        int i = teacherMapper.deleteById(tid);
        return i == 1 ? RS.ok() : RS.error("删除失败");
    }

    public List<TTeacher> findByCollege(String collegeId) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("college_id", collegeId);
        List<TTeacher> list = teacherMapper.selectList(wrapper);
        return list;
    }
}
