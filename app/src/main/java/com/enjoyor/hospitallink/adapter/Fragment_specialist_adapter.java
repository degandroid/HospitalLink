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
public class Fragment_specialist_adapter extends BaseAdapter{
    private Context context;
 public    Fragment_specialist_adapter( Context context){
     this.context=context;
 }
    @Override
    public int getCount() {
        return 5;
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
        convertView= LayoutInflater.from(context).inflate(R.layout.specialist_item,null);
        return convertView;
    }
}
