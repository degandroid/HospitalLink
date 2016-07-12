package com.enjoyor.hospitallink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.ActivtyGhDoctorData;

/**
 * Created by wuzhenzhen on 2016/7/11.
 */
public class Activty_Gh_data_adapter extends BaseAdapter{
    private Context context;
    private  GhViewHolder ghViewHolder;
   public Activty_Gh_data_adapter(Context context){
       this.context=context;

   }
    @Override
    public int getCount() {
        return 12;
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

            convertView= LayoutInflater.from(context).inflate(R.layout.ghdoctor_time_itme,null);
            ghViewHolder=new GhViewHolder();
            ghViewHolder.no_num= (TextView) convertView.findViewById(R.id.no_num);
            ghViewHolder.time_itme= (TextView) convertView.findViewById(R.id.time_itme);
            ghViewHolder.select_icon= (ImageView) convertView.findViewById(R.id.select_icon);
            convertView.setTag(ghViewHolder);

if (ActivtyGhDoctorData.update==position){
    ghViewHolder.no_num.setTextColor(ghViewHolder.no_num.getResources().getColor(R.color.hl_color_blue));
    ghViewHolder.time_itme.setTextColor(ghViewHolder.time_itme.getResources().getColor(R.color.hl_color_blue));
    ghViewHolder.select_icon.setImageResource(R.mipmap.confirm_select);

}else {
    ghViewHolder.no_num.setTextColor(ghViewHolder.no_num.getResources().getColor(R.color.hl_textcolor_light));
    ghViewHolder.time_itme.setTextColor(ghViewHolder.time_itme.getResources().getColor(R.color.hl_textcolor_light));
    ghViewHolder.select_icon.setImageResource(R.mipmap.con_normal);

}
        return convertView;
    }

}
class  GhViewHolder{
public TextView no_num,time_itme;
    public ImageView   select_icon;


}