package com.xinshiwi.power.progressview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by apple on 2018/3/20.
 */

public class DingViewGroup extends ViewGroup {

    private Context mContext;

    public DingViewGroup(Context context) {
        this(context, null);
    }

    public DingViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DingViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int pt = getPaddingTop();
        int pb = getPaddingBottom();
        int pl = getPaddingLeft();
        int pr = getPaddingRight();

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;
        int maxWidth = 0;

        if (widthMode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() == GONE) {
                    continue;
                }
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                int childWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                maxWidth = maxWidth > childWidth ? maxWidth : childWidth;
            }
            width = maxWidth + pl + pr;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() == GONE) {
                    continue;
                }
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                height += childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            }
            height += pt + pb;
        }

        setMeasuredDimension(
                widthMode == MeasureSpec.AT_MOST ? width : widthSize,
                heightMode == MeasureSpec.AT_MOST ? height : heightSize
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //viewGroup的padding值影响孩子的摆放
        int pt = getPaddingTop();
        int pb = getPaddingBottom();
        int pl = getPaddingLeft();
        int pr = getPaddingRight();

        int cl = 0;
        int ct = 0;
        int cr = 0;
        int cb = 0;
        int bm = 0;     //这个bm很神奇

        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != GONE) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();

                cl += marginLayoutParams.leftMargin;
                ct += marginLayoutParams.topMargin;
                cr += childAt.getMeasuredWidth() + marginLayoutParams.leftMargin;
                cb += childAt.getMeasuredHeight() + marginLayoutParams.topMargin;

                int li = cl + pl;
                int ti = ct + pt + bm;
                int ri = cr + pr;
                int bi = cb + pb + bm;

                childAt.layout(li, ti, ri, bi);
                ct += childAt.getMeasuredHeight();
                bm += marginLayoutParams.bottomMargin;
            }

            int ac = childAt.getMeasuredWidth();
            int bc = childAt.getWidth();

            int ass = 0;
            ass = 0;
        }


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(mContext, attrs);
    }


}
