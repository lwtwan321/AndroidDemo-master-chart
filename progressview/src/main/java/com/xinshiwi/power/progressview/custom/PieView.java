package com.xinshiwi.power.progressview.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xinshiwi.power.progressview.R;

/**
 * Created by apple on 2018/3/27.
 */

public class PieView extends View {

    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private float circleText = 13;
    private int height;
    private int width;
    float mParent;

    private boolean startAnimation = true;
    private boolean noLayoutRefresh = true;


    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieView, defStyleAttr, 0);
            for (int i = 0; i < typedArray.length(); i++) {
                int index = typedArray.getIndex(i);
                switch (index) {
                    case R.styleable.PieView_pieTextSize:
                        circleText = typedArray.getDimensionPixelSize(index, 13);
                        break;
                }
            }
            typedArray.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);


        if (noLayoutRefresh) {
            noLayoutRefresh = false;
            int modeH = MeasureSpec.getMode(heightMeasureSpec);
            int sizeH = MeasureSpec.getSize(heightMeasureSpec);

            if (modeW == MeasureSpec.EXACTLY) {
                width = sizeW;
            } else {
                width = getMeasuredWidth() + getPaddingLeft() + getPaddingRight();
                if (modeW == MeasureSpec.AT_MOST) {
                    width = Math.min(width, sizeW);
                }
            }

            if (modeH == MeasureSpec.EXACTLY) {
                height = sizeH;
            } else {
                height = getMeasuredHeight() + getPaddingTop() + getPaddingBottom();
                if (modeH == MeasureSpec.AT_MOST) {
                    height = Math.min(height, sizeH);
                }
            }

            setMeasuredDimension(width, height);
            init();
        }
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.YELLOW);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeWidth(20);
        paint2.setStyle(Paint.Style.STROKE);

        paint3 = new Paint();
        paint3.setColor(Color.GRAY);
        paint3.setTextSize(circleText);
        centerX = width / 2;
        centterY = height / 2;

    }

    int centerX = 0;
    int centterY = 0;
    int r = 0;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        r = width / 2 - 40;
        if (height < width) {
            r = height / 2 - 40;
        }
        canvas.drawCircle(centerX, centterY, r, paint);

        RectF rectF = new RectF();
        rectF.set(centerX - r, centterY - r, centerX + r, centterY + r);
        canvas.drawArc(rectF, 90, mParent, false, paint2);

        canvas.restore();
    }

    public void startAnimator(float start, float end, long animTime) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator = ValueAnimator.ofFloat(start, end);
        valueAnimator.setDuration(animTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mParent = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
