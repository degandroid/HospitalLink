package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MessageActivity extends ToolBarActivity {
    @Bind(R.id.root_mainactivity)LinearLayout root_mainactivity;

    @Bind(R.id.re_interrogation)RelativeLayout re_interrogation;
    @Bind(R.id.re_hosptialline)RelativeLayout re_hosptialline;
    @Bind(R.id.re_appoint)RelativeLayout re_appoint;
    @Bind(R.id.re_register)RelativeLayout re_register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message,false);
        ButterKnife.bind(this);
        setImmerseLayout(root_mainactivity);
        initHeadTittle();
    }

    private void initHeadTittle() {
        setTitle("消息");
    }

    @OnClick(R.id.re_interrogation)
    public void interrogation() {
        startActivity(new Intent(MessageActivity.this,MessageDetialActivity.class).putExtra("fromWhere",MessageDetialActivity.FROM_INTERROGATION));
    }

    @OnClick(R.id.re_hosptialline)
    public void hosptialline() {
        startActivity(new Intent(MessageActivity.this,MessageDetialActivity.class).putExtra("fromWhere",MessageDetialActivity.FROM_LINEHOSPITAL));
    }

    @OnClick(R.id.re_appoint)
    public void appoint() {
        startActivity(new Intent(MessageActivity.this,MessageDetialActivity.class).putExtra("fromWhere",MessageDetialActivity.FROM_APPOINT));
    }

    @OnClick(R.id.re_register)
    public void register() {
        startActivity(new Intent(MessageActivity.this,MessageDetialActivity.class).putExtra("fromWhere",MessageDetialActivity.FROM_REGISTER));
    }
}
