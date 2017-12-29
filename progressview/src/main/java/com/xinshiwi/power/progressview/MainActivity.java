package com.xinshiwi.power.progressview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.week.WeekCalendarView;

public class MainActivity extends AppCompatActivity implements OnCalendarClickListener {

    private ChartHourView horitantalView;
    private TextView tv_year;
    private TextView tv_month;
    private TextView tv_day;
    private WeekCalendarView wcvCalendar;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horitantalView = (ChartHourView) findViewById(R.id.sss);
        wcvCalendar = findViewById(R.id.wcvCalendar);
        tv_year = findViewById(R.id.tv_year);
        tv_month = findViewById(R.id.tv_month);
        tv_day = findViewById(R.id.tv_day);
        wcvCalendar.setOnCalendarClickListener(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                horitantalView.addSelectRange(1, 5);
            }
        }, 2000);

    }

    @Override
    public void onClickDate(int year, int month, int day) {
        tv_year.setText(String.valueOf(year + "年"));
        tv_month.setText(String.valueOf(month + 1 + "月"));
        tv_day.setText(String.valueOf(day + "日"));
    }

    @Override
    public void onPageChange(int year, int month, int day) {
        Log.i("djh", year + " " + month + " " + day);
    }

}
