package com.futurebytedance.mall_publisher.service;

import com.futurebytedance.mall_publisher.bean.KeywordStats;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/8 - 23:14
 * @Description 关键词统计接口
 */
public interface KeywordStatsService {
    public List<KeywordStats> getKeywordStats(int date, int limit);
}
