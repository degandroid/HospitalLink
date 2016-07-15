package com.enjoyor.hospitallink.protocol;


import com.enjoyor.hospitallink.model.BaseModel.BaseResponse;
import com.enjoyor.hospitallink.model.MineModel;
import com.enjoyor.hospitallink.model.PayInfo;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface MineProtocol{
//        @GET("/{appName}/p/list")
//        Observable<BaseResponse<List<Bill>>> requestCouponList(@Path("appName") String appName, @Query("orderId") Long orderId);
//
//
//        @POST("/{appName}/ord/update")
//        Observable<BaseResponse> changeOrder(@Path("appName") String appName, @QueryMap Map<String, Object> map, @Body Map<String, Object> bodyMap);

        @GET("/record/notes/947.action")
        Observable<BaseResponse<List<MineModel>>> getMine();

        @GET("/pub_v2/app/app_pay.php?plat=android")
        Observable<PayInfo> getInfo();
}
