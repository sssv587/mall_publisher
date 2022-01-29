package com.futurebytedance.mall_publisher.service.impl;

import com.futurebytedance.mall_publisher.mapper.ProductStatsMapper;
import com.futurebytedance.mall_publisher.service.ProductStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/1/29 - 22:02
 * @Description 商品统计Service接口实现类
 */
@Service //标识是Spring的Service层组件，将对象的创建交给Spring的IOC管理
public class ProductStatsServiceImpl implements ProductStatsService {
    //自动注入 在容器中，需要ProductStatsMapper类型的对象，赋值给当前属性
    @Autowired
    ProductStatsMapper productStatsMapper;

    @Override
    public BigDecimal getGMV(int date) {
        return productStatsMapper.getGMV(date);
    }
}
