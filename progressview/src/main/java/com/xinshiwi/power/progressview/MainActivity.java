package com.xinshiwi.power.progressview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ChartHourView horitantalView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horitantalView = (ChartHourView) findViewById(R.id.sss);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                horitantalView.addSelectRange(1,5);
            }
        },2000);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
