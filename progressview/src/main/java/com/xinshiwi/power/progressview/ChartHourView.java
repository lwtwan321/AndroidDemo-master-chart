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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/12/22.
 */

public class ChartHourView extends View {
    public ChartHourView(Context context) {
        this(context, null);
    }

    public ChartHourView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartHourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Paint mPaint;
    int countStartIndex = 0;
    int countEndIndex = 24;
    int barHeight = 200;
    private int mTitleTextSize = 50;
    private Rect mBound = new Rect();
    private float offsetY = 0;
    private Rect[] rangeRect;
    private int lineXoffset = 0;
    private int strokeWidth = 1;
    private List<Rect> selectRange = new ArrayList<>();
    private List<Integer> selectIndex = new ArrayList<>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        int j = 0;
        rangeRect = new Rect[countEndIndex * 2 + 2];//一个里面有两个时间
        for (int i = countStartIndex; i <= countEndIndex; i++) {
            String fromat = "%02d:00";
            String str = String.format(fromat, i);
            mPaint.setTextSize(mTitleTextSize);
            mPaint.setColor(Color.BLACK);
            mPaint.getTextBounds(str, 0, str.length(), mBound);
            mPaint.setColor(Color.WHITE);
            Rect rect = new Rect(0, (int) (barHeight * (i - countStartIndex) + offsetY), getWidth(), (int) (barHeight * (i - countStartIndex + 1) + offsetY));
            canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, mPaint);

            mPaint.setColor(Color.BLACK);
            int rh = rect.height();
            mPaint.setAntiAlias(true);
            canvas.drawText(str, 0, rect.bottom - rh / 2 + mBound.height() / 2, mPaint);

            mPaint.setColor(Color.LTGRAY);
            rangeRect[j] = new Rect();
            rangeRect[j].set(rect.left, rect.top, rect.right, rect.bottom - rect.height() / 2);
            drawInBarLine(canvas, rangeRect[j], j);
            j++;
            rangeRect[j] = new Rect();
            rangeRect[j].set(rect.left, rect.top + rect.height() / 2, rect.right, rect.bottom);
            drawInBarLine(canvas, rangeRect[j], j);
            j++;

            mPaint.setColor(Color.RED);
            int lineY = rect.bottom - rh / 2;
            lineXoffset = mBound.width() + 30;
            mPaint.setStrokeWidth(strokeWidth);
            canvas.drawLine(lineXoffset, lineY, getWidth(), lineY, mPaint);
        }
    }

    private void drawInBarLine(Canvas canvas, Rect rect, int index) {
        for (Integer i : selectIndex) {
            if (i == index) {
                canvas.drawRect(lineXoffset + rect.left, rect.top, rect.right, rect.bottom, mPaint);
                break;
            }
        }
    }

    public void addSelectRange(int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            selectIndex.add(i);
        }

        postInvalidate();
    }

    private float preY = 0;
    private float downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
                preY = y;
                downY = y;
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
                float maxOffsetY = -(barHeight * (countEndIndex + 1) - viewHeight);
                if (offsetY <= maxOffsetY) {
                    offsetY = maxOffsetY;
                    postInvalidate();
                    break;
                }
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP://松开
                if (Math.abs(downY - y) <= 5) {
                    Log.i("djh", x + " " + y);
                }
                break;
        }

        return true;
    }
}
