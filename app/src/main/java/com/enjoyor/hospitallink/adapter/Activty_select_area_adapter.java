package com.enjoyor.hospitallink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.enjoyor.hospitallink.R;

/**
 * Created by wuzhenzhen on 2016/7/7.
 */
public class Activty_select_area_adapter extends BaseAdapter{
    private Context context;
    private int tag;
   public Activty_select_area_adapter(Context context,int tag){
       this.context=context;
       this.tag=tag;

   }
    @Override
    public int getCount() {
        return tag;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.select_area_item,null);
        if (position==tag){


        }
        return convertView;
    }
}
