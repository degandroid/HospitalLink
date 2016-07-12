package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.adapter.Activty_Gh_data_adapter;
import com.enjoyor.hospitallink.custom.Mylistview;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/11.
 */
public class ActivtyGhDoctorData extends ToolBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    @Bind(R.id.time_data)
    Mylistview time_data;
    @Bind(R.id.forenoon)
    TextView forenoon;
    @Bind(R.id.afternoon)
    TextView afternoon;
    public  static  int update;
    private  Activty_Gh_data_adapter activty_gh_data_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtyg_gh_data,false);
        ButterKnife.bind(this);
        activty_gh_data_adapter=new Activty_Gh_data_adapter(ActivtyGhDoctorData.this);
        time_data.setAdapter(activty_gh_data_adapter);
        time_data.setOnItemClickListener(this);
        afternoon.setOnClickListener(this);
        forenoon.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         update=position;
        activty_gh_data_adapter.notifyDataSetInvalidated();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forenoon:
                forenoon.setTextColor(forenoon.getResources().getColor(R.color.hl_color_blue));
                afternoon.setTextColor(afternoon.getResources().getColor(R.color.hl_textcolor_light));
                break;
            case  R.id.afternoon:
                forenoon.setTextColor(forenoon.getResources().getColor(R.color.hl_textcolor_light));
                afternoon.setTextColor(afternoon.getResources().getColor(R.color.hl_color_blue));
                break;


        }
    }
}
