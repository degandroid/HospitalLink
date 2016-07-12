package com.enjoyor.hospitallink.act;

import android.os.Bundle;

import android.view.View;
import android.widget.ListView;

import com.enjoyor.hospitallink.R;

import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.adapter.Activty_select_area_adapter;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/7.
 */

public class Select_area extends ToolBarActivity {
    @Bind(R.id.icon_select_view)
    View view;
    @Bind(R.id.recently_list)
    ListView recently_list;
    @Bind(R.id.all_list)
    ListView all_list;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_area,false);
        ButterKnife.bind(this);
        setTitle("选择地区");
        recently_list.setAdapter(new Activty_select_area_adapter(Select_area.this,3));
        all_list.setAdapter(new Activty_select_area_adapter(Select_area.this,18));

    }
}
