package com.xinshiwi.power.progressview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinshiwi.power.progressview.newweek.DingWeekDay;
import com.xinshiwi.power.progressview.newweek.WeekHeaderView;

import java.util.Calendar;

/**
 * Created by apple on 2018/1/17.
 */

public class FourActivity extends AppCompatActivity {

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
    }
}
