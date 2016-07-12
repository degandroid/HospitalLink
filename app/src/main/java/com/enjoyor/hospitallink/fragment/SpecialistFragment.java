package com.enjoyor.hospitallink.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.ActivtyDoctorMain;
import com.enjoyor.hospitallink.adapter.Fragment_specialist_adapter;
import com.enjoyor.hospitallink.fragment.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/7.
 * 专家预约表单
 */
public class SpecialistFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    public  static   SpecialistFragment specialistFragment=null;
    public  static  SpecialistFragment getSpecialistFragment(){
        if (specialistFragment==null){
            specialistFragment=new SpecialistFragment();

        }
        return  specialistFragment;
    };
    @Bind(R.id.specialist_list)
    ListView specialist_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_specialist,null);
        ButterKnife.bind(this,view);
        specialist_list.setAdapter(new Fragment_specialist_adapter(getActivity()));
        specialist_list.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), ActivtyDoctorMain.class);
        startActivity(intent);
    }
}
