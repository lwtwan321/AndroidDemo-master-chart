package com.xinshiwi.power.progressview;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xinshiwi.power.progressview.newweek.DingWeekDay;
import com.xinshiwi.power.progressview.newweek.WeekHeaderView;
import com.xinshiwi.power.progressview.newweek.WeekViewEvent;

import java.util.Calendar;

/**
 * Created by apple on 2018/1/17.
 */

public class FourActivity extends AppCompatActivity implements DingWeekDay.EventClickListener, DingWeekDay.EventLongPressListener {

    private WeekHeaderView weekHeaderView;
    private DingWeekDay dingweekday;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_month);
        dingweekday = findViewById(R.id.dingweekday);
        weekHeaderView = findViewById(R.id.weekheaderview);
        weekHeaderView.setDateSelectedChangeListener(new WeekHeaderView.DateSelectedChangeListener() {
            @Override
            public void onDateSelectedChange(Calendar oldSelectedDay, Calendar newSelectedDay) {
//                mWeekView.goToDate(newSelectedDay);
            }
        });

        dingweekday.setEventClickListener(this);
        dingweekday.setEventLongPressListener(this);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Log.i("djh", "short click");
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Log.i("djh", "long click");
    }
}
