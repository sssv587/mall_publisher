package com.futurebytedance.mall_publisher.mapper;

import com.futurebytedance.mall_publisher.bean.VisitorStats;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/7 - 22:42
 * @Description 访客统计接口
 */
public interface VisitorStatsMapper {
    //新老访客流量统计
    @Select("select is_new,sum(uv_ct) uv_ct,sum(pv_ct) pv_ct," +
            "sum(sv_ct) sv_ct, sum(uj_ct) uj_ct,sum(dur_sum) dur_sum " +
            "from visitor_stats_2021 where toYYYYMMDD(stt)=#{date} group by is_new")
    List<VisitorStats> selectVisitorStatsByNewFlag(int date);

    //分时流量统计
    @Select("select sum(if(is_new='1', visitor_stats_2021.uv_ct,0)) new_uv,toHour(stt) hr," +
            "sum(visitor_stats_2021.uv_ct) uv_ct, sum(pv_ct) pv_ct, sum(uj_ct) uj_ct " +
            "from visitor_stats_2021 where toYYYYMMDD(stt)=#{date} group by toHour(stt)")
    List<VisitorStats> selectVisitorStatsByHour(int date);
}
