package com.enjoyor.hospitallink;

import android.app.Application;
import android.app.Service;
import android.graphics.Bitmap;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.enjoyor.hospitallink.db.DBHelper;
import com.enjoyor.hospitallink.service.LocationService;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016/7/1.
 */
public class HLApplication extends Application{
    private static HLApplication mHLApplication;
    private DBHelper mDBHelper;

    public LocationService locationService;
    public Vibrator mVibrator;

    public static DisplayImageOptions option;

    @Override
    public void onCreate() {
        super.onCreate();
        mHLApplication = this;

        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

        option = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        //imageloader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }


    public static HLApplication getInstance() {
        return mHLApplication;
    }

    public DBHelper getDBHelper() {
        if (mDBHelper == null) {
            mDBHelper = new DBHelper(this);
        }
        return mDBHelper;
    }
}
