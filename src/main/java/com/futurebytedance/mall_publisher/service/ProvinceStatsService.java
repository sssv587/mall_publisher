package com.futurebytedance.mall_publisher.service;

import com.futurebytedance.mall_publisher.bean.ProvinceStats;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/7 - 22:02
 * @Description 按照地区统计的业务接口
 */
public interface ProvinceStatsService {
    List<ProvinceStats> getProvinceStats(int date);
}
