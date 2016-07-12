package com.enjoyor.hospitallink.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.fragment.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/26.
 */
public class MineFragment extends BaseFragment {
//    @Bind(R.id.tv_info)TextView tv_info;

    private static MineFragment mineFragment;

    public static MineFragment getInstance(int info){
        mineFragment = new MineFragment();

        Bundle bundle = new Bundle();
        bundle.putString("info",info+"MineFragment");
        mineFragment.setArguments(bundle);
        return mineFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,null);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
//        tv_info.setText(bundle.getString("info"));
        return initView(view,getActivity(),false);
    }



}
