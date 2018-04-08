package com.xinshiwi.power.progressview.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by apple on 2018/3/21.
 */

public class DingTextView extends android.support.v7.widget.AppCompatTextView {
    public DingTextView(Context context) {
        super(context);
    }

    public DingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int w = getMeasuredWidth();
        int rw = getWidth();
        int a = 0;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        RectF rectF = new RectF(0, 0, getWidth()-40, getHeight());
//        Paint paint = new Paint();
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(rectF, paint);
    }
}
