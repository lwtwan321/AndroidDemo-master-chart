package com.xinshiwi.power.progressview.viewpage;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xinshiwi.power.progressview.R;


public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager mViewPager;
    private ViewGroupAdapter mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled = true;

    public ShadowTransformer(ViewPager viewPager, ViewGroupAdapter adapter) {
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
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
        Button viewBtn = currentCard.findViewById(R.id.btn_ok);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (viewBtn != null) {
            if (mScalingEnabled) {
                viewBtn.setScaleX((float) (1 + 1 * (1 - realOffset)));
                viewBtn.setScaleY((float) (1 + 1 * (1 - realOffset)));

            }
        }

        ViewGroup nextCard = mAdapter.getViewGroupAt(nextPosition);
        Button viewBtnNext = nextCard.findViewById(R.id.btn_ok);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (viewBtnNext != null) {
            if (mScalingEnabled) {
                viewBtnNext.setScaleX((float) (1 + 1 * (realOffset)));
                viewBtnNext.setScaleY((float) (1 + 1 * (realOffset)));
            }
        }

        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
