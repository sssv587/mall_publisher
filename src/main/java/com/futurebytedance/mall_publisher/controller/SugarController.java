package com.futurebytedance.mall_publisher.controller;

import com.futurebytedance.mall_publisher.service.ProductStatsService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2022/1/29 - 22:03
 * @Description 大屏展示的控制层
 * 主要职责：接收客户端请求(request)，对请求进行处理，并给客户端相应(response)
 * @RestController = @Controller + @ResponseBody
 * @RequestMapping() 可以加在类和方法上 加载类上，相当于指定了访问路径的命名空间
 */
@RestController
@RequestMapping("/api/sugar")
public class SugarController {
    //将service注入进来
    @Autowired
    ProductStatsService productStatsService;

    /**
     * 请求路径: /api/sugar/gmv
     *
     * @return {
     * "status":0,
     * "msg":"",
     * "data":12454646.4564654
     * }
     */
    @RequestMapping("/gmv")
    public String getGMV(@RequestParam(value = "date", defaultValue = "0") Integer date) {
        if (date == 0) {
            date = now();
        }
        BigDecimal gmv = productStatsService.getGMV(date);
        return "{" +
                "\"status\":0," +
                "\"msg\":\"\"," +
                "\"data\":" + gmv +
                "}";
    }

    private Integer now() {
        return Integer.valueOf(DateFormatUtils.format(new Date(), "yyyyMMdd"));
    }
}
