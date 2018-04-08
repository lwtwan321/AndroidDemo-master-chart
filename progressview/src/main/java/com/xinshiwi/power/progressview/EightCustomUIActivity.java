package com.xinshiwi.power.progressview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinshiwi.power.progressview.custom.PieView;

/**
 * Created by apple on 2018/3/20.
 */

public class EightCustomUIActivity extends AppCompatActivity {

    PieView rootview;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);
        rootview = findViewById(R.id.aaa);
        rootview.startAnimator(0, 360, 60000);
    }
}
