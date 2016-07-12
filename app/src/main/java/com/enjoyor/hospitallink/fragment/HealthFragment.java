package com.enjoyor.hospitallink.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.enjoyor.hospitallink.HLApplication;
import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.adapter.FragmentFealth_adapter;
import com.enjoyor.hospitallink.adapter.Health_viewpager_adapter;
import com.enjoyor.hospitallink.custom.Mylistview;
import com.enjoyor.hospitallink.fragment.base.BaseFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.util.concurrent.TimeUnit.*;

/**
 * Created by Administrator on 2016/7/1.
 */
public class HealthFragment extends BaseFragment {
    @Bind(R.id.datum_text)
     Mylistview mylist;
    @Bind(R.id.health_pager)
    ViewPager health_viewPager;
    private List<ImageView>list=new ArrayList<ImageView>();
    private  List<String>list_url=new ArrayList<String>();
    private String BAIDU = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    public static    HealthFragment healthFragment=null;
    private  int currenItem=0;
    /*         碎片單例      */
    public static HealthFragment  getHealthFragment(){
        if (healthFragment==null){

            healthFragment=new HealthFragment();
        }
        return  healthFragment;

    }
    /*      切換圖片*/
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
           health_viewPager.setCurrentItem(currenItem);
        }
    };
    //一個定時累
    private ScheduledExecutorService scheduledExecutorService = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health, null);
        ButterKnife.bind(this, view);
        mylist.setAdapter(new FragmentFealth_adapter(getActivity()));
        for (int i=0;i<5;i++){
            ImageView imageView=new ImageView(getActivity());
            imageView.setMinimumWidth(View.MEASURED_STATE_TOO_SMALL);
            imageView.setMinimumHeight(View.MEASURED_STATE_TOO_SMALL);
            ImageLoader.getInstance().displayImage(BAIDU, imageView, HLApplication.option);
            list.add(imageView);
        }

        health_viewPager.setAdapter(new Health_viewpager_adapter(list));
        scheduledExecutorService=Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(),2, 2, TimeUnit.SECONDS);
        return view;

    }

private  class ScrollTask implements  Runnable{
    @Override
    public void run() {
        currenItem=(currenItem+1)%list.size();

       handler.obtainMessage().sendToTarget();
    }
}

}
