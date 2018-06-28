package calendar.mobvoi.com.calendarview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import calendar.mobvoi.com.calendarview.calendar.CalendarAdapter;
import calendar.mobvoi.com.calendarview.calendar.OnItemClickListener;

/**
 * @author cuiqiang
 * @since 2018/6/27
 */
public class MobvoiCalendar extends LinearLayout {

    private TextView mTvDate;
    private RecyclerView mCalendarRecyView;
    private Calendar mCurDate = Calendar.getInstance();
    private OnItemClickListener mItemClickListener =  new OnItemClickListener(){

        @Override
        public void onItemClick(View view) {
            int position = mCalendarRecyView.getChildAdapterPosition(view);
        }
    };

    public MobvoiCalendar(Context context) {
        super(context);
        init(context);
    }

    public MobvoiCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MobvoiCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        initView(context);
        renderCalendar();
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.calendar_view, this, true);
        mTvDate = view.findViewById(R.id.tv_date);
        mCalendarRecyView = view.findViewById(R.id.calendar_recycle_view);
    }

    /**
     * 渲染日期内容
     */
    public void renderCalendar() {
        //设置头部的标题日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM yyyy");
        mTvDate.setText(simpleDateFormat.format(mCurDate.getTime()));

        ArrayList<Date> dateList = new ArrayList<>();
        Calendar calendar = (Calendar) mCurDate.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        //得到本月1号前边空余的日期
        //得到当前星期几，依次向前减，直到结果为0，这时得到calendar是从上月开始的
        int preDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DAY_OF_MONTH, -preDays);
        int maxCount = 6 * 7;
        //calendar是从上月开始的，只要不断加1就可以
        while (dateList.size() < maxCount) {
            dateList.add(calendar.getTime());
            //当前日期每次加1，到31加1时会从1开始
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        mCalendarRecyView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        CalendarAdapter calendarAdapter = new CalendarAdapter(getContext(), dateList, mItemClickListener);
        mCalendarRecyView.setAdapter(calendarAdapter);
    }

    public void setCurDate(Calendar curDate) {
        this.mCurDate = curDate;
    }

    public Calendar getCurDate() {
        return mCurDate;
    }

}
