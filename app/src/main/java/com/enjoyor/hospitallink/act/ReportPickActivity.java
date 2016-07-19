package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ReportPickActivity extends ToolBarActivity {

    @Bind(R.id.lv_reportpick)ListView lv_reportpick;
    private List<String> list = new ArrayList<>();

    public static final int VALUE_SEL_REPORT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportpick,false);
        ButterKnife.bind(this);
        initHead();
        initListView();
    }

    private void initListView() {
        list.add("肝功能化验单");
        list.add("核磁共振化验单");
        lv_reportpick.setAdapter(new ReportPickAdapter());
        lv_reportpick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("int_report",position+10);
                intent.putExtra("str_report",list.get(position));
                setResult(VALUE_SEL_REPORT, intent);
                finish();
            }
        });

    }

    private void initHead() {
        setTitle("取报告单");
    }

    class ReportPickAdapter extends BaseAdapter {

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
                convertView = LayoutInflater.from(ReportPickActivity.this).inflate(R.layout.item_repickpick,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_reportname.setText(list.get(position));
            return convertView;

        }

        class ViewHolder{

            @Bind(R.id.tv_reportname)TextView tv_reportname;
            ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }
}
