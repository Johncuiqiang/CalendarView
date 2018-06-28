package calendar.mobvoi.com.calendarview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

import calendar.mobvoi.com.calendarview.viewpager.CalendarViewPager;
import calendar.mobvoi.com.calendarview.viewpager.ViewPagerAdapter;

import static calendar.mobvoi.com.calendarview.calendar.CalendarConstant.START_DATE;

public class MainActivity extends AppCompatActivity {

    private CalendarViewPager mViewPager;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
       mViewPager = findViewById(R.id.view_pager);
    }

    private void initData() {
        mPagerAdapter = new ViewPagerAdapter(MainActivity.this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(START_DATE);
    }

}
