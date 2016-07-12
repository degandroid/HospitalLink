package com.enjoyor.hospitallink.protocol;


import com.enjoyor.hospitallink.model.BaseModel.BaseResponse;
import com.enjoyor.hospitallink.model.MineModel;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface MineProtocol{
        @GET("/record/notes/947.action")
        Observable<BaseResponse<List<MineModel>>> getMine();
}
