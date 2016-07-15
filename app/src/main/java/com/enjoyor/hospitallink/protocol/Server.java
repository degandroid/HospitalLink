package com.enjoyor.hospitallink.protocol;

import android.content.Context;

import retrofit.RestAdapter;

/**
 * Created by Administrator on 2016/6/29.
 */
public class Server {
    static final String  ENDPOINT = "http://115.28.37.145:9008/healthstationserver";
    static final String  WCHAT = "http://wxpay.weixin.qq.com";

    private static MineProtocol mMineProtocol;


    public static MineProtocol mineBuild(final Context context) {
        if (mMineProtocol == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(WCHAT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            mMineProtocol = restAdapter.create(MineProtocol.class);
        }
        return mMineProtocol;
    }
}
