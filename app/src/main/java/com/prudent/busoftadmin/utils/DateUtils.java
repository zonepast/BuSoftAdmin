package com.prudent.busoftadmin.utils;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dharmik Patel on 14-Jul-17.
 */

public class DateUtils {

    private Context mContext;

    public DateUtils(Context context){
        this.mContext = context;
    }

    public String GetCurrentDate(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(c.getTime());
    }

    public long getDaysBetweenDates(String start, String end, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        Date startDate, endDate;
        long numberOfDays = 0;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numberOfDays;
    }

    private static long getUnitBetweenDates(Date startDate, Date endDate, TimeUnit unit) {
        long timeDiff = endDate.getTime() - startDate.getTime();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }
}
