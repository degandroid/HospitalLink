package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.view.View;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ConfirmExaminationActivity extends ToolBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmexamination,false);

        ButterKnife.bind(this);
        initHead();
    }

    private void initHead() {
        setTitle("体检信息确认");
        setRightTvBtn("提交");
        getRightTvBtn().setTextColor(getResources().getColor(R.color.hl_color_blue));
        getRightTvBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("提交成功");
            }
        });
    }
}
