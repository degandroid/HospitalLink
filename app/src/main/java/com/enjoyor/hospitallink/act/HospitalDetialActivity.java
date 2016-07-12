package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/11.
 */
public class HospitalDetialActivity extends ToolBarActivity {

    @Bind(R.id.iv_attention)ImageView iv_attention;
    @Bind(R.id.re_attertion)RelativeLayout re_attertion;

    private boolean attertint_tag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitaldetial,false);

        if (getIntent().hasExtra("hospitalName")){
            initHead(getIntent().getStringExtra("hospitalName"));
        }

        ButterKnife.bind(this);
    }

    @OnClick(R.id.re_attertion)
    public void attention(){
        initAttention(attertint_tag);
    }

    private void initAttention(boolean tag) {
        if(!tag){
            iv_attention.setImageResource(R.mipmap.hl_icon_attention_sel);
        }else{
            iv_attention.setImageResource(R.mipmap.hl_icon_attention);
        }
        attertint_tag = !tag;
    }

    private void initHead(String hospitalName) {
        setTitle(hospitalName);
    }
}
