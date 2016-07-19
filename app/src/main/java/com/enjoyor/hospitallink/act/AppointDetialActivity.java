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
import com.enjoyor.hospitallink.utils.DateUtil;
import com.enjoyor.hospitallink.utils.StringUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/11.
 */
public class AppointDetialActivity extends ToolBarActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    @Bind(R.id.re_picktime)
    RelativeLayout re_picktime;
    @Bind(R.id.tv_time)
    TextView tv_time;

    WheelView wv_mouth;
    WheelView wv_day;

    private String time;

    private int DEFAULT_VALUE = 0;

    private static int TYPE_HOUR = 1;
    private static int TYPE_MINUTE = 2;

    private String time_hour;
    private String time_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointdetial, false);
        ButterKnife.bind(this);
        initHead();
        initView();
    }

    private void initView() {
        re_picktime.setOnClickListener(this);
        tv_time.setText(DateUtil.longToDateString(DateUtil.getCurrentTime(),"HH:mm"));
    }

    protected void initPopuptWindow(View view) {
        // TODO Auto-generated method stub

        View popupWindow_view = getLayoutInflater().inflate(R.layout.popwindow_wheelview, null, false);
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        TextView tv_cancel = (TextView) popupWindow_view.findViewById(R.id.tv_cancel);
        TextView tv_commit = (TextView) popupWindow_view.findViewById(R.id.tv_commit);
        tv_cancel.setOnClickListener(this);
        tv_commit.setOnClickListener(this);
        initWheelView(popupWindow_view);

    }

    private void initHead() {
        setTitle("预约信息");
        setRightTvBtn("提交");
        getRightTvBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointDetialActivity.this, ConfirmAppointActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initWheelView(View popupWindow_view) {

        int time_now_hour = Integer.parseInt(DateUtil.longToDateString(DateUtil.getCurrentTime(),"HH"));
        int time_now_minute = Integer.parseInt(DateUtil.longToDateString(DateUtil.getCurrentTime(),"mm"));

        wv_mouth = (WheelView) popupWindow_view.findViewById(R.id.wv_mouth);
        wv_day = (WheelView) popupWindow_view.findViewById(R.id.wv_day);

        wv_mouth.setData(getStartData(TYPE_HOUR));
        wv_mouth.setDefault(time_now_hour);

        wv_mouth.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                Log.i("time", "id:" + id + "text:" + text);
                time_hour =text;
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        wv_day.setData(getStartData(TYPE_MINUTE));
        wv_day.setDefault(time_now_minute);
        wv_day.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                Log.i("time", "id:" + id + "text:" + text);
                time_minute =text;
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        time_hour = wv_mouth.getSelectedText();
        time_minute = wv_day.getSelectedText();
    }

    private ArrayList<String> getStartData(int type) {
        ArrayList<String> _list = new ArrayList<>();
        if (type == TYPE_HOUR) {
            for (int i = 0; i < 24; i++) {
                _list.add(String.format("%02d", i) + "");
            }
        } else if (type == TYPE_MINUTE) {
            for (int i = 0; i < 60; i++) {
                _list.add(String.format("%02d", i) + "");
            }
        }
        return _list;
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        switch (key) {
            case R.id.re_picktime:
                initPopuptWindow(v);
                break;
            case R.id.tv_cancel:
                popupWindow.dismiss();
                break;
            case R.id.tv_commit:
                if (timeCorrect()) {
                    tv_time.setText(time);
                    popupWindow.dismiss();
                }
                break;
        }
    }

    private boolean timeCorrect() {
        if (!StringUtils.isBlank(time_hour) && !StringUtils.isBlank(time_minute)) {
            time = time_hour + ":" + time_minute;
            return true;
        }
        return false;
    }
}
