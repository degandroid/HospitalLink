package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.common.Constant;
import com.enjoyor.hospitallink.model.PayInfo;
import com.enjoyor.hospitallink.protocol.Server;
import com.enjoyor.hospitallink.utils.StringUtils;
import com.enjoyor.hospitallink.utils.alipay.AlipayUtil;
import com.enjoyor.hospitallink.utils.alipay.PayResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2016/7/12.
 */
public class PayExaminationOrderActivity extends ToolBarActivity implements View.OnClickListener {

    @Bind(R.id.re_alipay)
    RelativeLayout re_alipay;
    @Bind(R.id.re_wchat)
    RelativeLayout re_wchat;
    @Bind(R.id.re_bank)
    RelativeLayout re_bank;

    @Bind(R.id.iv_alipay_sel)
    ImageView iv_alipay_sel;
    @Bind(R.id.iv_wchat_sel)
    ImageView iv_wchat_sel;
    @Bind(R.id.iv_bank_sel)
    ImageView iv_bank_sel;


    @Bind(R.id.ly_topay)
    LinearLayout ly_topay;

    private int payValueType = 1;//默认选中支付宝支付

    private IWXAPI api;


    private static final int SDK_PAY_FLAG = 1;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayExaminationOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayExaminationOrderActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayExaminationOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payexaminationorder, false);
        api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
        ButterKnife.bind(this);
        initHead();
        initPayStyle();
    }
    private void initHead() {
        setTitle("支付体检订单");
    }


    private void initPayStyle() {
        re_alipay.setOnClickListener(this);
        re_wchat.setOnClickListener(this);
        re_bank.setOnClickListener(this);
        initDefaultPay();
        iv_alipay_sel.setImageResource(R.mipmap.hl_icon_paystyle_sel);
    }

    private void initDefaultPay() {
        iv_alipay_sel.setImageResource(R.mipmap.hl_icon_paystyle);
        iv_wchat_sel.setImageResource(R.mipmap.hl_icon_paystyle);
        iv_bank_sel.setImageResource(R.mipmap.hl_icon_paystyle);
    }

    @OnClick(R.id.ly_topay)
    public void toPay() {
        if (Constant.PAY_VALUE_ALIPAY == payValueType) {
            //调用接口获取支付数据orderInfo
            AlipayUtil alipayUtil = new AlipayUtil(PayExaminationOrderActivity.this, mHandler);
            String payInfo = alipayUtil.getPayInfo("测试的商品", "该测试商品的详细描述", "0.1");
            if (!StringUtils.isBlank(payInfo)) {
                alipayUtil.startAlipay(payInfo);
            }
        } else if (Constant.PAY_VALUE_WCHAT == payValueType) {
            sendData();
        } else if (Constant.PAY_VALUE_BANK == payValueType) {

        }
//        Intent intent = new Intent(PayExaminationOrderActivity.this, ConfirmExaminationActivity.class);
//        startActivity(intent);
    }

    public void sendData() {
        Subscription s;
        s = Server.mineBuild(PayExaminationOrderActivity.this).getInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            Log.i("sendData","sendData"+data.toString());
                            getJsonInfo(data);
                        },
                        e -> {
                            onError(e);
                        }
                );
        addSubscription(s);
    }

    public void getJsonInfo(PayInfo payInfo) {
        if (null != payInfo) {
            PayReq req = new PayReq();
//                req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
            req.appId = payInfo.getAppid();
            req.partnerId = payInfo.getPartnerid();
            req.prepayId = payInfo.getPrepayid();
            req.nonceStr = payInfo.getNoncestr();
            req.timeStamp = payInfo.getTimestamp();
            req.packageValue = "";
            req.sign = payInfo.getSign();
            req.extData = "app data"; // optional
            Toast.makeText(PayExaminationOrderActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            api.sendReq(req);
        }
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        switch (key) {
            case R.id.re_alipay:
                payValueType = Constant.PAY_VALUE_ALIPAY;

                initDefaultPay();
                iv_alipay_sel.setImageResource(R.mipmap.hl_icon_paystyle_sel);
                break;
            case R.id.re_wchat:
                payValueType = Constant.PAY_VALUE_WCHAT;

                initDefaultPay();
                iv_wchat_sel.setImageResource(R.mipmap.hl_icon_paystyle_sel);
                break;
            case R.id.re_bank:
                payValueType = Constant.PAY_VALUE_BANK;

                initDefaultPay();
                iv_bank_sel.setImageResource(R.mipmap.hl_icon_paystyle_sel);
                break;
        }
    }
}
