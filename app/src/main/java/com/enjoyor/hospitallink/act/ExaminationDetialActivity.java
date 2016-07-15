package com.enjoyor.hospitallink.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ExaminationDetialActivity extends ToolBarActivity {

    @Bind(R.id.ly_addcontent)LinearLayout ly_addcontent;
    @Bind(R.id.ly_tobuy)LinearLayout ly_tobuy;

    @Bind(R.id.tv_price)TextView tv_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinationdetial,false);

        ButterKnife.bind(this);
        initHead();

        initContent();
    }

    private void initContent() {

        for(int i=0;i<5;i++){
            View view = LayoutInflater.from(ExaminationDetialActivity.this).inflate(R.layout.item_addcontent_examination,null);
            ly_addcontent.addView(view);
        }
    }

    private void initHead() {
        setTitle("套餐介绍");
    }

    @OnClick(R.id.ly_tobuy)
    public void toBuy(){
        Intent intent = new Intent(ExaminationDetialActivity.this,PayExaminationOrderActivity.class);
        startActivity(intent);
    }
}
