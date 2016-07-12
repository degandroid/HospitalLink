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
 * Created by Administrator on 2016/7/6.
 */
public class MessageDetialActivity extends ToolBarActivity {
    @Bind(R.id.root_mainactivity)
    LinearLayout root_mainactivity;

    @Bind(R.id.ll_content)
    LinearLayout ll_content;

    public static final int FROM_INTERROGATION = 1;
    public static final int FROM_LINEHOSPITAL = 2;
    public static final int FROM_APPOINT = 3;
    public static final int FROM_REGISTER = 4;

    private int fromWhere;
//    MessageDetialActivity(int fromWhere){
//this.fromWhere = fromWhere;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_message_detial, false);
        setImmerseLayout(root_mainactivity);
        ButterKnife.bind(this);
        if (getIntent().hasExtra("fromWhere")) {
            fromWhere = getIntent().getIntExtra("fromWhere", FROM_INTERROGATION);
            initHeadTittle();
        }

    }

    private void initHeadTittle() {

        if (fromWhere == FROM_INTERROGATION) {
            setTitle("问诊消息");
            for (int i = 0; i < 3; i++) {
                View child_view = LayoutInflater.from(MessageDetialActivity.this).inflate(R.layout.item_message_detial, null);
                ll_content.addView(child_view);
            }
        } else if (fromWhere == FROM_LINEHOSPITAL) {
            setTitle("链医");
            for (int i = 0; i < 1; i++) {
                View child_view = LayoutInflater.from(MessageDetialActivity.this).inflate(R.layout.item_message_detial, null);
                ll_content.addView(child_view);
            }
        } else if (fromWhere == FROM_APPOINT) {
            setTitle("预约消息");
            for (int i = 0; i < 2; i++) {
                View child_view = LayoutInflater.from(MessageDetialActivity.this).inflate(R.layout.item_message_detial, null);
                ll_content.addView(child_view);
            }
        } else if (fromWhere == FROM_REGISTER) {
            setTitle("挂号消息");
            for (int i = 0; i < 5; i++) {
                View child_view = LayoutInflater.from(MessageDetialActivity.this).inflate(R.layout.item_message_detial, null);
                ll_content.addView(child_view);
            }
        }

    }

}
