package com.zhongruan.android.zkfingerdemo.utils;

import android.annotation.SuppressLint;


import com.zhongruan.android.zkfingerdemo.BuildConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.zhongruan.android.zkfingerdemo.utils.Utils.stringIsEmpty;


@SuppressLint({"SimpleDateFormat"})
public class DateUtil {
    private static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String birthFormat(String date) {
        if (date == null) {
            return null;
        }
        String year = BuildConfig.VERSION_NAME;
        String mon = BuildConfig.VERSION_NAME;
        String d = BuildConfig.VERSION_NAME;
        if (date.length() > 4) {
            year = date.substring(0, 4);
        }
        if (date.length() > 7) {
            mon = date.substring(4, 6);
        }
        return year + "-" + mon + "-" + date.substring(6, date.length());
    }

    public static String getStringFromDate(Date date, String template) {
        return new SimpleDateFormat(template).format(date);
    }

    public static String dateFormat(Date date) {
        return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS).format(date);
    }

    public static String dateFormat(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getDateByFormat(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String getNowTime() {
        return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS).format(new Date());
    }

    public static String getNowTimeChinese() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date());
    }

    public static String getChineseTime(Date date) {
        if (date == null) {
            return BuildConfig.VERSION_NAME;
        }
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(date);
    }

    public static String getChineseTimeByFormat(Date date) {
        if (date == null) {
            return BuildConfig.VERSION_NAME;
        }
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(date);
    }

    public static String getHHmmTime(Date date) {
        if (date == null) {
            return BuildConfig.VERSION_NAME;
        }
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static String getNowTimeNoDate() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static String getNowTime2() {
        return new SimpleDateFormat("yyyyMMdHHmmss").format(new Date());
    }

    public static String getNowTime3() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static String getNowTime_Millisecond() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date());
    }

    public static String getNowTime_Millisecond3() {
        return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS).format(new Date());
    }

    public static String getNowTime_minute() {
        return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM).format(new Date());
    }

    public static String getNowTime_Millisecond2() {
        return new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()).substring(9, 17);
    }

    public static String getNowTime_Millisecond4() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    public static String getNowTime_Millisecond4(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static Date getyyyyMMddhhmmssSSSFromTime(String time) {
        try {
            return new SimpleDateFormat("yyyyMMddhhmmssSSS").parse(time);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date getyyyyMMddHHmmssSSSFromTime(String time) {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(time);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getFormateDateInMillisecond(String Date_Millisecond_str) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        String result = BuildConfig.VERSION_NAME;
        if (Date_Millisecond_str == null) {
            return result;
        }
        try {
            return dateFormat(format.parse(Date_Millisecond_str));
        } catch (Exception e) {
            result = Date_Millisecond_str;
            e.printStackTrace();
            return result;
        }
    }

    public static Date getDateFromString(String string, String template) {
        Date date = null;
        try {
            date = new SimpleDateFormat(template).parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long dateToLong(String date) {
        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        if (date != null) {
            try {
                return dateformat.parse(date).getTime();
            } catch (Exception e) {
                e.printStackTrace();
                dateformat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM);
                if (date != null) {
                    try {
                        return dateformat.parse(date).getTime();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return 0;
    }

    public static boolean isTime(long now, long kssj, long jssj) {
        if (now <= kssj || now >= jssj) {
            return false;
        }
        return true;
    }

    public static boolean isValidDate(String str, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Date getStringToDate(String date) {
        if (stringIsEmpty(date)) {
            return null;
        }
        Date date2 = null;
        try {
            return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return date2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return date2;
        }
    }

    public static long getTimeDifference(String date) {
        return (new Date().getTime() - getStringToDate(date).getTime()) / 1000;
    }

    public static String getStrFromServer(String time) {
        SimpleDateFormat formatter = null;
        int tempPos = time.indexOf("-");
        if (time.length() < 12 && time.length() >= 10) {
            formatter = new SimpleDateFormat("yyyyMMddHHmm");
        } else if (tempPos < 0 && time.indexOf(" ") < 0) {
            formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        } else if (time.indexOf("-") > -1 && time.indexOf(" ") > -1) {
            formatter = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        }
        Date ctime = null;
        try {
            ctime = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS).format(ctime);
    }

    public static String getStrFromDB(String time) {
        SimpleDateFormat formatter = null;
        int tempPos = time.indexOf("-");
        if (time.length() < 12 && time.length() >= 10) {
            formatter = new SimpleDateFormat("yyyyMMddHHmm");
        } else if (tempPos < 0 && time.indexOf(" ") < 0) {
            formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        } else if (time.indexOf("-") > -1 && time.indexOf(" ") > -1) {
            formatter = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        }
        Date ctime = null;
        try {
            ctime = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyyMMddHHmmss").format(ctime);
    }
}
