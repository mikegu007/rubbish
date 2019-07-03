package com.garbage.classify.constant;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Author: shenjian
 * @Desription:
 * @Date: Created in 上午9:08 2017/12/1
 * @Modified By:
 */
public class Constant {
    /** 默认日志打印Logger,配置见logback logger节点 */
    public static final String LOGGER = "garbage";

    public static final String SUCCESS = "success";

    /** -------------------------------路由类型---------------------------------*/
    public static final String LAY_TABLE_SUCCESS = "1";
    public static final String LAY_TABLE_FAIL = "0";

    public static final Boolean IS_DEL = true;
    public static final Boolean NOT_DEL = false;

    /** -------------------------------日期格式化---------------------------------*/
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final BigDecimal SMALL_ORDER_PRICE = new BigDecimal(3);
    public static final BigDecimal MIDDLE_ORDER_PRICE = new BigDecimal(5);
    public static final BigDecimal BIG_ORDER_PRICE = new BigDecimal(7);


}
