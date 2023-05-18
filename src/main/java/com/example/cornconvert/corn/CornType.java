package com.example.cornconvert.corn;

public class CornType {

    public static String MIN_EVERY = "0 0/MIN * 1/1 * ? *";
    public static String HOUR_EVERY = "0 0 0/HOUR 1/1 * ? *";
    public static String HOUR_START = "0 MIN HOUR 1/1 * ? *";

    public static String DAY_EVERY = "0 MIN HOUR 1/1 * ? *";
    public static String DAY_START = "0 MIN HOUR ? * MON-FRI *";

    public static String WEEK_START = "0 MIN HOUR ? * DAYS *";

    public static String MONTH_EVERY = "0 MIN HOUR 1 1/MONTH ? *";

    public static String MONTH_START = "0 MIN HOUR ? 1/MONTH DAY_WEEK#WEEK *";

    public static String YEAR_EVERY = "0 MIN HOUR DAY MONTH ? *";

    public static String YEAR_START = "0 MIN HOUR ? MONTH DAY_WEEK#WEEK *";



}
