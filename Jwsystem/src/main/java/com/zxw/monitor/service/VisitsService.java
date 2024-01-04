package com.zxw.monitor.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author zxw
 * @date 2019/12/18 20:23
 */
public interface VisitsService {

    /**
     * 提供给定时任务，每天0点执行
     */
    void save();

    /**
     * 新增记录
     *
     */
    @Async
    void count();

    /**
     * 获取数据
     *
     * @return /
     */
    Object get();

    /**
     * getChartData
     *
     * @return /
     */
    Object getChartData();
}
