package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ResultReportActivity extends ToolBarActivity {

    @Bind(R.id.ly_addreportresult)
    LinearLayout ly_addreportresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_reault, false);

        ButterKnife.bind(this);

        initHead();
        initView();
    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            View view = LayoutInflater.from(ResultReportActivity.this).inflate(R.layout.item_report_reault, null);
            ly_addreportresult.addView(view);
        }
    }

    private void initHead() {
        setTitle("检查结果");
    }
}
