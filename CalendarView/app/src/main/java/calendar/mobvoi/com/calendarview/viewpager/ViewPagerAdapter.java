package calendar.mobvoi.com.calendarview.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.HashMap;

import calendar.mobvoi.com.calendarview.MobvoiCalendar;

import static calendar.mobvoi.com.calendarview.calendar.CalendarConstant.START_DATE;

/**
 * @author cuiqiang
 *
 * @since 2018/6/27
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private Calendar mCurDate = Calendar.getInstance();
    //缓存控件的map
    private HashMap<Integer, MobvoiCalendar> mCacheHashMap = new HashMap<>();
    private int mLastPosition = START_DATE;//上次位置就是开始位置

    public ViewPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 初时创建3个实例，每次多于3个就释放
     *
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        MobvoiCalendar mobvoiCalendar = new MobvoiCalendar(container.getContext());
        renderCalendar(mobvoiCalendar,position);
        container.addView(mobvoiCalendar);
        //使用hashmap和position来标记释放
        mCacheHashMap.put(position, mobvoiCalendar);
        return mobvoiCalendar;
    }

    /**
     * 渲染数据
     *
     * @param position 当前索引
     */
    private void renderCalendar(MobvoiCalendar mobvoiCalendar, int position) {
        mCurDate.add(Calendar.MONTH, position - mLastPosition);
        mobvoiCalendar.setCurDate(mCurDate);
        mobvoiCalendar.renderCalendar();
        mLastPosition = position;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        //从尾开始添加
        mCacheHashMap.remove(position);
    }


}
