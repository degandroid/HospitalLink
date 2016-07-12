package com.enjoyor.hospitallink.fragment;

import android.os.Bundle;

import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.adapter.Fragment_specialist_adapter;
import com.enjoyor.hospitallink.fragment.base.BaseFragment;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/11.
 */
public class Data_AppFragment extends BaseFragment{

    @Bind(R.id.doctor_list)
    ListView doctor_list;

    @Bind(R.id.data1)
    TextView data1;
    @Bind(R.id.data2)
    TextView data2;
    @Bind(R.id.data3)
    TextView data3;
    @Bind(R.id.data4)
    TextView data4;
    @Bind(R.id.data5)
    TextView data5;
    @Bind(R.id.data6)
    TextView data6;
    @Bind(R.id.data7)
    TextView data7;
    Long data_drop;





    private List<String> list_data;
    private  List<TextView>list_textview;
 static    Data_AppFragment data_appFragment=null;
    public  static  Data_AppFragment getData_app(){
        if (data_appFragment==null){

            data_appFragment=new Data_AppFragment();

        }

        return data_appFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     View view=inflater.inflate(R.layout.fragmeny_data_app,null);
        ButterKnife.bind(this,view);
        list_textview=new ArrayList<TextView>();
        list_data= new ArrayList<String>();
        list_textview.add(data1);
        list_textview.add(data2);
        list_textview.add(data3);
        list_textview.add(data4);
        list_textview.add(data5);
        list_textview.add(data6);
        list_textview.add(data7);
        data_drop= System.currentTimeMillis();
        doctor_list.setAdapter(new Fragment_specialist_adapter(getActivity()));
        Log.e("wokao",data_drop+"");




        tiem_data(data_drop);
        for (int i=0;i<7;i++){
            list_textview.get(i).setText(list_data.get(i));


        }
        return view;
    }


/********************时间周几**********************/
    private void tiem_data(Long data_drop ){
        Date data=null;

        for (int i=0;i<7;i++){
            SimpleDateFormat format=new SimpleDateFormat("mm-dd");
            Long as_data=60*60*24*1000*(i+1)+data_drop;
           String d=format.format(as_data);
            try {
                data =format.parse(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.e("wokao",data.getDay()+"");
            switch (data.getDay()){
                case 1:
                    list_data.add("周一");
                break;
                case 2:
                    list_data.add("周二");
                    break;
                case 3:
                    list_data.add("周三");
                    break;
                case 4:
                    list_data.add("周四");
                    break;
                case 5:
                    list_data.add("周五");
                    break;
                case 6:
                    list_data.add("周六");
                    break;
                case 0:
                    list_data.add("周日");
                    break;






            }
        }


    }

}
