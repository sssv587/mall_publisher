package com.futurebytedance.mall_publisher.service;

import com.futurebytedance.mall_publisher.bean.VisitorStats;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/7 - 22:43
 * @Description 访客统计业务层接口
 */
public interface VisitorStatsService {
    List<VisitorStats> getVisitorStatsByNewFlag(int date);

    List<VisitorStats> getVisitorStatsByHour(int date);

}
