package com.xinshiwi.power.progressview.content;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xinshiwi.power.progressview.R;

/**
 * Created by apple on 2018/3/8.
 */

public class AlertCustom {

    private Context context;
    private ViewGroup decorView;//activity的根View
    private ViewGroup rootView;

    public AlertCustom(Context context) {
        this.context = context;
        initViews();
    }

    private void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        decorView = (ViewGroup) ((Activity)context).getWindow().getDecorView();//.findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview, decorView, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }

    public void show(){
        decorView.addView(rootView);
    }


}
