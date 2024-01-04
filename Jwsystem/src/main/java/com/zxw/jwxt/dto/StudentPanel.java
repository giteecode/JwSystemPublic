package com.zxw.jwxt.dto;

import com.zxw.jwxt.domain.TSpecialty;
import com.zxw.jwxt.domain.TStudent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zxw
 * @date 2020/1/29 16:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPanel {
    // 总学时
    Integer totalTime = 0;
    TSpecialty specialty;
    // 课程总数
    Integer CourseNum = 0;
    // 到课率
    Double upCourseRate = 0.0;
    //合格率
    Double eligiableRate = 0.0;
    // 违纪
    Integer disciplinary = 0;
    // 奖学金
    Integer scholarship = 0;
    // 上网时间
    Integer internet = 0;
    // 缴费及欠费
    Integer paid = 0;
    // 心理卫生
    Integer health = 0;
    // 校园卡的余额
    Integer balance = 0;
    // 及格数量
    Integer passNum = 0;
    TStudent student;
    // 学生能力分析 - 课程数量
    Integer gonggong = 0;
    Integer zhuanye = 0;
    Integer tongshi = 0;
    Integer shijan = 0;
    Integer xueke = 0;
    // 学生能力分析
    // 公共率
    Double gonggongRate = 0.0;
    // 专业率
    Double zhuanyeRate = 0.0;
    // 通识教育率
    Double tongshiRate = 0.0;
    // 实践率
    Double shijanRate = 0.0;
    // 学科率
    Double xuekeRate = 0.0;
    // 缺勤统计
    int[][] absentCount;
}
