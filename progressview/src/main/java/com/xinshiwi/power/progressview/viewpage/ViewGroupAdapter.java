package com.xinshiwi.power.progressview.viewpage;


import android.view.ViewGroup;

public interface ViewGroupAdapter {

    ViewGroup getViewGroupAt(int position);

    int getCount();
}
