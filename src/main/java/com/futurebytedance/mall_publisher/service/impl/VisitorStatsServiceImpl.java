package com.futurebytedance.mall_publisher.service.impl;

import com.futurebytedance.mall_publisher.bean.VisitorStats;
import com.futurebytedance.mall_publisher.mapper.VisitorStatsMapper;
import com.futurebytedance.mall_publisher.service.VisitorStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/7 - 22:44
 * @Description 访客统计接口实现类
 */
@Service
public class VisitorStatsServiceImpl implements VisitorStatsService {
    @Autowired
    VisitorStatsMapper visitorStatsMapper;

    @Override
    public List<VisitorStats> getVisitorStatsByNewFlag(int date) {
        return visitorStatsMapper.selectVisitorStatsByNewFlag(date);
    }

    @Override
    public List<VisitorStats> getVisitorStatsByHour(int date) {
        return visitorStatsMapper.selectVisitorStatsByHour(date);
    }
}
