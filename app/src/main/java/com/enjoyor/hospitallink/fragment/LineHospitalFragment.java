package com.enjoyor.hospitallink.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.enjoyor.hospitallink.HLApplication;
import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.Actual_gh;
import com.enjoyor.hospitallink.act.AppointDetialActivity;
import com.enjoyor.hospitallink.act.ExaminationActivity;
import com.enjoyor.hospitallink.act.GetReportActivity;
import com.enjoyor.hospitallink.act.HospitalDetialActivity;
import com.enjoyor.hospitallink.act.MessageActivity;
import com.enjoyor.hospitallink.adapter.HosAndDocAdapter;
import com.enjoyor.hospitallink.common.BaseDate;
import com.enjoyor.hospitallink.custom.ObservableScrollView;
import com.enjoyor.hospitallink.custom.ObservableScrollView.ScrollViewListener;
import com.enjoyor.hospitallink.custom.PullToRefreshLayout;
import com.enjoyor.hospitallink.custom.PullableListView;
import com.enjoyor.hospitallink.custom.XListView;
import com.enjoyor.hospitallink.fragment.base.BaseFragment;
import com.enjoyor.hospitallink.model.BDLPoint;
import com.enjoyor.hospitallink.service.LocationService;
import com.enjoyor.hospitallink.utils.ScrollListUtil;
import com.enjoyor.hospitallink.utils.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/1.
 */
public class LineHospitalFragment extends BaseFragment implements XListView.IXListViewListener, ViewPager.OnPageChangeListener, ScrollViewListener, View.OnClickListener {
    @Bind(R.id.osv_scroll)
    ObservableScrollView osv_scroll;

    @Bind(R.id.re_edittittle)
    RelativeLayout re_edittittle;
    @Bind(R.id.ly_point)
    LinearLayout ly_point;
    @Bind(R.id.tv_point)
    TextView tv_point;
    @Bind(R.id.iv_arrow_down)
    ImageView iv_arrow_down;
    @Bind(R.id.re_searchframe)
    RelativeLayout re_searchframe;
    @Bind(R.id.ly_searchframe)
    LinearLayout ly_searchframe;
    @Bind(R.id.iv_search)
    ImageView iv_search;
    @Bind(R.id.tv_hint)
    TextView tv_hint;
    @Bind(R.id.iv_message)
    ImageView iv_message;

    @Bind(R.id.iv_banner)
    ImageView iv_banner;

    @Bind(R.id.ly_register)
    LinearLayout ly_register;
    @Bind(R.id.ly_appoint)
    LinearLayout ly_appoint;
    @Bind(R.id.ly_examination)
    LinearLayout ly_examination;
    @Bind(R.id.ly_report)
    LinearLayout ly_report;

    @Bind(R.id.vp_minefollow)
    ViewPager vp_minefollow;
    @Bind(R.id.ly_poi)
    LinearLayout ly_poi;
    private ImageView imageView;//viewpager下的点

//    @Bind(R.id.xlv_hospital)
//    XListView xlv_hospital;

    /*模糊背景*/
    @Bind(R.id.ly_alpha)
    LinearLayout ly_alpha;
    @Bind(R.id.ly_alpha_search)
    LinearLayout ly_alpha_search;
    @Bind(R.id.tv_cancel)
    TextView tv_cancel;
    @Bind(R.id.tv_search_city)
    TextView tv_search_city;

    @Bind(R.id.et_search_all)
    EditText et_search_all;
    @Bind(R.id.sv_hosanddoc)
    ScrollView sv_hosanddoc;

    @Bind(R.id.re_search_info)
    RelativeLayout re_search_info;

    @Bind(R.id.ly_searchhospital)
    LinearLayout ly_searchhospital;
    @Bind(R.id.ly_searchdoctor)
    LinearLayout ly_searchdoctor;

    @Bind(R.id.lv_searchhospital)
    ListView lv_searchhospital;
    @Bind(R.id.lv_searchdoctor)
    ListView lv_searchdoctor;


