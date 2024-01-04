package com.zxw.monitor.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * pv 与 ip 统计
 *
 * @author zxw
 * @date 2019/12/18 20:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "visits")
public class Visits implements Serializable {

    @TableId
    private Long id;

    // 日期
    private String date;

    // 访问量
    private Long pvCounts;

    // 访问用户数
    private Long ipCounts;

    // 创建时间
    private Timestamp createTime;

    // 星期
    private String weekDay;
}
