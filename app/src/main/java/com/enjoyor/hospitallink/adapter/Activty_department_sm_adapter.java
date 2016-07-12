package com.enjoyor.hospitallink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.ActivtyDepartments;

/**
 * Created by wuzhenzhen on 2016/7/7.
 */
public class Activty_department_sm_adapter extends BaseAdapter{
    Context context;
  public   Activty_department_sm_adapter(Context context){

      this.context=context;
  }
    @Override
    public int getCount() {
        return ActivtyDepartments.depatments_tag+1;
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
        convertView= LayoutInflater.from(context).inflate(R.layout.activty_department_big_itme,null);
        convertView.setBackgroundColor(convertView.getResources().getColor(R.color.hl_color_white));
        return convertView;
    }
}
