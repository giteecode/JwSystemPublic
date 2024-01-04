package com.zxw.jwxt.controller;

import com.zxw.jwxt.domain.TClasses;
import com.zxw.jwxt.domain.TSpecialty;
import com.zxw.jwxt.domain.TStudent;
import com.zxw.jwxt.dto.*;
import com.zxw.jwxt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxw
 * @date 2019/11/6 20:23
 */
@RequestMapping("/api/main")
@Controller
public class IndexController extends BaseController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ITeacherCourseService teacherCourseService;

    @Autowired
    private IAbsentService absentService;

    @Autowired
    private IUserNoticeService userNoticeService;

    @GetMapping("/")
    public String index() {
        return "web/login";
    }

    @GetMapping("/main0")
    public String main(Model model) {
        return "/web/index0";
    }

    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> mainData() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage nonHeapMemoryUsage = mem.getNonHeapMemoryUsage();
        MemoryUsage heapMemoryUsage = mem.getHeapMemoryUsage();
        //初始的总内存
        long init = heapMemoryUsage.getInit();
        //最大可用内存
        long max = heapMemoryUsage.getMax();
        //已使用的内存
        long used = heapMemoryUsage.getUsed();
        long total = heapMemoryUsage.getCommitted();
        long max1 = nonHeapMemoryUsage.getMax() / 1024 / 1024;
        long free = (nonHeapMemoryUsage.getCommitted() - nonHeapMemoryUsage.getUsed()) / 1024 / 1024;
        long used1 = nonHeapMemoryUsage.getUsed() / 1024 / 1024;
        long total1 = nonHeapMemoryUsage.getCommitted() / 1024 / 1024;
        long totalMemory = total / 1024 / 1024;
        long freeMemory = (total - used) / 1024 / 1024;
        long maxMemory = max / 1024 / 1024;
        long UsedMemory = used / 1024 / 1024;
        map.put("totalMemory", totalMemory);
        map.put("freeMemory", freeMemory);
        map.put("maxMemory", maxMemory);
        map.put("UsedMemory", UsedMemory);
        map.put("nontotalMemory", total1);
        map.put("nonfreeMemory", free);
        map.put("nonmaxMemory", max1);
        map.put("nonUsedMemory", used1);
        return map;
    }

    @GetMapping("/page/{page}")
    public String page(@PathVariable("page") String page) {
        return "/web/" + page;
    }

    @GetMapping("/page/{category}/{page}")
    public String page(@PathVariable("category") String category, @PathVariable("page") String page) {
        return "/web/" + category + "/" + page;
    }

    @GetMapping("/findStudentPanel")
    public ResponseEntity findStudentPanel() {
        DecimalFormat df = new DecimalFormat(".0");
        TStudent student = (TStudent) getRealm();
        TClasses classes = classesService.findById(student.getClassesId());
        TSpecialty specialty = specialtyService.findById(classes.getSpecialtyId());
        List<CourseDTO> list = scoreService.findCourseByStudent(student.getId());
        StudentPanel studentPanel = new StudentPanel();
        studentPanel.setCourseNum(list.size());
        studentPanel.setStudent(student);
        studentPanel.setSpecialty(specialty);
        // 成绩能力模型
        int tongshi = 0;
        int zhuanye = 0;
        int shijian = 0;
        int gonggong = 0;
        int xueke = 0;
        for (CourseDTO courseDTO : list) {
            studentPanel.setTotalTime(studentPanel.getTotalTime() + courseDTO.getTotalTime());
            if (courseDTO.getScore() >= 60) {
                // 合格率
                studentPanel.setPassNum(studentPanel.getPassNum() + 1);
            }
            if (courseDTO.getSystemId().equals("1")) {
                gonggong += 1;
                studentPanel.setGonggong(studentPanel.getGonggong() + courseDTO.getScore());
            }
            if (courseDTO.getSystemId().equals("2")) {
                zhuanye += 1;
                studentPanel.setZhuanye(studentPanel.getZhuanye() + courseDTO.getScore());
            }
            if (courseDTO.getSystemId().equals("4")) {
                tongshi += 1;
                studentPanel.setTongshi(studentPanel.getTongshi() + courseDTO.getScore());
            }
            if (courseDTO.getSystemId().equals("5")) {
                shijian += 1;
                studentPanel.setShijan(studentPanel.getShijan() + courseDTO.getScore());
            }
            if (courseDTO.getSystemId().equals("6")) {
                xueke += 1;
                studentPanel.setXueke(studentPanel.getXueke() + courseDTO.getScore());
            }
        }
        // 缺勤次数
        studentPanel.setDisciplinary(student.getAbsent());
        // 合格率
        studentPanel.setEligiableRate(studentPanel.getPassNum() == 0 ? 0 : Double.valueOf(df.format(studentPanel.getPassNum() / (studentPanel.getCourseNum() * 1.0) * 100)));
        // 到课率
        studentPanel.setUpCourseRate(studentPanel.getDisciplinary() == 0 ? 0 : Double.valueOf(df.format(100.0 - studentPanel.getDisciplinary() / (studentPanel.getTotalTime() / 2.0) * 100)));
        // 成绩能力模型
        studentPanel.setGonggongRate(studentPanel.getGonggong() == 0 ? 0 : Double.valueOf(df.format(studentPanel.getGonggong() / (gonggong * 100.0) * 100)));
        studentPanel.setZhuanyeRate(studentPanel.getZhuanye() == 0 ? 0 : Double.valueOf(df.format(studentPanel.getZhuanye() / (zhuanye * 100.0) * 100)));
        studentPanel.setTongshiRate(studentPanel.getTongshi() == 0 ? 0 : Double.valueOf(df.format(studentPanel.getTongshi() / (tongshi * 100.0) * 100)));
        studentPanel.setXuekeRate(studentPanel.getXueke() == 0 ? 0 : Double.valueOf(df.format(studentPanel.getXueke() / (xueke * 100.0) * 100)));
        studentPanel.setShijanRate(studentPanel.getShijan() == 0 ? 0 : Double.valueOf(df.format(studentPanel.getShijan() / (shijian * 100.0) * 100)));
        // 统计缺勤
        int[][] absentCount = absentService.countStudentAbsent(getUserId());
        studentPanel.setAbsentCount(absentCount);
        return ResponseEntity.ok(studentPanel);
    }

    @GetMapping("/findUserPanel")
    public ResponseEntity findUserPanel() {
        JWPanel jwPanel = new JWPanel();
        // 统计最近7天缺勤
        int[][] absentCount = absentService.countStudentByJW(getRealm());
        jwPanel.setAbsentCount(absentCount);
        // 统计在校人数
        List<Integer> countGrade = studentService.countPeople(getRealm());
        jwPanel.setCountGrade(countGrade);
        // 统计缺课课时
        int[] courseDTOList = courseService.countDownCourseRate(getRealm(), absentCount);
        jwPanel.setDownCourseRate(courseDTOList);
        // 统计旷课时段
        int[] list = teacherCourseService.countDownCourseSection(getRealm(), absentCount);
        jwPanel.setDownCourseSectionRate(list);
        // 通知公告
        List moticeLists = userNoticeService.findNoticeByJW(getRealm());
        jwPanel.setNoticeList(moticeLists);
        return ResponseEntity.ok(jwPanel);
    }

    @GetMapping("/findTeacherPanel")
    public ResponseEntity findTeacherPanel(String teamId) {
        TeacherPanel teacherPanel = new TeacherPanel();
        // 通知公告
        List moticeLists = userNoticeService.findNoticeByTeacher(getRealm());
        teacherPanel.setNoticeList(moticeLists);
        // 教学完成进度
        TeacherSchedule teacherSchedule = teacherCourseService.countTeacherSchedule(getRealm(), teamId);
        // 缺课情况
        List<CourseDTO> list = teacherCourseService.countAbsent(getRealm(), teamId);
        teacherPanel.setCourseList(list);
        teacherPanel.setTeacherSchedule(teacherSchedule);
        return ResponseEntity.ok(teacherPanel);
    }
}
