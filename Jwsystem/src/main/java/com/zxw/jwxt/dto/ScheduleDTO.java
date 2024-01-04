package com.zxw.jwxt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.zxw.jwxt.vo
 * @author: Administrator
 * @DATE: 2019/12/31 14:35:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private String courseName;
    private String week;
    private String teacherName;
    private String classroom;
    private ScheduleDTO children;

}