    private int imageHeight;
    private LayoutInflater mInflater;
    private LocationService locationService;
    private static LineHospitalFragment mLineHospitalFragment;

    private List<View> view_list = new ArrayList<>();//viewpager所用到的布局------我关注的医院
    private List<String> url_list = new ArrayList<>();//图片地址集合-----我关注的医院
    private List<ImageView> image_list = new ArrayList<>();//切换时显示的小点-----我关注的医院
    private List<String> hospital_list = new ArrayList<>();//图片地址集合-----更多本地医院
    private String BAIDU = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";



    @Bind(R.id.refresh_view)PullToRefreshLayout refresh_view;
    @Bind(R.id.content_view)PullableListView content_view;

    public static LineHospitalFragment getInstance(String mCity) {
        mLineHospitalFragment = new LineHospitalFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mCity", mCity);
        mLineHospitalFragment.setArguments(bundle);
        return mLineHospitalFragment;
    }

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hospitallink, null);
        ButterKnife.bind(this, view);
        mInflater = getActivity().getLayoutInflater();
        startLocation();
        initOnClick();
        initSearchAll();
        initListeners();
        initViewPager();
        initListView();
        return initView(view, getActivity(), false);
    }

    private void initOnClick() {
        ly_point.setOnClickListener(this);
        re_searchframe.setOnClickListener(this);
        ly_searchframe.setOnClickListener(this);

        iv_message.setOnClickListener(this);
        ly_register.setOnClickListener(this);
        ly_appoint.setOnClickListener(this);
        ly_examination.setOnClickListener(this);
        ly_report.setOnClickListener(this);

        tv_cancel.setOnClickListener(this);
    }

    /*------------------------------实现搜索框的渐变效果------------------------------*/

    private void initSearchAll() {
        et_search_all.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isBlank(s.toString().trim())) {
                    re_search_info.setVisibility(View.GONE);
                    ly_searchhospital.setVisibility(View.VISIBLE);
                    ly_searchdoctor.setVisibility(View.VISIBLE);
                    initHosAndDocList();
                } else {
                    re_search_info.setVisibility(View.VISIBLE);
                    ly_searchhospital.setVisibility(View.GONE);
                    ly_searchdoctor.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initHosAndDocList() {

        lv_searchhospital.setAdapter(new HosAndDocAdapter.HospitalAdapter(getActivity()));
        lv_searchdoctor.setAdapter(new HosAndDocAdapter.DoctorAdapter(getActivity()));
        ScrollListUtil.setListViewHeightBasedOnChildren(lv_searchhospital);
        ScrollListUtil.setListViewHeightBasedOnChildren(lv_searchdoctor);
    }

    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = iv_banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_banner.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = iv_banner.getHeight();

                osv_scroll.setScrollViewListener(LineHospitalFragment.this);
            }
        });
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {
            re_edittittle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= imageHeight) {
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            re_edittittle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            setWhiteLayout(true);
        } else {
            re_edittittle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            setWhiteLayout(false);
        }
    }

    private void setWhiteLayout(boolean worb) {
        if (worb) {
            tv_point.setTextColor(getResources().getColor(R.color.hl_color_white));
            iv_arrow_down.setImageResource(R.mipmap.hl_icon_arrow_down_small);
            re_searchframe.setBackgroundResource(R.drawable.hl_bg_alpha_frame_fillet);
            iv_search.setImageResource(R.mipmap.hl_icon_search);
            tv_hint.setTextColor(getResources().getColor(R.color.hl_color_white));
            iv_message.setImageResource(R.mipmap.hl_icon_message);
        } else {
            tv_point.setTextColor(getResources().getColor(R.color.hl_textcolor_dark));
            iv_arrow_down.setImageResource(R.mipmap.hl_icon_arrow_down_small_black);
            re_searchframe.setBackgroundResource(R.drawable.hl_bg_unalpha_frame_fillet);
            iv_search.setImageResource(R.mipmap.hl_icon_search_black);
            tv_hint.setTextColor(getResources().getColor(R.color.hl_textcolor_dark));
            iv_message.setImageResource(R.mipmap.hl_icon_message_black);
        }
    }

    /*------------------------------我关注的医院------------------------------*/

    private void initViewPager() {
        for (int i = 0; i < 5; i++) {
            url_list.add(BAIDU);
        }
        for (int i = 0; i < url_list.size(); i++) {
            View view = mInflater.inflate(R.layout.item_linehospital_carousel, null);
            ImageView iv_myfollow = (ImageView) view.findViewById(R.id.iv_myfollow);
            ImageLoader.getInstance().displayImage(url_list.get(i), iv_myfollow, HLApplication.option);
            view_list.add(view);
        }
        for (int i = 0; i < url_list.size(); i++) {
            imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(12, 12));
            image_list.add(imageView);
            if (i == 0) {
                imageView.setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                imageView.setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            ((ViewGroup) ly_poi).addView(imageView);
        }
        vp_minefollow.setAdapter(new MyFollowAdapter());
        vp_minefollow.setOnPageChangeListener(this);
        vp_minefollow.setCurrentItem(0);
    }

    public class MyFollowAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            try {
                ((ViewPager) container).addView(view_list.get(position % view_list.size()), 0);
            } catch (Exception e) {
                //handler something
            }
            return view_list.get(position % view_list.size());
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }

        @Override
        public void finishUpdate(View arg0) {
        }


        @Override
        public void destroyItem(View container, int position, Object object) {
//			((ViewPager)container).removeView(list.get(position%list.size()));
        }
    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % url_list.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /*------------------------------本地医院------------------------------*/

    private void initListView() {
        for (int i = 0; i < 10; i++) {
            hospital_list.add(i + "条info。。。");
        }
        osv_scroll.canPullDown();
        refresh_view.setLoading("更多医院正在努力接洽中");
        refresh_view.setLoadingPic(R.mipmap.hl_icon_load);
        refresh_view.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

                // 下拉刷新操作
//                new Handler()
//                {
//                    @Override
//                    public void handleMessage(Message msg)
//                    {
//                        // 千万别忘了告诉控件刷新完毕了哦！
//                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//                    }
//                }.sendEmptyMessageDelayed(0, 5000);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                // 加载操作
                new Handler()
                {
                    @Override
                    public void handleMessage(Message msg)
                    {
                        // 千万别忘了告诉控件加载完毕了哦！
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 5000);
            }

        });
        content_view.setAdapter(new HospitalAdapater());
        content_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), HospitalDetialActivity.class);
                intent.putExtra("hospitalName",hospital_list.get(position));
                startActivity(intent);
            }
        });
        ScrollListUtil.setListViewHeightBasedOnChildren(content_view);
