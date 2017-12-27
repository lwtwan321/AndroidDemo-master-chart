package com.xinshiwi.power.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by apple on 2017/12/21.
 */

public class HoritantalView extends View {

    public static final String TAG = "djh";

    private Rect mBound;
    private Paint mPaint;
    private int mTitleTextSize = 50;
    private String mTitleText = "似懂非懂是";

    private float offsetY = 0;

    public HoritantalView(Context context) {
        this(context, null);
    }

    public HoritantalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HoritantalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private float totalHeight = 0;
    int countStartIndex = 0;
    int countEndIndex = 24;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        for (int i = countStartIndex; i <= countEndIndex; i++) {
            String fromat = "%02d:00";
            String str = String.format(fromat, i);
            mPaint.setTextSize(mTitleTextSize);
            mPaint.setColor(Color.parseColor("#000000"));
            mBound = new Rect();
            mPaint.getTextBounds(str, 0, str.length(), mBound);
            int height = mBound.height();

            float y = (height * (i - countStartIndex + 1)) + 100 * ((i - countStartIndex + 1)) + offsetY;
            if (totalHeight == 0) {
                totalHeight = y;
            }
            canvas.drawText(str, 0, y, mPaint);

            int width = mBound.width();
            int viewWidth = getWidth();
            float lineStartX = width + 30;
            canvas.drawLine(lineStartX, y - height / 2, viewWidth, y - height / 2, mPaint);

        }
    }

    private float preY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取手指在屏幕上的坐标
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
                preY = y;
                Log.i(TAG, "ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE://移动
                offsetY -= preY - y;
                preY = y;
                if (offsetY >= 0) {
                    offsetY = 0;
                    postInvalidate();
                    break;
                }

                int viewHeight = getHeight();
                float maxOffsetY = -(totalHeight * (countEndIndex + 1) - viewHeight);
                if (offsetY <= maxOffsetY) {
                    offsetY = maxOffsetY;
                }

                Log.i(TAG, "ACTION_MOVE " + maxOffsetY + " " + offsetY);

                postInvalidate();
//                Log.i(TAG, "ACTION_MOVE " + y + " offsetY " + offsetY);
                break;
            case MotionEvent.ACTION_UP://松开
                Log.i(TAG, "ACTION_UP");
                break;
        }

//        return super.onTouchEvent(event);
        return true;
    }

    public void post() {

    }
}
