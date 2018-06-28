package calendar.mobvoi.com.calendarview.calendar;

import java.util.Calendar;

/**
 * @author cuiqiang
 * @since 2018/6/27
 */
public class CalendarConstant {

    /**
     *  起始位置
     */
    public static final int START_DATE = Calendar.getInstance().getTime().getYear() * 12 + Calendar.getInstance().getTime().getMonth();
}