//        xlv_hospital.setPullRefreshEnable(false);
//        xlv_hospital.setPullLoadEnable(true, "更多医院正在努力接洽中");
//        xlv_hospital.setXListViewListener(this);
//        xlv_hospital.setAdapter(new HospitalAdapater());
//
//        xlv_hospital.setSelection(0);
//        xlv_hospital.setFocusable(false);
//        ScrollListUtil.setListViewHeightBasedOnChildren(xlv_hospital);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < image_list.size(); i++) {
            if (i == selectItems) {
                image_list.get(i).setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                image_list.get(i).setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
        }
    }

    class HospitalAdapater extends BaseAdapter {

        @Override
        public int getCount() {
            return hospital_list.size();
        }

        @Override
        public Object getItem(int position) {
            return hospital_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_linehospital, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_hospitalname.setText(hospital_list.get(position));

            return convertView;
        }

        class ViewHolder {

            @Bind(R.id.tv_hospitalname)
            TextView tv_hospitalname;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadMore() {
        showSnackBar("弄啥嘞");
    }

    /*------------------------------百度定位------------------------------*/

    public void logMsg(BDLocation location) {
        try {
            if (tv_point != null)
                tv_point.setText(location.getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (tv_search_city != null)
                tv_search_city.setText(
                        Html.fromHtml(
                                "在" + "<font color='#2b7cb2'>" + location.getCity() + "</font>" +
                                        "为您搜索相关内容"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLocation() {
        locationService = ((HLApplication) (getActivity().getApplication())).locationService;
        locationService.registerListener(mListener);
        int type = getActivity().getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                BDLPoint point = new BDLPoint();
                point.setTime(location.getTime());
                point.setErrorCode(location.getLocType() + "");
                point.setLatitude(location.getLatitude() + "");
                point.setLontitude(location.getLongitude() + "");
                point.setRadius(location.getRadius() + "");
                point.setCountryCode(location.getCountryCode() + "");
                point.setCountry(location.getCountry());
                point.setCitycode(location.getCityCode() + "");
                point.setCity(location.getCity());
                point.setDistrict(location.getDistrict() + "");
                point.setStreet(location.getStreet());
                point.setAddr(location.getAddrStr());
                point.setDescribe(location.getLocationDescribe());
                point.setDirectionValue(location.getDirection() + "");
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        point.setPoi(poi.getName() + "");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                    sb.append("\nspeed : ");
//                    sb.append(location.getSpeed());// 单位：km/h
//                    sb.append("\nsatellite : ");
//                    sb.append(location.getSatelliteNumber());
//                    sb.append("\nheight : ");
//                    sb.append(location.getAltitude());// 单位：米
//                    sb.append("\ndescribe : ");
//                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    point.setOperationers(location.getOperators() + "");
                    point.setDescribe("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    point.setDescribe("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    point.setDescribe("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    point.setDescribe("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    point.setDescribe("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                logMsg(location);
                BaseDate.setLocation(location);
                HLApplication.getInstance().getDBHelper().saveBDLPoint(point);
            }
        }
    };
    public void hintTab(){
        ((RadioGroup) getActivity().findViewById(R.id.main_content_radio)).setVisibility(View.GONE);

    }
    public void displayTab(){
        ((RadioGroup) getActivity().findViewById(R.id.main_content_radio)).setVisibility(View.VISIBLE);
    }
    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        int key = v.getId();
        switch (key) {
            case R.id.re_searchframe://搜索医院
                hintTab();
                ly_alpha_search.setVisibility(View.VISIBLE);
                ly_alpha.setAlpha(0.9f);
                Animation animation = AnimationUtils.loadAnimation(
                        getActivity(), R.anim.fade_in);
                // 启动动画
                ly_alpha_search.startAnimation(animation);
                osv_scroll.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View arg0, MotionEvent arg1) {
                        return true;
                    }
                });
                break;
            case R.id.tv_cancel://搜索医院
                displayTab();
                ly_searchhospital.setVisibility(View.GONE);
                ly_searchdoctor.setVisibility(View.GONE);
                ly_alpha_search.setVisibility(View.GONE);
                et_search_all.setText("");
                hintKbTwo(ly_alpha_search);
                Animation animation2 = AnimationUtils.loadAnimation(
                        getActivity(), R.anim.fade_out);
                ly_alpha_search.startAnimation(animation2);
                osv_scroll.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                break;

            case R.id.ly_point://定位选择城市
                break;

            case R.id.iv_message://消息
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.ly_register://实时挂号
                Intent _actual = new Intent(getActivity(), Actual_gh.class);
                startActivity(_actual);
                break;
            case R.id.ly_appoint://手机预约
                Intent _appoint = new Intent(getActivity(), AppointDetialActivity.class);
                startActivity(_appoint);

                break;
            case R.id.ly_examination://体检套餐
                Intent _examination = new Intent(getActivity(), ExaminationActivity.class);
                startActivity(_examination);
                break;
            case R.id.ly_report://取报告单
                Intent _report = new Intent(getActivity(), GetReportActivity.class);
                startActivity(_report);
                break;

        }
    }

    @Override
    public void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }
}