package com.xinshiwi.power.progressview.newweek;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xinshiwi.power.progressview.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by apple on 2018/1/10.
 */

public class SecondActivity extends AppCompatActivity implements WeekDayView.MonthChangeListener, WeekDayView.EventLongPressListener, WeekDayView.EventClickListener, WeekDayView.ScrollListener {

    private TextView tv_date;
    private WeekHeaderView mWeekHeaderView;
    private WeekDayView mWeekView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv_date = findViewById(R.id.tv_date);
        Calendar now = Calendar.getInstance();
        tv_date.setText(now.get(Calendar.YEAR) + "年" + (now.get(Calendar.MONTH) + 1) + "月");
        mWeekHeaderView = findViewById(R.id.weekheaderview);
        mWeekHeaderView.setDateSelectedChangeListener(new WeekHeaderView.DateSelectedChangeListener() {
            @Override
            public void onDateSelectedChange(Calendar oldSelectedDay, Calendar newSelectedDay) {
                tv_date.setText(newSelectedDay.get(Calendar.YEAR) + "年" + (newSelectedDay.get(Calendar.MONTH) + 1) + "月");
                mWeekView.goToDate(newSelectedDay);
            }
        });
        mWeekHeaderView.setScrollListener(new WeekHeaderView.ScrollListener() {
            @Override
            public void onFirstVisibleDayChanged(Calendar newFirstVisibleDay, Calendar oldFirstVisibleDay) {
                mWeekView.goToDate(mWeekHeaderView.getSelectedDay());
            }
        });

        mWeekView = (WeekDayView) findViewById(R.id.weekdayview);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setScrollListener(this);

        setupDateTimeInterpreter(false);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     *
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        final String[] weekLabels = {"日", "一", "二", "三", "四", "五", "六"};
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat("d", Locale.getDefault());
                return format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return String.format("%02d:00", hour);

            }

            @Override
            public String interpretWeek(int date) {
                if (date > 7 || date < 1) {
                    return null;
                }
                return weekLabels[date - 1];
            }
        });
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent(1, "This is a Event!!", startTime, endTime);
        event.setColor(ColorUtil.getRandomColor());
        events.add(event);

//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 10);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 4);
//        endTime.set(Calendar.MINUTE, 0);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        event = new WeekViewEvent(10, "22", startTime, endTime);
//        event.setColor(ColorUtil.getRandomColor());
//        events.add(event);

        return events;
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Log.i("djh", event.getName() + " " + eventRect.centerX() + " " + eventRect.centerY());
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Log.i("djh", event.getName());
    }

    @Override
    public void onFirstVisibleDayChanged(Calendar newFirstVisibleDay, Calendar oldFirstVisibleDay) {

    }

    @Override
    public void onSelectedDaeChange(Calendar selectedDate) {
        mWeekHeaderView.setSelectedDay(selectedDate);
        tv_date.setText(selectedDate.get(Calendar.YEAR) + "年" + (selectedDate.get(Calendar.MONTH) + 1) + "月");
    }
}
