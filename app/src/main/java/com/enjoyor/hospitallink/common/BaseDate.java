package com.enjoyor.hospitallink.common;

import com.baidu.location.BDLocation;

/**
 * Created by Administrator on 2016/6/30.
 */
public class BaseDate {

    private static int toolbarHeight;
    private static BDLocation location;
//    private static String mCity;

    public static int getToolbarHeight() {
        return toolbarHeight;
    }

    public static void setToolbarHeight(int toolbarHeight) {
        BaseDate.toolbarHeight = toolbarHeight;
    }

//    public static String getmCity() {
//        return mCity;
//    }
//
//    public static void setmCity(String mCity) {
//        BaseDate.mCity = mCity;
//    }


    public static BDLocation getLocation() {
        return location;
    }

    public static void setLocation(BDLocation location) {
        BaseDate.location = location;
    }
}
