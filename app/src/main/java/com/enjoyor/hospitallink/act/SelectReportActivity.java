package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.custom.WheelView;
import com.enjoyor.hospitallink.utils.StringUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SelectReportActivity extends ToolBarActivity implements View.OnClickListener {
    @Bind(R.id.re_reportstyle)
    RelativeLayout re_reportstyle;
    @Bind(R.id.re_name)
    RelativeLayout re_name;

    @Bind(R.id.tv_report)
    TextView tv_report;
    @Bind(R.id.tv_name)
    TextView tv_name;

    private PopupWindow popupWindow;
    private WheelView wheelview;

    private String selectString;

    private int int_report = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectreport, false);

        ButterKnife.bind(this);
        initHead();
        initView();

    }

    private void initView() {
        re_reportstyle.setOnClickListener(this);
        re_name.setOnClickListener(this);
    }

    private void initHead() {
        setTitle("取报告单");
        setRightTvBtn("确定");
        getRightTvBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_report > 0) {
                    if (10 == int_report) {
                        Intent intent = new Intent(SelectReportActivity.this, ResultReportActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SelectReportActivity.this, ResultReportNucleusActivity.class);
                        startActivity(intent);
                    }
                } else {
                    showSnackBar("请选择报告单类别");
                }
            }
        });
    }

    protected void initPopuptWindow(View view) {
        // TODO Auto-generated method stub

        View popupWindow_view = getLayoutInflater().inflate(R.layout.popwindow_wheelview_onlyone, null, false);
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        TextView tv_cancel = (TextView) popupWindow_view.findViewById(R.id.tv_cancel);
        TextView tv_commit = (TextView) popupWindow_view.findViewById(R.id.tv_commit);
        tv_cancel.setOnClickListener(this);
        tv_commit.setOnClickListener(this);
        initWheelView(popupWindow_view);
    }

    private void initWheelView(View popupWindow_view) {
        wheelview = (WheelView) popupWindow_view.findViewById(R.id.wheelview);
        wheelview.setData(getStartData());
        wheelview.setDefault(0);

        wheelview.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                Log.i("time", "id:" + id + "text:" + text);
                selectString = text;
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        selectString = wheelview.getSelectedText();
    }

    private ArrayList<String> getStartData() {
        ArrayList<String> _list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            _list.add(i + "啦啦啦");
        }
        return _list;
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        switch (key) {
            case R.id.re_reportstyle:
                Intent intent = new Intent(SelectReportActivity.this, ReportPickActivity.class);
                startActivityForResult(intent, ReportPickActivity.VALUE_SEL_REPORT);
                break;
            case R.id.re_name:
                initPopuptWindow(v);
                break;
            case R.id.tv_cancel:
                popupWindow.dismiss();
                break;
            case R.id.tv_commit:
                if (timeCorrect()) {
                    tv_name.setText(selectString);
                    popupWindow.dismiss();
                }
                break;
        }
    }

    private boolean timeCorrect() {
        if (!StringUtils.isBlank(selectString)) {
            return true;
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if (requestCode == ReportPickActivity.VALUE_SEL_REPORT) {
                if (data.hasExtra("str_report")) {
                    tv_report.setText(data.getStringExtra("str_report"));
                }
                if (data.hasExtra("int_report")) {
                    int_report = data.getIntExtra("int_report", 0);
                    Log.i("report",int_report+"");
                }

            }
        }
    }
}
