package com.enjoyor.hospitallink.act.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ToolBarActivity extends BaseActivity{

    private ImageButton ib_right,ib_left;
    private TextView tv_center_title,tv_right_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int view,boolean needLoad)
    {
        View contentview = LayoutInflater.from(this).inflate(view, null);
        initView(contentview,this,needLoad,true);

        getLeftBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public TextView getTitleView()
    {

        if(null == tv_center_title)
            tv_center_title = (TextView)findViewById(R.id.tv_center_title);
        return tv_center_title;
    }


    public ImageButton getRightBtn()
    {

        if(null == ib_right)
            ib_right = (ImageButton)findViewById(R.id.ib_right);
        return ib_right;
    }

    public ImageButton getLeftBtn()
    {

        if(null == ib_left)
            ib_left = (ImageButton)findViewById(R.id.ib_left);
        return ib_left;
    }

    public TextView getRightTvBtn()
    {

        if(null == tv_right_btn)
            tv_right_btn = (TextView)findViewById(R.id.tv_right_btn);
        return tv_right_btn;
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title)
    {
        getTitleView().setText(title);
    }

    /**
     * 设置右文本按钮
     * @param text
     */
    public void setRightTvBtn(String text)
    {
        getRightTvBtn().setText(text);
        getRightTvBtn().setVisibility(View.VISIBLE);
    }

    /**
     *
     * 设置右按钮
     * @param rsid
     */
    public void setRightBtn(int rsid)
    {
        getRightBtn().setImageResource(rsid);
        getRightBtn().setVisibility(View.VISIBLE);
    }

    /**
     *
     * 设置左按钮
     * @param rsid
     */
    public void setLeftBtn(int rsid)
    {
        getLeftBtn().setImageResource(rsid);
        getLeftBtn().setVisibility(View.VISIBLE);
    }
}

