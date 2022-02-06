package com.futurebytedance.mall_publisher.controller;

import com.futurebytedance.mall_publisher.bean.ProductStats;
import com.futurebytedance.mall_publisher.service.ProductStatsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

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
     * 请求路径
     * $API_HOST/api/sugar/spu?limit=10
     * -返回数据的格式
     * {
     * "status": 0,
     * "data": {
     * "columns": [{
     * "name": "商品SPU名称",
     * "id": "spu_name"
     * },
     * {
     * "name": "交易额",
     * "id": "order_amount"
     * },
     * ],
     * "rows": [
     * {
     * "spu_name": "XXX",
     * "order_amount": "XXX"*
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/spu")
    public String getProductStatsBySPU(
            @RequestParam(value = "date", defaultValue = "0") Integer date,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        if (date == 0) {
            date = now();
        }
        //调用service层方法，获取按spu统计数据
        List<ProductStats> productStatsBySPUList = productStatsService.getProductStatsGroupBySpu(date, limit);
        //初始化表头信息
        StringBuilder jsonBuilder = new StringBuilder("{" +
                "\"status\": 0," +
                "\"data\": {" +
                "\"columns\": [{" +
                "\"name\": \"商品SPU名称\"," +
                "\"id\": \"spu_name\"" +
                "}," +
                "{" +
                "\"name\": \"交易额\"," +
                "\"id\": \"order_amount\"" +
                "}," +
                "{" +
                "\"name\": \"订单数\"," +
                "\"id\": \"order_ct\"" +
                "}" +
                "]," +
                "\"rows\": [");
        //对查询出来的数据进行遍历，将每一条遍历的结果封装为json的一行数据
        for (int i = 0; i < productStatsBySPUList.size(); i++) {
            ProductStats productStats = productStatsBySPUList.get(i);
            if (i >= 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("{" + "\"spu_name\": \"").append(productStats.getSpu_name()).append("\",").append("\"order_amount\":").append(productStats.getOrder_amount()).append(",").append("\"order_ct\":").append(productStats.getOrder_ct()).append("}");
        }

        jsonBuilder.append("]}}");
        return jsonBuilder.toString();
    }

    /**
     * 处理请求的路径
     * $API_HOST/api/sugar/category3?limit=5
     * 返回值格式
     * {
     * "status": 0,
     * "data": [
     * {
     * "name": "PC",
     * "value": 97
     * },
     * {
     * "name": "iOS",
     * "value": 50
     * }
     * ]
     * }
     */
    @RequestMapping("/category3")
    public Map getProductStatsByCategory3(
            @RequestParam(value = "date", defaultValue = "0") Integer date,
            @RequestParam(value = "limit", defaultValue = "0") Integer limit) {
        if (date == 0) {
            date = now();
        }
        //调用service获取品牌交易额排行榜
        List<ProductStats> productStatsByCategory3List = productStatsService.getProductStatsByCategory3(date, limit);

        HashMap resMap = new HashMap();
        resMap.put("status", 0);
        List dataList = new ArrayList();

        for (ProductStats productStats : productStatsByCategory3List) {
            Map dataMap = new HashMap();
            dataMap.put("name", productStats.getCategory3_name());
            dataMap.put("value", productStats.getOrder_amount());
            dataList.add(dataMap);
        }

        resMap.put("data", dataList);

        return resMap;
    }

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

    /*
  -请求地址
  $API_HOST/api/sugar/trademark?limit=5

  -返回数据的格式
      {
        "status": 0,
        "data": {
          "categories": ["苹果","三星","华为"],
          "series": [
            {
              "data": [9387,8095,8863]
            }
          ]
        }
      }
   */
    //方式1：使用字符串拼接的方式处理返回的json数据
    @RequestMapping("/trademark")
    public String getProductStatsByTrademark(
            @RequestParam(value = "date", defaultValue = "0") Integer date,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        //如果没有传日期参数，那么将日期设置为当前日期
        if (date == 0) {
            date = now();
        }
        //调用service根据品牌获取交易额排名
        List<ProductStats> productStatsByTrademarkList = productStatsService.getProductStatsByTrademark(date, limit);
        //定义两个集合，分别存放品牌的名称以及品牌的交易额
        List<String> trademarkNameList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        //对获取到的品牌交易额进行遍历
        for (ProductStats productStats : productStatsByTrademarkList) {
            trademarkNameList.add(productStats.getTm_name());
            amountList.add(productStats.getOrder_amount());
        }

        return "{" +
                "\"status\": 0," +
                "\"data\": {" +
                "\"categories\": [\"" + StringUtils.join(trademarkNameList, "\",\"") + "\"]," +
                "\"series\": [" +
                "{" +
                "\"data\": [" + StringUtils.join(amountList, ",") + "]" +
                "}]}}";
    }

    /*
    //方式2:封装对象，通过将对象转换为json格式字符串的形式 返回json数据
    @RequestMapping("/trademark")
    public Map getProductStatsByTrademark(
            @RequestParam(value = "date", defaultValue = "0") Integer date,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        //如果没有传日期参数，那么将日期设置为当前日期
        if (date == 0) {
            date = now();
        }
        //调用service根据品牌获取交易额排名
        List<ProductStats> productStatsByTrademarkList = productStatsService.getProductStatsByTrademark(date, limit);
        //定义两个集合，分别存放品牌的名称以及品牌的交易额
        List<String> trademarkNameList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        //对获取到的品牌交易额进行遍历
        for (ProductStats productStats : productStatsByTrademarkList) {
            trademarkNameList.add(productStats.getTm_name());
            amountList.add(productStats.getOrder_amount());
        }

        Map resMap = new HashMap();
        resMap.put("status", 0);
        Map dataMap = new HashMap();
        dataMap.put("categories", trademarkNameList);
        List seriesList = new ArrayList();
        Map seriesDataMap = new HashMap();
        seriesDataMap.put("data", amountList);
        seriesList.add(seriesDataMap);
        dataMap.put("series", seriesList);
        resMap.put("data", dataMap);
        return resMap;
    }*/
}