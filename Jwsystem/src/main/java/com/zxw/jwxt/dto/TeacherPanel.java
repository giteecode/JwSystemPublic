package com.zxw.jwxt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zxw
 * @date 2020/1/31 17:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPanel {
    List<NoticeDTO> noticeList;
    TeacherSchedule teacherSchedule;
    List<CourseDTO> courseList;
}
