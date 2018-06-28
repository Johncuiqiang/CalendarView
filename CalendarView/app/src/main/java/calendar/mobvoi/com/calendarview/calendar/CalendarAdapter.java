package calendar.mobvoi.com.calendarview.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import calendar.mobvoi.com.calendarview.R;

/**
 * @author cuiqiang
 * @since 2018/6/27
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private List<Date> mDateList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private CalendarTextView mLastView;

    public CalendarAdapter(Context context ,List<Date> dateList,OnItemClickListener onItemClickListener){
        mDateList = dateList;
        mContext = context;
        mItemClickListener = onItemClickListener;
    }

    private View.OnClickListener mOnClickLister = (view -> {
        if (mLastView != null && !mLastView.isToday()){
            mLastView.setBackgroundResource(R.drawable.shape_unselected_bg);
        }
        mLastView = (CalendarTextView)view;
        view.setBackgroundResource(R.drawable.shape_selected_bg);
        mItemClickListener.onItemClick(view);
    });

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CalendarViewHolder viewHolder = new CalendarViewHolder
                (LayoutInflater.from(mContext).inflate(R.layout.calendar_text_day, viewGroup,
                        false));
        viewHolder.getCalendarTextView().setOnClickListener(mOnClickLister);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        Date date = mDateList.get(position);

        int day = date.getDate();
        holder.getCalendarTextView().setText(String.valueOf(day));

        Date curDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //如果是同一个月，颜色加深，否则颜色变浅
        if (date.getMonth() == curDate.getMonth()) {
            holder.getCalendarTextView().setTextColor(Color.parseColor("#000000"));
        } else {
            holder.getCalendarTextView().setTextColor(Color.parseColor("#666666"));
        }
        //判断是不是今天，如果是字体颜色变白
        if (curDate.getDate() == date.getDate() && curDate.getMonth() == date.getMonth()
                && curDate.getYear() == date.getYear()) {
            holder.getCalendarTextView().setTextColor(Color.parseColor("#ffffff"));
            holder.getCalendarTextView().setToday(true);
        }
    }


    @Override
    public int getItemCount() {
        return mDateList.size();
    }

}
