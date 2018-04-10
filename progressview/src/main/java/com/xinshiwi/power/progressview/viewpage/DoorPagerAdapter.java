package com.xinshiwi.power.progressview.viewpage;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinshiwi.power.progressview.R;

import java.util.ArrayList;
import java.util.List;

public class DoorPagerAdapter extends PagerAdapter implements ViewGroupAdapter, View.OnClickListener {

    private List<ViewGroup> mViews;
    private List<DoorItem> mData;
    private OnClickLockListener onClickLockListener;

    public void setOnClickLockListener(OnClickLockListener onClickLockListener) {
        this.onClickLockListener = onClickLockListener;
    }

    public DoorPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(DoorItem item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public ViewGroup getViewGroupAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_door, container, false);
        container.addView(view);
        mData.get(position).setPosition(position);
        bind(mData.get(position), view);
        LinearLayout cardView = view.findViewById(R.id.rootv);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
        mData.get(position).setPosition(-1);
    }

    private void bind(DoorItem item, View view) {
        TextView tv_door = view.findViewById(R.id.tv_door);
        TextView tv_buildingname = view.findViewById(R.id.tv_buildingname);

        tv_door.setText(String.valueOf(item.getName()));
        tv_buildingname.setText("(" + String.valueOf(item.getBuildName()) + ")");
        ImageView btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        btn_ok.setTag(item);
    }

    @Override
    public void onClick(View view) {
        DoorItem item = (DoorItem) view.getTag();
        if (onClickLockListener != null) {
            onClickLockListener.onClickLock(item, view);
        }
    }

    public interface OnClickLockListener {
        void onClickLock(DoorItem item, View view);
    }

}
