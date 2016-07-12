package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.adapter.Fragment_specialist_adapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/11.
 */
public class ActivtyGhDodtor extends ToolBarActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.doctor_list)
    ListView doctor_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_gh_doctorlist,false);
        ButterKnife.bind(this);
        setTitle("医生列表");
        doctor_list.setOnItemClickListener(this);
        doctor_list.setAdapter(new Fragment_specialist_adapter(ActivtyGhDodtor.this));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(ActivtyGhDodtor.this,ActivtyGhDoctorData.class);
        startActivity(intent);
    }
}
