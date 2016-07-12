package com.enjoyor.hospitallink.act;


import android.os.Bundle;


import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.ButterKnife;


/**
 * Created by wuzhenzhen on 2016/7/8.
 */
public class ActivtyDoctorMain extends ToolBarActivity{
//    @Bind(R.id.data_text)
//    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_doctor_main,false);
        ButterKnife.bind(this);
        setTitle("医生主页");
    }
}
