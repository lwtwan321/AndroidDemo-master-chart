package com.xinshiwi.power.progressview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.alertview.AlertView;

/**
 * Created by apple on 2018/3/8.
 */

public class SixRootView extends AppCompatActivity {

    private ViewGroup decorView;//activity的根View

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_rootview);

//        AlertCustom alertCustom = new AlertCustom(this);
//        alertCustom.show();

        AlertView alertView = new AlertView(
                "sss",
                null,
                "cancel",
                new String[]{"sss"},
                new String[]{"sss"},
                this,
                AlertView.Style.ActionSheet,
                null
        );
        alertView.show();


//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//        decorView.setSystemUiVisibility(option);
//        getWindow().setStatusBarColor(this.getResources().getColor()R.);
//
//        ViewGroup actionBar = getActionBar(getWindow().getDecorView());
//        actionBar.setBackgroundResource(R.color.colorAccent);
//
//
//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//        decorView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
//        decorView.setBackgroundResource(R.color.colorAccent);
    }


    public ViewGroup getActionBar(View view) {
        try {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;

                if (viewGroup instanceof android.support.v7.widget.Toolbar) {
                    return viewGroup;
                }

                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    ViewGroup actionBar = getActionBar(viewGroup.getChildAt(i));

                    if (actionBar != null) {
                        return actionBar;
                    }
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

}
