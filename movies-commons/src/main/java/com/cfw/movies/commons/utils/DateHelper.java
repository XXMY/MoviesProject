package com.cfw.movies.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Duskrain on 2017/5/8.
 */
public class DateHelper {

    public static String dateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

    public static long toEndOfToday(){
        Date currentDate = new Date();

        return (endTimeofDate(currentDate).getTime() - currentDate.getTime());
    }

    /**
     * 获取某日的开始时间
     * @author CaiFangwei
     * @time since 2017-1-4 14:03:36
     * @param date
     * @return
     */
    public static Date firstTimeofDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);

        return calendar.getTime();
    }

    /**
     * 获取某日的技术时间
     * @author CaiFangwie
     * @time since 2017-1-4 14:05:51
     * @param date
     * @return
     */
    public static Date endTimeofDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstTimeofDate(date));
        calendar.add(Calendar.DAY_OF_YEAR,1);

        return calendar.getTime();
    }
}
