package com.xinshiwi.power.progressview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.xinshiwi.power.progressview.month.DateAdapter;
import com.xinshiwi.power.progressview.month.DateMonthsUtils;

/**
 * Created by apple on 2018/1/12.
 */

public class ThirdActivity extends AppCompatActivity {

    private GridView record_gridView;//定义gridView
    private DateAdapter dateAdapter;//定义adapter
    private int year;
    private int month;
    private String title;
    private int[][] days = new int[6][7];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        //初始化日期数据
        initData();
        //初始化组件
        initView();
        //组件点击监听事件
//        initEvent();
    }

    private void initData() {
        year = DateMonthsUtils.getYear();
        month = DateMonthsUtils.getMonth();
    }

    private void initView() {
        /**
         * 以下是初始化GridView
         */
        record_gridView = (GridView) findViewById(R.id.record_gridView);
        days = DateMonthsUtils.getDayOfMonthFormat(2018, 1);
        dateAdapter = new DateAdapter(this, days, year, month);//传入当前月的年
        record_gridView.setAdapter(dateAdapter);
        record_gridView.setVerticalSpacing(60);
        record_gridView.setEnabled(false);
        /**
         * 以下是初始化其他组件
         */
//        record_left = (ImageView) findViewById(R.id.record_left);
//        record_right = (ImageView) findViewById(R.id.record_right);
//        record_title = (TextView) findViewById(R.id.record_title);
    }
}
