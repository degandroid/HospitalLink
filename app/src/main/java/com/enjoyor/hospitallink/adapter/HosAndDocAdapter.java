package com.enjoyor.hospitallink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/7.
 */
public class HosAndDocAdapter {


    public static class HospitalAdapter extends BaseAdapter{
        Context context;
        public HospitalAdapter(Context context){
            this.context = context;
        }
        @Override
        public int getCount() {
            return 2;
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
            ViewHolder holder;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_search_hospital,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_lev.setText("666");
            return convertView;
        }

        class ViewHolder{
            @Bind(R.id.tv_lev)
            TextView tv_lev;

            ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }





    public static class DoctorAdapter extends BaseAdapter{

        Context context;
        public DoctorAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return 3;
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
            ViewHolder holder;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_search_doctor,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_lev.setText("999");
            return convertView;
        }
        class ViewHolder{

            @Bind(R.id.tv_lev)TextView tv_lev;
            ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }
}
