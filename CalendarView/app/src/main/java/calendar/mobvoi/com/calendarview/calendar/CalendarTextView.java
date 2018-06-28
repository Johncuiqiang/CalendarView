package calendar.mobvoi.com.calendarview.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/6/8.
 */

public class CalendarTextView extends android.support.v7.widget.AppCompatTextView {

    private boolean isToday = false;
    private Paint paint = new Paint();

    public CalendarTextView(Context context) {
        super(context);
    }

    public CalendarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public CalendarTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context);
    }

    private void initControl(Context context) {
        paint.setStyle(Paint.Style.FILL); //实心
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //在绘制文字之前
        if (isToday) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        }
        super.onDraw(canvas);
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }


}
