package com.futurebytedance.mall_publisher.service;

import com.futurebytedance.mall_publisher.bean.ProductStats;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/1/29 - 22:02
 * @Description 商品统计service接口
 */
public interface ProductStatsService {
    //获取某一天交易总额
    BigDecimal getGMV(int date);

    //获取某一天不同品牌的交易额
    List<ProductStats> getProductStatsByTrademark(int date, int limit);

    //获取某一天不同品类的交易额
    List<ProductStats> getProductStatsByCategory3(int date, int limit);

    //统计某天不同 SPU 商品交易额排名
    List<ProductStats> getProductStatsGroupBySpu(int date, int limit);
}
