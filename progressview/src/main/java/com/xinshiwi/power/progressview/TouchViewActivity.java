package com.xinshiwi.power.progressview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinshiwi.power.progressview.view.MyTouchView;

public class TouchViewActivity extends AppCompatActivity {

    MyTouchView view_touch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        view_touch = findViewById(R.id.view_touch);
    }
}
