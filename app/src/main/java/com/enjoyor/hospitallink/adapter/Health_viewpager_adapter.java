package com.enjoyor.hospitallink.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by wuzhenzhen on 2016/7/5.
 */
public class Health_viewpager_adapter extends PagerAdapter {
      List<ImageView>list;
     public Health_viewpager_adapter(List<ImageView> list){
     this.list=list;
 }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(list.get(position % list.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ((ViewPager)container).addView(list.get(position % list.size()), 0);

        return list.get(position % list.size());
    }
}
