package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.view.View;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

/**
 * Created by Administrator on 2016/7/11.
 */
public class AppointDetialActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointdetial, false);

        initHead();

    }

    private void initHead() {
        setTitle("预约信息");
        setRightTvBtn("提交");
        getRightTvBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("提交成功");
            }
        });
    }
}
