package com.enjoyor.hospitallink.act.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.common.BaseDate;
import com.enjoyor.hospitallink.model.BaseModel.BaseResponse;
import com.enjoyor.hospitallink.utils.ScreenUtil;
import com.enjoyor.hospitallink.utils.ScreenUtils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/6/29.
 */
public class BaseActivity extends AppCompatActivity {

    private AlertDialog mAlertDialog;

    private Dialog mDialog;

    /*----------------------------------网络数据请求----------------------------------*/
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
            //分析统计
//            MobclickAgent.reportError(this,e);
        } catch (Exception ee) {
            ee.printStackTrace();
//            MobclickAgent.reportError(this,ee);
        }

    }

    public void onError(int errorCode) {

    }

    public void onResult(Object o) {

    }

    /*----------------------------------添加toolbar----------------------------------*/

    private RelativeLayout contentview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentView(int view, boolean needLoad) {
        View contentview = LayoutInflater.from(this).inflate(view, null);
        initView(contentview, this, needLoad);
    }

    public void initView(View rootView, Context context, boolean needLoad) {
        initView(rootView, context, needLoad, false);
    }

    public void initView(View rootView, Context context, boolean needLoad, boolean needToolBar) {
        contentview = new RelativeLayout(context);

        RelativeLayout.LayoutParams rootLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
//        rootRL.setOrientation(LinearLayout.VERTICAL);
        contentview.setLayoutParams(rootLP);

        contentview.addView(rootView);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        if (needToolBar) {
            View toolbarView = LayoutInflater.from(context).inflate(R.layout.activity_base, contentview, false);
            contentview.addView(toolbarView, LinearLayout.LayoutParams.FILL_PARENT, getBarHeight());
            lp.setMargins(0, getBarHeight(), 0, 0);
        }
        rootView.setLayoutParams(lp);
        if (needLoad) {
            onRetry();
        }
//        setImmerseLayout(contentview);
        super.setContentView(contentview);
    }

    public void onRetry() {

    }

    private int getBarHeight() {
        if (BaseDate.getToolbarHeight() > 0)
            return BaseDate.getToolbarHeight();
        BaseDate.setToolbarHeight(ScreenUtils.returnBarHeight(this));
        return BaseDate.getToolbarHeight();
    }


    /*----------------------------------消息提示（可代替土司）----------------------------------*/
    public void showSnackBar(String text, int length, String btnText, View.OnClickListener listener) {
        View view = contentview;
        if (view == null)
            view = getWindow().getDecorView();
        Snackbar.make(view, text, length)
                .setAction(btnText, listener)
                .setActionTextColor(getResources().getColor(R.color.hl_color_white)).show();
    }

    public void showSnackBar(String text) {
        showSnackBar(text, Snackbar.LENGTH_LONG, "", null);
    }

    public void showSnackBar(String text, String btnText, View.OnClickListener listener) {
        showSnackBar(text, Snackbar.LENGTH_INDEFINITE, btnText, listener);
    }


        /*----------------------------------添加自定义对话框----------------------------------*/

    public void dialog(Context context, String tittle, String content, View.OnClickListener left, View.OnClickListener right) {
        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(R.layout.dialog_custom);

        ((TextView) mAlertDialog.findViewById(R.id.tv_dialog_tittle)).setText(tittle);//标题
        ((TextView) mAlertDialog.findViewById(R.id.tv_dialog_content)).setText(content);//文本内容
        ((TextView) mAlertDialog.findViewById(R.id.tv_dialog_left)).setOnClickListener(left);//左边按钮监听
        ((TextView) mAlertDialog.findViewById(R.id.tv_dialog_right)).setOnClickListener(right);//右边按钮监听
    }

    public void dialog(Context context, String tittle, String content, String leftStr, String rightStr, View.OnClickListener left, View.OnClickListener right) {
        dialog(context, tittle, content, left, right);
        ((TextView) mAlertDialog.findViewById(R.id.tv_dialog_left)).setText(leftStr);//左边文字描述
        ((TextView) mAlertDialog.findViewById(R.id.tv_dialog_right)).setText(rightStr);//右边文字描述
    }

    public void dialogCancel() {
        if (mAlertDialog != null) {
            mAlertDialog.cancel();
        }
    }

        /*----------------------------------圆形进度条----------------------------------*/

    public Dialog prepareDialgo(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.progress_custom, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCancelable(true);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
        return loadingDialog;
    }

    public void progress(Context context, String msg) {
        mDialog = prepareDialgo(context, msg);
        mDialog.show();
    }

    public void progressCancel() {
        if (mDialog != null)
            mDialog.cancel();
    }

    /*----------------------------------状态栏设置----------------------------------*/

    /**
     * 沉浸式开发方法
     */
    protected void setImmerseLayout(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = ScreenUtil.getStatusBarHeight(this.getBaseContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    /*----------------------------------软件盘----------------------------------*/
    //开启则关闭，关闭则开启
    public void hintKbOne() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //此方法只是关闭软键盘
    public void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
