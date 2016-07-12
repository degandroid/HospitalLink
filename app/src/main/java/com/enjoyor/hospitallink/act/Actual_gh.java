package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.adapter.Activty_actual_adapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/6.
 */
public class Actual_gh extends ToolBarActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.sousuo_text)
    EditText sousuo_text;
    @Bind(R.id.icon_suosuo_view)
    View icon_suosuo_view;
    @Bind(R.id.actual_gh_list)
    ListView actual_gh_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_actual_gh,false);
        ButterKnife.bind(this);
        setTitle("实时挂号");
        setRightBtn(R.mipmap.icon_distance);



        setRightTvBtn("杭州");
        /****头部右边监听事件*****/
        getRightBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(Actual_gh.this,Select_area.class);
                startActivity(intent);
            }
        });
        on_listen();
        actual_gh_list.setAdapter(new Activty_actual_adapter(Actual_gh.this));
        actual_gh_list.setOnItemClickListener(this);

    }
    /***********搜索框的监听************/
   public void on_listen(){

       sousuo_text.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               if (sousuo_text.getText()!=null){
                   icon_suosuo_view.setVisibility(View.GONE);


               }
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });



   }
    /***********列表的item监听************/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(Actual_gh.this,ActivtyDepartments.class);
        startActivity(intent);
    }
}
