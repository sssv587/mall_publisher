package com.futurebytedance.mall_publisher.service.impl;

import com.futurebytedance.mall_publisher.bean.KeywordStats;
import com.futurebytedance.mall_publisher.mapper.KeywordStatsMapper;
import com.futurebytedance.mall_publisher.service.KeywordStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/8 - 23:14
 * @Description 关键词统计接口实现类
 */
@Service
public class KeywordStatsServiceImpl implements KeywordStatsService {
    @Autowired
    KeywordStatsMapper keywordStatsMapper;

    @Override
    public List<KeywordStats> getKeywordStats(int date, int limit) {
        return keywordStatsMapper.selectKeywordStats(date, limit);
    }
}