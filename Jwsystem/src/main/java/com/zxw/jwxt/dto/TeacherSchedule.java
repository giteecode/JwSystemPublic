package com.zxw.jwxt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zxw
 * @date 2020/5/8 20:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSchedule {
    private Integer num;
    private List<String> finishName;
    private List<String> unFinishName;
    private Integer finishNum;
    private Integer unFinishNum;
}
