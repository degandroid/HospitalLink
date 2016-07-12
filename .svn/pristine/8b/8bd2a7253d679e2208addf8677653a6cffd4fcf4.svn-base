package com.enjoyor.hospitallink.model.BaseModel;

/**
 * Created by Administrator on 2016/6/29.
 */
public class BaseResponse<T> {

    public static final int STATUS_NULL = -1;//返回空值或返回的值不是BaseResponse格式;
    public static final int STATUS_SUCCESS = 1001;
    public static final int STATUS_ERROR = 0;
    private String Msg;
    private int Errcode;
    private T Data;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getErrcode() {
        return Errcode;
    }

    public void setErrcode(int errcode) {
        Errcode = errcode;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
