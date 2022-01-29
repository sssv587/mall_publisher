package com.futurebytedance.mall_publisher.mapper;

import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/1/29 - 22:01
 * @Description 商品主题统计的Mapper接口
 */
public interface ProductStatsMapper {
    //获取某一天商品的交易额
    @Select("select sum(order_amount) from product_stats where toYYYYMMDD(stt)=#{date})")
    BigDecimal getGMV(int date);
}
