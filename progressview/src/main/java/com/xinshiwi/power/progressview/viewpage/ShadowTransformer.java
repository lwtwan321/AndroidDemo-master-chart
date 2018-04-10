package com.xinshiwi.power.progressview.viewpage;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinshiwi.power.progressview.R;


public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager mViewPager;
    private ViewGroupAdapter mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled = true;
    private final static float scale = 0.9f;
    private final static float aphla = 0.7f;
    private LinearLayout view_guide_ll_point;
    private ImageView iv_point;
    private ImageView[] ivPointArray;

    public ShadowTransformer(ViewPager viewPager, ViewGroupAdapter adapter) {
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
        initPoint();
    }

    public void initPoint() {
        ViewGroup viewGroup = (ViewGroup) mViewPager.getParent();
        view_guide_ll_point = viewGroup.findViewById(R.id.view_guide_ll_point);
        ivPointArray = new ImageView[mAdapter.getCount()];
        int size = mAdapter.getCount();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(viewGroup.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            int margin = 15;
            layoutParams.setMargins(margin, 0, margin, 0);
            iv_point.setLayoutParams(layoutParams);
            ivPointArray[i] = iv_point;
            view_guide_ll_point.addView(iv_point);
        }
        showPointPosition(0);
    }

    private void showPointPosition(int position) {
        int length = mAdapter.getCount();
        for (int i = 0; i < length; i++) {
            ivPointArray[i].setBackgroundResource(R.drawable.ic_welcome_unselected_page);
            if (i == position) {
                ivPointArray[i].setBackgroundResource(R.drawable.ic_welcome_selected_page);
            }
        }
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i("djh", position + "");
        int realCurrentPosition;
        int nextPosition;
        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        ViewGroup currentCard = mAdapter.getViewGroupAt(realCurrentPosition);
        View viewBtn = currentCard.findViewById(R.id.view_scan);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (viewBtn != null) {
            if (mScalingEnabled) {
                viewBtn.setScaleX((float) (1 + scale * (1 - realOffset)));
                viewBtn.setScaleY((float) (1 + scale * (1 - realOffset)));
                viewBtn.setAlpha(aphla + (1 - realOffset));
//                if ((1 - realOffset)<aphla){
//                    viewBtn.setAlpha(aphla);
//                }else {
//                    viewBtn.setAlpha((1 - realOffset));
//                }

            }
        }

        ViewGroup nextCard = mAdapter.getViewGroupAt(nextPosition);
        View viewBtnNext = nextCard.findViewById(R.id.view_scan);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (viewBtnNext != null) {
            if (mScalingEnabled) {
                viewBtnNext.setScaleX((float) (1 + scale * (realOffset)));
                viewBtnNext.setScaleY((float) (1 + scale * (realOffset)));
                if (realOffset < aphla) {
                    viewBtnNext.setAlpha(aphla);
                } else {
                    viewBtnNext.setAlpha(realOffset);
                }


            }
        }

        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        showPointPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
