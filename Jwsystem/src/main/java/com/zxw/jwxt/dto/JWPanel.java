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
public class JWPanel {
    int[][] absentCount;
    List<Integer> countGrade;
    int[] downCourseRate;
    int[] downCourseSectionRate;
    List<NoticeDTO> noticeList;
}
