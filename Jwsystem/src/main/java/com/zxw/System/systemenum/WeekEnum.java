package com.zxw.System.systemenum;

import lombok.AllArgsConstructor;

/**
 * @author zxw
 * @date 2020/5/2 13:31
 */
@AllArgsConstructor
public enum WeekEnum {
    MONDAY(1, "星期一"),
    TUESDAY(2, "星期二"),
    WEDNESDAY(3, "星期三"),
    THURSDAY(4, "星期四"),
    FIRDAY(5, "星期五"),
    SATURDAY(6, "星期六"),
    SUNDAY(7, "星期天"),
    ;


    private Integer value;
    private String label;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
