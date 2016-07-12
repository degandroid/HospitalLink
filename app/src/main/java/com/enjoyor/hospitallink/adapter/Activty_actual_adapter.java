package com.enjoyor.hospitallink.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;

/**
 * Created by wuzhenzhen on 2016/7/6.
 */
public class Activty_actual_adapter extends BaseAdapter{
    private Context context;
    private  ViewHolder_actual viewHolder_actual;
   public Activty_actual_adapter(Context context){
       this.context=context;

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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.actual_item,null);
            viewHolder_actual=new ViewHolder_actual();
            viewHolder_actual.actual_icon= (ImageView) convertView.findViewById(R.id.actual_gh_icon);
            viewHolder_actual.actualt_text= (TextView) convertView.findViewById(R.id.actual_grade);
            viewHolder_actual.actual_title= (TextView) convertView.findViewById(R.id.hospital_name);
            viewHolder_actual.actualt_docctor= (TextView) convertView.findViewById(R.id.doctor_mun);
            convertView.setTag(viewHolder_actual);
        }else {

            convertView.getTag();
        }


        return convertView;
    }

}
class ViewHolder_actual{

    ImageView actual_icon;
    TextView actual_title,actualt_text,actualt_docctor;

}