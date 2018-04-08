package com.xinshiwi.power.progressview.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by apple on 2018/3/20.
 */

public class DingView extends View {

    Paint paint;

    public DingView(Context context) {
        this(context, null);
    }

    public DingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int realWidth = width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            realWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            realWidth = width;
        }


        int height = MeasureSpec.getSize(heightMeasureSpec);
        int realHeight = height;
        int heidhtMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heidhtMode == MeasureSpec.EXACTLY) {
            realHeight = height;
        } else if (heidhtMode == MeasureSpec.AT_MOST) {
            realHeight = 500;
        }

        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRect(rectF, paint);
    }
}
