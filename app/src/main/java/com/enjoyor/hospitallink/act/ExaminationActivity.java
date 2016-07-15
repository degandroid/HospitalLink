package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.custom.PullToRefreshLayout;
import com.enjoyor.hospitallink.custom.PullableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ExaminationActivity extends ToolBarActivity {

    @Bind(R.id.et_search_examination)EditText et_search_examination;

    @Bind(R.id.refresh_view)
    PullToRefreshLayout refresh_view;
    @Bind(R.id.content_view)
    PullableListView content_view;

    private List<String> examination_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination, false);
        ButterKnife.bind(this);

        initHead();
        initListView();
    }

    private void initHead() {
        setTitle("体检套餐");
    }

    private void initListView() {
        for (int i = 0; i < 10; i++) {
            examination_list.add(i + "条info。。。");
        }
        content_view.canPullDown();
        refresh_view.setLoading("查看更多");
        refresh_view.setLoadingPic(R.mipmap.hl_icon_load);
        refresh_view.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                // 加载操作
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        // 千万别忘了告诉控件加载完毕了哦！
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 5000);
            }

        });
        content_view.setAdapter(new ExaminationAdapater());
        content_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExaminationActivity.this, ExaminationDetialActivity.class);
                intent.putExtra("hospitalName", examination_list.get(position));
                startActivity(intent);
            }
        });
    }

    class ExaminationAdapater extends BaseAdapter {

        @Override
        public int getCount() {
            return examination_list.size();
        }

        @Override
        public Object getItem(int position) {
            return examination_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(ExaminationActivity.this).inflate(R.layout.item_examination, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_examinationname.setText(examination_list.get(position));

            return convertView;
        }

        class ViewHolder {

            @Bind(R.id.tv_examinationname)
            TextView tv_examinationname;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
