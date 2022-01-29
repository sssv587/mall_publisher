package com.futurebytedance.mall_publisher.service;

import java.math.BigDecimal;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/1/29 - 22:02
 * @Description 商品统计service接口
 */
public interface ProductStatsService {
    //获取某一天交易总额
    BigDecimal getGMV(int date);
}
