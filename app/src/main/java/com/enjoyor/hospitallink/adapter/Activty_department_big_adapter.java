package com.enjoyor.hospitallink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.ActivtyDepartments;

/**
 * Created by wuzhenzhen on 2016/7/6.
 */
public class Activty_department_big_adapter extends BaseAdapter {
    private Context context;
    private TextView big_depaer_item;

    public Activty_department_big_adapter(Context context) {
        this.context = context;

    }


    @Override
    public int getCount() {
        return 6;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.activty_department_big_itme, null);
        if (ActivtyDepartments.depatments_tag == position) {
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.hl_color_white));
            big_depaer_item = (TextView) convertView.findViewById(R.id.big_depaer_item);
            big_depaer_item.setTextColor(big_depaer_item.getResources().getColor((R.color.hl_color_blue)));
        }

        return convertView;
    }
}