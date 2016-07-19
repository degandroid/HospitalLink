package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19.
 */
public class GetReportActivity extends ToolBarActivity {

    @Bind(R.id.lv_getreport)ListView lv_getreport;

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getreport,false);
        ButterKnife.bind(this);

        initHead();
        initListView();

    }

    private void initHead() {
        setTitle("取报告单");
    }

    private void initListView() {
        for(int i=0;i<5;i++){
            list.add(i+"");
        }

        lv_getreport.setAdapter(new ReportAdapter());
    }

    class ReportAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
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
                convertView = LayoutInflater.from(GetReportActivity.this).inflate(R.layout.item_getreport,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;

        }

        class ViewHolder{

            ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }
}
