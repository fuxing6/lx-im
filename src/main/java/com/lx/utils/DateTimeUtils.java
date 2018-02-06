package com.lx.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;


/**
 * 日期时间工具类
 * 
 * @author ghost
 * @email fuzhao918@163.com
 * @time 2012-10-22 下午4:01:07
 */
public class DateTimeUtils extends org.apache.commons.lang.time.DateUtils {
  private static final Map<String, String> dtfmt = new HashMap<String, String>();

  static {
    dtfmt.put("\\d{8}", "yyyyMMdd");
    dtfmt.put("\\d{12}", "yyyyMMddHHmm");
    dtfmt.put("\\d{14}", "yyyyMMddHHmmss");
    dtfmt.put("\\d{17}", "yyyyMMddHHmmssSSS");
    dtfmt.put("\\d{4}-\\d{1,2}-\\d{1,2}", "yyyy-MM-dd");
    dtfmt.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2}", "yyyy-MM-dd HH:mm");
    dtfmt.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}\\d{2}", "yyyy-MM-dd HHmm");
    dtfmt.put("\\d{2}\\d{2}", "HHmm");
    dtfmt.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}\\d{2}\\d{2}", "yyyy-MM-dd HHmmss");
    dtfmt.put("\\d{2}:\\d{2}:\\d{2}", "HH:mm:ss");
    dtfmt.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2}:\\d{2}", "yyyy-MM-dd HH:mm:ss");
    dtfmt.put("\\d{4}-\\d{1,2}-\\d{1,2} \\d{2}:\\d{2}:\\d{2}\\..*", "yyyy-MM-dd HH:mm:ss.SSS");
    dtfmt.put("\\d{4}/\\d{1,2}/\\d{1,2}", "yyyy/MM/dd");
    dtfmt.put("\\d{4}年\\d{1,2}月\\d{1,2}日", "yyyy年MM月dd日");
    dtfmt.put("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{2}时\\d{2}分\\d{2}秒", "yyyy年MM月dd日 HH时mm分ss秒");
    dtfmt.put("\\d{2}时\\d{2}分\\d{2}秒", "HH时mm分ss秒");
  }

  private static final ThreadLocal<Map<String, DateFormat>> fmt =
      new ThreadLocal<Map<String, DateFormat>>() {
        @Override
        protected Map<String, DateFormat> initialValue() {
          Map<String, DateFormat> fmt = new HashMap<String, DateFormat>();
          fmt.put("yyyyMMdd", new SimpleDateFormat("yyyyMMdd"));
          fmt.put("yyyyMMddHHmm", new SimpleDateFormat("yyyyMMddHHmm"));
          fmt.put("yyyyMMddHHmmss", new SimpleDateFormat("yyyyMMddHHmmss"));
          fmt.put("yyyyMMddHHmmssSSS", new SimpleDateFormat("yyyyMMddHHmmssSSS"));
          fmt.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
          fmt.put("yyyy-MM-dd HH:mm", new SimpleDateFormat("yyyy-MM-dd HH:mm"));
          fmt.put("yyyy-MM-dd HHmm", new SimpleDateFormat("yyyy-MM-dd HHmm"));
          fmt.put("yyyy-MM-dd HHmmss", new SimpleDateFormat("yyyy-MM-dd HHmmss"));
          fmt.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
          fmt.put("HH:mm:ss", new SimpleDateFormat("HH:mm:ss"));
          fmt.put("HHmmss", new SimpleDateFormat("HHmmss"));
          fmt.put("HHmm", new SimpleDateFormat("HHmm"));
          fmt.put("yyyy-MM-dd HH:mm:ss.SSS", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
          fmt.put("yyyy年MM月dd日", new SimpleDateFormat("yyyy年MM月dd日"));
          fmt.put("yyyy年MM月dd日 HH时mm分ss秒", new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));
          fmt.put("HH时mm分ss秒", new SimpleDateFormat("HH时mm分ss秒"));
          fmt.put("yyyy/MM/dd", new SimpleDateFormat("yyyy/MM/dd"));
          return fmt;
        }
      };

  private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
      "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
      "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

  /**
   * 得到当前日期字符串 格式（yyyy-MM-dd）
   */
  public static String getDate() {
    return getDate("yyyy-MM-dd");
  }

  /**
   * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   */
  public static String getDate(String pattern) {
    return DateFormatUtils.format(new Date(), pattern);
  }

  /**
   * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   */
  public static String formatDate(Date date, Object... pattern) {
    String formatDate = null;
    if (pattern != null && pattern.length > 0) {
      formatDate = DateFormatUtils.format(date, pattern[0].toString());
    } else {
      formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
    }
    return formatDate;
  }

  /**
   * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
   */
  public static String formatDateTime(Date date) {
    return formatDate(date, "yyyy-MM-dd HH:mm:ss");
  }

  /**
   * 得到当前时间字符串 格式（HH:mm:ss）
   */
  public static String getTime() {
    return formatDate(new Date(), "HH:mm:ss");
  }

  /**
   * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
   */
  public static String getDateTime() {
    return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
  }

  /**
   * 得到当前年份字符串 格式（yyyy）
   */
  public static String getYear() {
    return formatDate(new Date(), "yyyy");
  }

  /**
   * 得到当前月份字符串 格式（MM）
   */
  public static String getMonth() {
    return formatDate(new Date(), "MM");
  }

  /**
   * 得到当天字符串 格式（dd）
   */
  public static String getDay() {
    return formatDate(new Date(), "dd");
  }

  /**
   * 得到当天字符串 格式（dd）
   */
  public static String getHour() {
    return formatDate(new Date(), "HH");
  }

  /**
   * 得到当前星期字符串 格式（E）星期几
   */
  public static String getWeek() {
    return formatDate(new Date(), "E");
  }

  /**
   * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
   * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
   * "yyyy.MM.dd HH:mm" }
   */
  public static Date parseDate(Object str) {
    if (str == null) {
      return null;
    }
    try {
      return parseDate(str.toString(), parsePatterns);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 获取过去的天数
   * 
   * @param date
   * @return
   */
  public static long pastDays(Date date) {
    long t = new Date().getTime() - date.getTime();
    return t / (24 * 60 * 60 * 1000);
  }

  /**
   * 获取过去的小时
   * 
   * @param date
   * @return
   */
  public static long pastHour(Date date) {
    long t = new Date().getTime() - date.getTime();
    return t / (60 * 60 * 1000);
  }

  /**
   * 获取过去的分钟
   * 
   * @param date
   * @return
   */
  public static long pastMinutes(Date date) {
    long t = new Date().getTime() - date.getTime();
    return t / (60 * 1000);
  }

  /**
   * 转换为时间（天,时:分:秒.毫秒）
   * 
   * @param timeMillis
   * @return
   */
  public static String formatDateTime(long timeMillis) {
    long day = timeMillis / (24 * 60 * 60 * 1000);
    long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
    long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
    long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
    long sss =
        (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
    return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
  }

  /**
   * 获取两个日期之间的天数
   * 
   * @param before
   * @param after
   * @return
   */
  public static double getDistanceOfTwoDate(Date before, Date after) {
    long beforeTime = before.getTime();
    long afterTime = after.getTime();
    return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
  }

  public static List<Date> getMonthBetween(Date minDate, Date maxDate) throws ParseException {
    ArrayList<Date> result = new ArrayList<Date>();
    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();

    min.setTime(minDate);
    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

    max.setTime(maxDate);
    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);// 2比1大表示包括最大的一个月

    Calendar curr = min;
    while (curr.before(max)) {
      result.add(curr.getTime());
      curr.add(Calendar.MONTH, 1);
    }
    return result;
  }

  public static List<String> getMonthStrBetween(Date minDate, Date maxDate) throws ParseException {
    ArrayList<String> result = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月

    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();

    min.setTime(minDate);
    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

    max.setTime(maxDate);
    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);// 2比1大表示包括最大的一个月

    Calendar curr = min;
    while (curr.before(max)) {
      result.add(sdf.format(curr.getTime()));
      curr.add(Calendar.MONTH, 1);
    }
    return result;
  }

  public static List<Date> getYearBetween(Date minDate, Date maxDate) throws ParseException {
    ArrayList<Date> result = new ArrayList<Date>();
    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();

    min.setTime(minDate);
    min.set(min.get(Calendar.YEAR), 1, 1);

    max.setTime(maxDate);
    max.set(max.get(Calendar.YEAR), 1, 2);// 2比1大表示包括最大的一个年

    Calendar curr = min;
    while (curr.before(max)) {
      result.add(curr.getTime());
      curr.add(Calendar.YEAR, 1);
    }
    return result;
  }

  public static List<String> getYearStrBetween(Date minDate, Date maxDate) throws ParseException {
    ArrayList<String> result = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");// 格式化为年

    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();

    min.setTime(minDate);
    min.set(min.get(Calendar.YEAR), 1, 1);

    max.setTime(maxDate);
    max.set(max.get(Calendar.YEAR), 1, 2);// 2比1大表示包括最大的一个年

    Calendar curr = min;
    while (curr.before(max)) {
      result.add(sdf.format(curr.getTime()));
      curr.add(Calendar.YEAR, 1);
    }
    return result;
  }

  /**
   * 根据日期字符串取得日期格式
   * 
   * @param dateStr
   * @return
   */
  public static String getDatePattern(String dateStr) {
    if (StringUtils.isBlank(dateStr)) {
      return null;
    }
    String pattern = null;
    for (String key : dtfmt.keySet()) {
      if (dateStr.matches(key)) {
        return dtfmt.get(key);
      }
    }
    return pattern;
  }

  /**
   * 根据日期字符串生成java.util.Date
   * 
   * @param dateStr
   * @return
   */
  public static java.util.Date toDate(String dateStr) {
    if (StringUtils.isBlank(dateStr)) {
      return null;
    }
    String pattern = getDatePattern(dateStr);
    if (StringUtils.isBlank(pattern)) {
      throw new IllegalArgumentException("无法识别日期字符串格式[" + dateStr + "]");
    }
    return toDate(dateStr, pattern);
  }

  /**
   * 根据日期字符串及格式生成java.util.Date
   * 
   * @param dateStr
   * @param pattern
   * @return
   */
  public static java.util.Date toDate(String dateStr, String pattern) {
    if (StringUtils.isBlank(dateStr)) {
      return null;
    }
    try {
      return DateUtils.parseDate(dateStr, new String[] {pattern});
    } catch (ParseException e) {
      throw new IllegalArgumentException(String.format("转换日期字符串不正确,字符串:%s,格式:%s", dateStr, pattern));
    }
  }

  /**
   * 根据日期字符串生成java.sql.Date
   * 
   * @param dateStr
   * @return
   */
  public static java.sql.Date toSQLDate(String dateStr) {
    return new java.sql.Date(toDate(dateStr).getTime());
  }

  /**
   * 根据日期字符串及格式生成java.sql.Date
   * 
   * @param dateStr
   * @param pattern
   * @return
   */
  public static java.sql.Date toSQLDate(String dateStr, String pattern) {
    return new java.sql.Date(toDate(dateStr, pattern).getTime());
  }

  /**
   * 根据日期字符串生成java.util.Calendar
   * 
   * @author ghost
   * @time 2012-11-28 上午11:05:05
   * @param src
   * @return
   */
  public static Calendar toCalendar(final String src) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(toDate(src));
    return calendar;
  }

  /**
   * 格式化日期为指定格式字符串
   * 
   * @author ghost
   * @time 2012-10-29 上午11:02:14
   * @param date
   * @param pattern
   * @return
   */
  public static String format(final java.util.Date date, final String pattern) {
    if (StringUtils.isBlank(pattern) || !fmt.get().containsKey(pattern)) {
      throw new IllegalArgumentException("不支持格式化Pattern");
    }
    if (date == null) {
      return null;
    }
    return fmt.get().get(pattern).format(date);
  }

  /**
   * 转换非标准格式日期时间为标准格式字符串
   * 
   * @author ghost
   * @time 2013-5-6 下午2:20:17
   * @param date
   * @param time
   * @return
   */
  public static String getNormalDateTime(String date, String time) {
    return StringUtils.leftPad(date.replaceAll("-|/|年|月|日", ""), 8, "20")
        + StringUtils.rightPad(StringUtils.leftPad(time.replaceAll(":|时|分|秒", ""), 4, "0"), 6, "0");
  }

  /**
   * 转换非标准格式日期时间为Date
   * 
   * @author ghost
   * @time 2013-9-21 上午11:43:55
   * @param date
   * @param time
   * @return
   */
  public static Date getDate(String date, String time) {
    return toDate(getNormalDateTime(date, time));
  }

  /**
   * 转换非标准格式日期时间为毫秒数
   * 
   * @author ghost
   * @time 2013-9-21 上午11:43:55
   * @param date
   * @param time
   * @return
   */
  public static Long getMilliSecond(String date, String time) {
    return toDate(getNormalDateTime(date, time)).getTime();
  }

  /**
   * 转换非标准格式日期时间为秒数
   * 
   * @author ghost
   * @time 2013-9-21 上午11:43:55
   * @param date
   * @param time
   * @return
   */
  public static Long getSecond(String date, String time) {
    return getMilliSecond(date, time).longValue() / 1000;
  }

  /**
   * 转换标准格式日期时间为秒数
   * 
   * @author ghost
   * @time 2013-9-21 上午11:43:55
   * @param datetime
   * @return
   */
  public static Long getSecond(String datetime) {
    if (StringUtils.isBlank(datetime)) {
      return null;
    }
    return toDate(datetime).getTime() / 1000;
  }

  /**
   * 转换标准格式日期时间为秒数
   * 
   * @author ghost
   * @time 2013-9-21 上午11:43:55
   * @param datetime
   * @return
   */
  public static Long getSecond(Date date) {
    if (date == null) {
      return null;
    }
    return Long.valueOf(date.getTime() / 1000);
  }

  /**
   * 转换标准格式日期时间为秒数
   * 
   * @author ghost
   * @time 2013-11-14 下午1:24:37
   * @param date
   * @param time
   * @return
   */
  public static long getNvlSecond(String date, String time) {
    Long second = getSecond(date, time);
    if (second == null) {
      return 0;
    } else {
      return second;
    }
  }

  /**
   * 转换标准格式日期时间为秒数
   * 
   * @author ghost
   * @time 2013-11-14 下午1:24:33
   * @param datetime
   * @return
   */
  public static long getNvlSecond(String datetime) {
    Long second = getSecond(datetime);
    if (second == null) {
      return 0;
    } else {
      return second;
    }
  }

  /**
   * 转换标准格式日期时间为秒数,空返回0
   * 
   * @author ghost
   * @time 2013-11-14 下午1:23:24
   * @param date
   * @return
   */
  public static long getNvlSecond(Date date) {
    Long second = getSecond(date);
    if (second == null) {
      return 0;
    } else {
      return second;
    }
  }

  /**
   * 转换秒为 x天x小时x分x秒
   * 
   * @author ghost
   * @time 2013-5-23 下午3:18:03
   * @param seconds
   * @return
   */
  public static String getDayHourMinSec(long seconds) {
    long day = seconds / (24 * 60 * 60);
    long hour = (seconds - day * 24 * 60 * 60) / (60 * 60);
    long min = (seconds - day * 24 * 60 * 60 - hour * 60 * 60) / 60;
    long sec = seconds - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
    StringBuffer result = new StringBuffer();
    if (day > 0) {
      result.append(day + "天");
    }
    if (hour > 0) {
      result.append(hour + "小时");
    }
    if (min > 0) {
      result.append(min + "分");
    }
    if (sec > 0) {
      result.append(sec + "秒");
    }
    if (result.length() == 0) {
      result.append("0秒");
    }
    return result.toString();
  }

  public static Date getFirstDayOfWeek(Date date) {
    Calendar cal = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
    cal.setTime(date);
    int d = 0;
    if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
      d = -6;
    } else {
      d = 2 - cal.get(Calendar.DAY_OF_WEEK);
    }
    cal.add(Calendar.DAY_OF_WEEK, d);
    return cal.getTime();
  }

  public static Date getLastDayOfWeek(Date date) {
    Calendar cal = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
    cal.setTime(date);
    int d = 0;
    if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
      d = -6;
    } else {
      d = 2 - cal.get(Calendar.DAY_OF_WEEK);
    }
    cal.add(Calendar.DAY_OF_WEEK, d);
    cal.add(Calendar.DAY_OF_WEEK, 6);
    return cal.getTime();
  }

  public static Date set(Date date, int hour, int minute, int second) {
    Calendar cal = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, hour);
    cal.set(Calendar.MINUTE, minute);
    cal.set(Calendar.SECOND, second);
    return cal.getTime();
  }
}
