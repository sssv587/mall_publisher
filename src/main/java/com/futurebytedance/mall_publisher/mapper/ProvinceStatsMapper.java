package com.futurebytedance.mall_publisher.mapper;

import com.futurebytedance.mall_publisher.bean.ProvinceStats;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/7 - 22:00
 * @Description 按照地区统计交易额
 */
public interface ProvinceStatsMapper {
    //按地区查询交易额
    @Select("select province_name,sum(order_amount) order_amount " +
            "from province_stats_2021 where toYYYYMMDD(stt)=#{date} " +
            "group by province_id ,province_name ")
    public List<ProvinceStats> selectProvinceStats(int date);
}
