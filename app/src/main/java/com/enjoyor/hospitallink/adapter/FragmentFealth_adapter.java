package com.enjoyor.hospitallink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;

import java.util.List;

/**
 * Created by wuzhenzhen on 2016/7/5.
 */
public class FragmentFealth_adapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<String>list;
    private  ViewHolder viewHolder=null;
    public  FragmentFealth_adapter(Context context){
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
    convertView=LayoutInflater.from(context).inflate(R.layout.fragment_health_item,null);
    viewHolder=new ViewHolder();
    viewHolder.fragment_icon= (ImageView) convertView.findViewById(R.id.health_item);
    viewHolder.fragment_title= (TextView) convertView.findViewById(R.id.fl_itme_text);
    viewHolder.fragment_text= (TextView) convertView.findViewById(R.id.fl_itme_texts);
    convertView.setTag(viewHolder);
}else {
    viewHolder= (ViewHolder) convertView.getTag();

}

        return convertView;
    }

}
class ViewHolder{

    ImageView fragment_icon;
    TextView  fragment_title,fragment_text;

}