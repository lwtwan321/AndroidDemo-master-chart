package com.xinshiwi.power.progressview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MyTouchView extends View {

    GestureDetector.OnGestureListener onGestureListener;
    GestureDetector gestureDetector;
    private Context mContext;

    public MyTouchView(Context context) {
        this(context, null);

    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        onGestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                Log.i("djh", "xs:" + motionEvent.getX() + " xe:" + motionEvent1.getX() + " dx:" + v + " dy:" + v1);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return true;
            }
        };
        gestureDetector = new GestureDetector(mContext, onGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
