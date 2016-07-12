package com.enjoyor.hospitallink.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.enjoyor.hospitallink.R;

/**
 * Created by Administrator on 2016/7/7.
 */
public class AlphaPopWindow extends PopupWindow {
    private View conentView;

    public AlphaPopWindow(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popwindow_alpha, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        this.setContentView(conentView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable dw = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.AnimationPreview);

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
//            this.showAsDropDown(null,0, 0);
//            this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//            this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            this.dismiss();
        }
    }
}
