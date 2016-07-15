package com.enjoyor.hospitallink.act;

import android.os.Bundle;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ConfirmAppointActivity extends ToolBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmappoint,false);

        ButterKnife.bind(this);
        initHead();
    }

    private void initHead() {
        setTitle("预约成功");
    }
}
