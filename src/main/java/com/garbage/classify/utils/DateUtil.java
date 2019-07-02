package com.garbage.classify.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /** 日期格式yyyy-MM字符串常量 */
    public static final String MONTH_FORMAT = "yyyy-MM";

    /** 日期格式yyyy-MM-dd字符串常量 */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /** 日期格式yyyy-MM-dd字符串常量 */
    public static final String DATE_FORMAT_YYYYMMDD_HH_MM = "yyyy-MM-dd HH:mm";

    /** 日期格式yyyyMM字符串常量 */
    public static final String  YEARMONTH_FORMAT = "yyyyMM";

    /** 日期格式yyyyMMdd字符串常量 */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */
    public static final String DATE_FORMAT_YYYYMMDD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);



    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     *
     * @return
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return
     */
    public static String getAllTime() {
        return formatDate(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期类型转换成String
     * @param date 日期
     * @param format 日期格式(默认：yyyy-MM-dd)
     *
     * @return sting类型的日期
     */
    public static String dateConvertString(Date date, String format) {


        String value = "";
        if (date == null) {
            return value;
        }

        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT;
        }

        try {
            SimpleDateFormat df = getSimpleDateFormat(format);
            value = df.format(date);
        }catch (Exception e) {
            LOGGER.error("DateUtil.dateConvertString() is error, e = {}", e);
        }

        return value;
    }

    /**
     * 根据日期格式类型 创建SimpleDateFormat对象
     * @param format 日期格式类型
     *
     * @return SimpleDateFormat对象
     */
    private static SimpleDateFormat getSimpleDateFormat(String format) {
        return new SimpleDateFormat(format);
    }
    /**
     * string转换成日期类型
     * @param date string类型日期
     * @param format 日期格式
     *
     * @return date日期
     */
    public static Date stringConvertDate(String date, String format) {

        Date value = null;
        if (date == null) {
            return value;
        }

        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT;
        }

        try {
            SimpleDateFormat df = getSimpleDateFormat(format);
            value = df.parse(date);
        }catch (Exception e) {
            LOGGER.error("DateUtil.stringConvertDate() is error, e = {}", e);
        }
        return value;
    }


    /**
     * 比较date1与date2相差天数
     * @param date1 日期1
     * @param date2 日期2
     *
     * @return date1与date2相差天数
     */
    public static int getDifferDayNum(String date1, String date2) {
        int margin;
        try {
            SimpleDateFormat df = getSimpleDateFormat(DATE_FORMAT);
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = df.parse(date1, pos);
            Date dt2 = df.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            LOGGER.error("DateUtil.getDifferDayNum() is error, e = {}", e);
            return 0;
        }
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurrent() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     * 指定日期添加月
     * @param date 日期
     * @param month 添加月份
     *
     * @return 添加月份后的日期
     */
    public static Date dateAddMonth(Date date , int month) {
        try{
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, month);
            return cal.getTime();
        } catch(Exception e) {
            LOGGER.error("DateUtil.dateAddMonth() is error, e = {}", e);
            return null;
        }
    }

    public static String stringConvertDateString(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return "";
        }
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT;
        }
        Date conDate = stringConvertDate(date, format);
        return dateConvertString(conDate, format);
    }



    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

}
