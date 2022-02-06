package com.futurebytedance.mall_publisher.mapper;

import com.futurebytedance.mall_publisher.bean.ProductStats;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

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

    //获取某一天不同品牌的交易额
    //如果mybatis的方法中，有多个参数，每个参数前需要用@param注解指定参数的名称
    @Select("select spu_id,spu_name,sum(order_amount) order_amount," +
            "sum(order_ct) order_ct from product_stats " +
            "where toYYYYMMDD(stt)=#{date} group by spu_id,spu_name " +
            "having order_amount>0 order by order_amount desc limit #{limit} ")
    List<ProductStats> getProductStatsByTrademark(@Param("date") int date, @Param("limit") int limit);

    //获取某一天不同品类的交易额
    @Select("select category3_id,category3_name,sum(order_amount) order_amount " +
            "from product_stats " +
            "where toYYYYMMDD(stt)=#{date} group by category3_id,category3_name " +
            "having order_amount>0 order by order_amount desc limit #{limit}")
    List<ProductStats> getProductStatsByCategory3(@Param("date") int date, @Param("limit") int limit);

    //统计某天不同 SPU 商品交易额排名
    @Select("select spu_id,spu_name,sum(order_amount) order_amount," +
            "sum(order_ct) order_ct from product_stats " +
            "where toYYYYMMDD(stt)=#{date} group by spu_id,spu_name " +
            "having order_amount>0 order by order_amount desc limit #{limit} ")
    List<ProductStats> getProductStatsGroupBySpu(@Param("date") int date, @Param("limit") int limit);
}
