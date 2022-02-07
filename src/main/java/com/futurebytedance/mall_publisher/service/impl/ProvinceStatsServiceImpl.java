package com.futurebytedance.mall_publisher.service.impl;

import com.futurebytedance.mall_publisher.bean.ProvinceStats;
import com.futurebytedance.mall_publisher.mapper.ProvinceStatsMapper;
import com.futurebytedance.mall_publisher.service.ProductStatsService;
import com.futurebytedance.mall_publisher.service.ProvinceStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/7 - 22:03
 * @Description
 */
@Service
public class ProvinceStatsServiceImpl implements ProvinceStatsService {
    //注入mapper
    @Autowired
    ProvinceStatsMapper provinceStatsMapper;

    @Override
    public List<ProvinceStats> getProvinceStats(int date) {
        return provinceStatsMapper.selectProvinceStats(date);
    }
}
