package com.enjoyor.hospitallink.act;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.BaseActivity;
import com.enjoyor.hospitallink.adapter.FragmentTabAdapter;
import com.enjoyor.hospitallink.fragment.HealthFragment;
import com.enjoyor.hospitallink.fragment.LineHospitalFragment;
import com.enjoyor.hospitallink.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/1.
 */
public class MainTabActivity extends BaseActivity {
    @Bind(R.id.main_content_radio)
    RadioGroup main_content_radio;
    @Bind(R.id.main_tab1)
    RadioButton main_tab1;
    @Bind(R.id.main_tab2)
    RadioButton main_tab2;
    @Bind(R.id.main_tab3)
    RadioButton main_tab3;

    private List<Fragment> fragment_list = new ArrayList<>();
    public static int CurrentFragment = 0;
    private long exitTime = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maintab,false);
        ButterKnife.bind(this);
        initAdapter();
        initDrawable();
    }

    private void initAdapter() {
        initFragment();
        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragment_list, R.id.tab_content, main_content_radio);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                CurrentFragment = index;
            }
        });
    }

    private void initFragment() {
        fragment_list.clear();
        fragment_list.add(LineHospitalFragment.getInstance(""));
        fragment_list.add( HealthFragment.getHealthFragment());
        fragment_list.add(MineFragment.getInstance(3));
    }


    private void initDrawable() {
        int tabIconHeight = getResources().getDimensionPixelOffset(R.dimen.tab_icon_height);
        Drawable topDrawable1 = getResources().getDrawable(R.drawable.hl_sel_tab_linehospital);
        topDrawable1.setBounds(0, 0, tabIconHeight, tabIconHeight);
        main_tab1.setCompoundDrawables(null, topDrawable1, null, null);

        Drawable topDrawable2 = getResources().getDrawable(R.drawable.hl_sel_tab_health);
        topDrawable2.setBounds(0, 0, tabIconHeight, tabIconHeight);
        main_tab2.setCompoundDrawables(null, topDrawable2, null, null);

        Drawable topDrawable3 = getResources().getDrawable(R.drawable.hl_sel_tab_mine);
        topDrawable3.setBounds(0, 0, tabIconHeight, tabIconHeight);
        main_tab3.setCompoundDrawables(null, topDrawable3, null, null);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}