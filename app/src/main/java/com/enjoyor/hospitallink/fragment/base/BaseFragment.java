package com.enjoyor.hospitallink.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.model.BaseModel.BaseResponse;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/6/29.
 */
public class BaseFragment extends Fragment {
    protected RelativeLayout contentview;

    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        getCompositeSubscription().add(s);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View initView(View rootView, Context context, boolean needLoad) {
        contentview = new RelativeLayout(context);
        RelativeLayout.LayoutParams rootLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
//        rootRL.setOrientation(LinearLayout.VERTICAL);
        contentview.setLayoutParams(rootLP);
        contentview.addView(rootView);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        rootView.setLayoutParams(lp);
        if (needLoad) {

        }
        return contentview;
    }

    public void onRetry() {

    }

    public void showSnackBar(String text, int length, String btnText, View.OnClickListener listener) {
        Snackbar.make(getView(), text, length)
                .setAction(btnText, listener)
                .setActionTextColor(getResources().getColor(R.color.hl_color_white)).show();
    }

    public void showSnackBar(String text) {
        showSnackBar(text, Snackbar.LENGTH_LONG, "", null);
    }

    public void showSnackBar(String text, String btnText, View.OnClickListener listener) {
        showSnackBar(text, Snackbar.LENGTH_INDEFINITE, btnText, listener);
    }

    public void dealWithResponse(BaseResponse response) {
        if (response != null) {
            if (response.getErrcode() == BaseResponse.STATUS_ERROR) {
                onError(response.getErrcode());
            } else {
                onResult(response.getData());
            }
        } else {
            onError(BaseResponse.STATUS_NULL);
        }
    }

    public void onError(Throwable e) {
        try {
            e.printStackTrace();
            showSnackBar(e.toString());
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void onResult(Object o) {

    }

    public void onError(int errorCode) {

    }

    /*-------------------------------------通过sessionId判断是否是登陆状态-------------------------------------*/
    public boolean isLogin() {
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(this.getClass().getSimpleName());//只统计跳转
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(this.getClass().getSimpleName());//只统计跳转
    }

    /*----------------------------------软件盘----------------------------------*/
    public void hintKbOne() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    //此方法只是关闭软键盘
    public void hintKbTwo(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

        }
    }
}
