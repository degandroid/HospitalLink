package com.enjoyor.hospitallink.act;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enjoyor.hospitallink.HLApplication;
import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.model.MineModel;
import com.enjoyor.hospitallink.model.User;
import com.enjoyor.hospitallink.protocol.Server;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends ToolBarActivity {

    private Context mContext;

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.et_name)
    EditText et_name;

    @Bind(R.id.bt_sql_write)
    Button bt_sql_write;

    @Bind(R.id.bt_sql_read)
    Button bt_sql_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, true);
        mContext = this;

        ButterKnife.bind(this);

        initHead();

        initClick();
    }

    private void initClick() {
        bt_sql_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_sqlWrite();
            }
        });
        bt_sql_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_sqlRead();
            }
        });
    }

    public void click_sqlWrite() {
        String name = et_name.getText().toString().trim();
        Log.i("zxw", name);
        User user = new User();
        user.setName(name);
        user.setId(1);
        HLApplication.getInstance().getDBHelper().saveUser(user);
    }

    public void click_sqlRead() {
        if (HLApplication.getInstance().getDBHelper().getUser() != null) {
            tv.setText(HLApplication.getInstance().getDBHelper().getUser().getName());
        }

    }

    private void initHead() {
        setTitle("MainActivity");
    }

    @OnClick(R.id.bt_net)
    public void click_Net() {
        sendData();
    }

    @OnClick(R.id.bt_showSnackBar)
    public void click_ShowSnackBar() {
        showSnackBar("我是新一代的土司");
    }

    @OnClick(R.id.bt_customdialog)
    public void click_CustomDialog() {
        dialog(mContext, "自定义对话框", "这个对话框不能修改两个按钮的文字描述", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("我点击了左边的按钮");
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("我点击了右边的按钮");
            }
        });
    }

    @OnClick(R.id.bt_customdialog_str)
    public void click_CustomDialog2() {
        dialog(mContext, "自定义对话框", "修改了两个按钮的文字描述", "取消", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("我点击了左边的按钮");
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("我点击了右边的按钮");
            }
        });
    }

    @OnClick(R.id.bt_progress)
    public void click_Progress() {
        progress(mContext, "点击取消");
    }


    public void sendData() {
        Subscription s;
        s = Server.mineBuild(MainActivity.this).getMine()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            dealWithResponse(data);
                        },
                        e -> {
                            onError(e);
                        }
                );
        addSubscription(s);
    }

    @Override
    public void onResult(Object o) {
        if (o instanceof ArrayList) {
            MineModel mineModel = (MineModel) ((ArrayList) o).get(0);
            tv.setText(mineModel.getContent());
        }
    }
}
