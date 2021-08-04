package com.example.nbshoping.fpage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class FPTypeVPAdapter extends PagerAdapter {
    List<GridView> viewList;

    public FPTypeVPAdapter(List<GridView> views) {
        this.viewList = views;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject( View view,  Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GridView view=viewList.get(position);
        container.addView(view);
        return view;



    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        GridView view=viewList.get(position);
        container.removeView(view);

    }
}
