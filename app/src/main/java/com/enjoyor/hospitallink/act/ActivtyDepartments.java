package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.adapter.Activty_department_big_adapter;
import com.enjoyor.hospitallink.adapter.Activty_department_sm_adapter;
import com.enjoyor.hospitallink.common.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/6.
 */
public class ActivtyDepartments extends ToolBarActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.big_departments_list)
    ListView big_departments_list;
    @Bind(R.id.sm_departments_list)
    ListView sm_departments_list;
    public  static  int depatments_tag=1000;//设置listview更新view的变量
    private  Activty_department_big_adapter activty_department_big_adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_departments,false);
        ButterKnife.bind(this);
        setTitle("开花人民医院");
        activty_department_big_adapter=new  Activty_department_big_adapter(ActivtyDepartments.this);
        big_departments_list.setAdapter(activty_department_big_adapter);
        big_departments_list.setOnItemClickListener(this);
        sm_departments_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId()==R.id.big_departments_list) {
            //大科室
            depatments_tag=position;
            activty_department_big_adapter.notifyDataSetChanged();
            sm_departments_list.setAdapter(new Activty_department_sm_adapter(ActivtyDepartments.this));

        }else if (parent.getId()==R.id.sm_departments_list){
            //小科室点击跳转
         judge(Constant.register_appointment);
            Log.e("wokao","wokao");
        }
    }
/*******挂号和预约的判断1（挂号）2（预约）进行跳转********/
    public  void judge(int register_appointment){
        if (register_appointment==1){
       Intent intent2=new Intent(ActivtyDepartments.this,ActivtyGhDodtor.class);
            startActivity(intent2);
        } else {
            Intent intent=new Intent(ActivtyDepartments.this,ActivtyAppointment.class);
            startActivity(intent);
        }



    }

}
