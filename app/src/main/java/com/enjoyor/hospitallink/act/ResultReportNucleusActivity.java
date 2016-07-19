package com.enjoyor.hospitallink.act;

import android.os.Bundle;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ResultReportNucleusActivity extends ToolBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportnucleus_reault, false);

        ButterKnife.bind(this);

        initHead();
        initView();
    }

    private void initView() {
    }

    private void initHead() {
        setTitle("检查结果");
    }
}
