package com.xinshiwi.power.progressview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.xinshiwi.power.progressview.viewpage.DoorItem;
import com.xinshiwi.power.progressview.viewpage.DoorPagerAdapter;
import com.xinshiwi.power.progressview.viewpage.ShadowTransformer;

public class CircleCycleActivity extends AppCompatActivity {

    private Button mButton;
    private ViewPager mViewPager;

    private DoorPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circlecycle);
        mViewPager = findViewById(R.id.viewPager);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        int divideW = width / 3;
        mViewPager.setPadding(divideW, 0, divideW, 0);


        mCardAdapter = new DoorPagerAdapter();
        mCardAdapter.addCardItem(new DoorItem("大门", "济南社区"));
        mCardAdapter.addCardItem(new DoorItem("大门", "张建社区"));
        mCardAdapter.addCardItem(new DoorItem("大门", "川沙建成"));
        mCardAdapter.addCardItem(new DoorItem("大门", "杨浦中心"));

        mCardAdapter.setOnClickLockListener(new DoorPagerAdapter.OnClickLockListener() {
            @Override
            public void onClickLock(DoorItem item, View view) {
                int current = mViewPager.getCurrentItem();
                if (current == item.getPosition()) {
                    ImageView imageView = (ImageView) view;
                    imageView.setImageResource(R.mipmap.open);
                }

            }
        });

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setCurrentItem(2);
    }
}
