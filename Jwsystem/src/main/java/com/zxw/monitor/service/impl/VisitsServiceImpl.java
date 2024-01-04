package com.zxw.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxw.jwxt.service.BaseService;
import com.zxw.monitor.domain.Visits;
import com.zxw.monitor.mapper.VisitsMapper;
import com.zxw.monitor.service.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zxw
 * @date 2019/12/18 20:23
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class VisitsServiceImpl extends BaseService implements VisitsService {

    @Autowired
    private VisitsMapper visitsMapper;

    @Override
    public void save() {
        LocalDate localDate = LocalDate.now();

        Visits visits = visitsMapper.selectOne(this.queryOne("date", localDate.toString()));
        if (visits == null) {
            visits = new Visits();
            visits.setWeekDay(getWeekDay());
            visits.setPvCounts(1L);
            visits.setIpCounts(1L);
            visits.setDate(localDate.toString());
            visitsMapper.insert(visits);
        }
    }

    @Override
    public void count() {
        LocalDate localDate = LocalDate.now();
        Visits visits = visitsMapper.selectOne(this.queryOne("date", localDate.toString()));
        visits.setPvCounts(visits.getPvCounts() + 1);
//        long ipCounts = logRepository.findIp(localDate.toString(), localDate.plusDays(1).toString());
        visits.setIpCounts((long) 10);
        visitsMapper.updateById(visits);
    }

    @Override
    public Object get() {
        Map<String, Object> map = new HashMap<>();
        LocalDate localDate = LocalDate.now();
        Visits visits = visitsMapper.selectOne(this.queryOne("date", localDate.toString()));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("date", localDate.minusDays(6).toString(), localDate.plusDays(1).toString());
        List<Visits> list = visitsMapper.selectList(queryWrapper);
        long recentVisits = 0, recentIp = 0;
        for (Visits data : list) {
            recentVisits += data.getPvCounts();
            recentIp += data.getIpCounts();
        }
        map.put("newVisits", visits.getPvCounts());
        map.put("newIp", visits.getIpCounts());
        map.put("recentVisits", recentVisits);
        map.put("recentIp", recentIp);
        return map;
    }

    @Override
    public Object getChartData() {
        Map<String, Object> map = new HashMap<>();
        LocalDate localDate = LocalDate.now();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.between("date", localDate.minusDays(6).toString(), localDate.plusDays(1).toString());
        List<Visits> list = visitsMapper.selectList(queryWrapper);
        map.put("weekDays", list.stream().map(Visits::getWeekDay).collect(Collectors.toList()));
        map.put("visitsData", list.stream().map(Visits::getPvCounts).collect(Collectors.toList()));
        map.put("ipData", list.stream().map(Visits::getIpCounts).collect(Collectors.toList()));
        return map;
    }

    public static String getWeekDay() {
        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
}
