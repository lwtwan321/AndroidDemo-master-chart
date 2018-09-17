package com.xinshiwi.power.progressview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class MyTouchView extends View {

    private GestureDetector.OnGestureListener onGestureListener;
    private GestureDetector gestureDetector;
    private Context mContext;
    private Scroller scroller;
    private PointF mCurrentOrigin = new PointF(0f, 0f);
    private Paint paint;
    private RectF rectF = new RectF();


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
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));

        onGestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                scroller.forceFinished(true);
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
                if (motionEvent1.getX() - motionEvent.getX() > 0) {
                    scroller.startScroll((int) mCurrentOrigin.x, 0, 500, 0, 2000);
                } else {
                    scroller.startScroll((int) mCurrentOrigin.x, 0, -500, 0, 2000);
                }
                Log.i("djh", "final x:" + scroller.getFinalX() + "");
//                scroller.fling((int) mCurrentOrigin.x, 0, (int) (v * 1), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
                ViewCompat.postInvalidateOnAnimation(MyTouchView.this);
                return true;
            }
        };
        gestureDetector = new GestureDetector(mContext, onGestureListener);
        scroller = new Scroller(mContext);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.left = mCurrentOrigin.x;
        rectF.right = mCurrentOrigin.x + 200;
        rectF.top = mCurrentOrigin.y;
        rectF.bottom = mCurrentOrigin.y + 200;
        canvas.drawRect(rectF, paint);

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            mCurrentOrigin.x = scroller.getCurrX();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
