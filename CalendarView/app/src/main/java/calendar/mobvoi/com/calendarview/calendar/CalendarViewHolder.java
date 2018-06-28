package calendar.mobvoi.com.calendarview.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import calendar.mobvoi.com.calendarview.calendar.CalendarTextView;

/**
 * @author cuiqiang
 * @since 2018/6/27
 */
public class CalendarViewHolder extends RecyclerView.ViewHolder {

    private CalendarTextView mCalendarTextView;

    public CalendarViewHolder(View view) {
        super(view);
        mCalendarTextView = (CalendarTextView)view;
    }

    public CalendarTextView getCalendarTextView() {
        return mCalendarTextView;
    }
}
