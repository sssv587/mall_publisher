package com.futurebytedance.mall_publisher.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/2/7 - 21:58
 * @Description 地区交易额统计实体类
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProvinceStats {
    private String stt;
    private String edt;
    private String province_id;
    private String province_name;
    private BigDecimal order_amount;
    private String ts;
}
