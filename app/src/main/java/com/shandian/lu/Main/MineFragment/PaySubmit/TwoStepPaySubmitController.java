package com.shandian.lu.Main.MineFragment.PaySubmit;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewDingJinPayBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.ThirdPay.ZFB.ZhiFuBaoUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/13.
 */

public class TwoStepPaySubmitController extends BaseController {
    private ZhiFuBaoUtil zhiFuBaoUtil;
    private String hyId,baojiaId;
    private String type="zfbpay";

    @BindView(R.id.rly_new_hyxq_two_steps_back)
    RelativeLayout rlyNewHYXQTwoStepsBack;
    @OnClick(R.id.rly_new_hyxq_two_steps_back)
    public void rlyNewHYXQTwoStepsBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_new_hyxq_two_steps_total)
    TextView tvNewHYXQTwoStepsTotal;
    @BindView(R.id.tv_new_hyxq_two_steps_dingjin)
    TextView tvNewHYXQTwoStepsDingJin;
    @BindView(R.id.tv_new_hyxq_two_steps_other)
    TextView tvNewHYXQTwoStepsOther;
    @BindView(R.id.cb_new_hyxq_two_steps_wxpay)
    CheckBox cbNewHYXQTwoStepsWXPay;
    @BindView(R.id.rly_new_hyxq_two_steps_wxpay)
    RelativeLayout rlyNewHYXQTwoStepsWXPay;
    @OnClick(R.id.rly_new_hyxq_two_steps_wxpay)
    public void rlyNewHYXQTwoStepsWXPayOnclick(){
        selectPayType("wxpay");
        type = "wxpay";
    }
    @BindView(R.id.cb_new_hyxq_two_steps_zfbpay)
    CheckBox cbNewHYXQTwoStepsZFBPay;
    @BindView(R.id.rly_new_hyxq_two_steps_zfbpay)
    RelativeLayout rlyNewHYXQTwoStepsZFBPay;
    @OnClick(R.id.rly_new_hyxq_two_steps_zfbpay)
    public void rlyNewHYXQTwoStepsZFBPayOnclick(){
        selectPayType("zfbpay");
        type = "zfbpay";

    }
    @BindView(R.id.cb_new_hyxq_two_steps_yerpay)
    CheckBox cbNewHYXQTwoStepsYERPay;
    @BindView(R.id.rly_new_hyxq_two_steps_yerpay)
    RelativeLayout rlyNewHYXQTwoStepsYERPay;
    @OnClick(R.id.rly_new_hyxq_two_steps_yerpay)
    public void rlyNewHYXQTwoStepsYERPayOnclick(){
        selectPayType("yerpay");
        type = "yerpay";
    }
    @BindView(R.id.bt_new_hyxq_two_steps_submit)
    Button btNewHYXQTwoStepsSubmit;
    @OnClick(R.id.bt_new_hyxq_two_steps_submit)
    public void btNewHYXQTwoStepsSubmitOnclick(){
        switch (type){
            case "wxpay":

                break;
            case "zfbpay":
                    zhiFuBaoPay();
                break;
            case "yerpay":

                break;
        }
    }
    public TwoStepPaySubmitController(Activity activity1){
        activity = activity1;
        init();

    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        zhiFuBaoUtil = new ZhiFuBaoUtil(activity);
        getId();
        getDingJinFromNet();
    }


    private void selectPayType(String type){
        switch (type){
            case "wxpay":
                cbNewHYXQTwoStepsWXPay.setBackgroundResource(R.mipmap.orange_selected);
                cbNewHYXQTwoStepsZFBPay.setBackgroundResource(R.color.transparent);
                cbNewHYXQTwoStepsYERPay.setBackgroundResource(R.color.transparent);
                break;
            case "zfbpay":

                cbNewHYXQTwoStepsWXPay.setBackgroundResource(R.color.transparent);
                cbNewHYXQTwoStepsZFBPay.setBackgroundResource(R.mipmap.orange_selected);
                cbNewHYXQTwoStepsYERPay.setBackgroundResource(R.color.transparent);
                break;
            case "yerpay":

                cbNewHYXQTwoStepsWXPay.setBackgroundResource(R.color.transparent);
                cbNewHYXQTwoStepsZFBPay.setBackgroundResource(R.color.transparent);
                cbNewHYXQTwoStepsYERPay.setBackgroundResource(R.mipmap.orange_selected);
                break;
        }
    }
    private void getId(){
        hyId = activity.getIntent().getStringExtra("hyId");
        if(hyId == null){
            hyId = "";
        }
        baojiaId = activity.getIntent().getStringExtra("baojiaId");
        if(baojiaId == null){
            baojiaId = "";
        }
    }
    private void getDingJinFromNet(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("baojiaid",baojiaId);
        paramMap.put("hyid",hyId);
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getDingJinFromNet(paramMap, new Observer<NewDingJinPayBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewDingJinPayBean newDingJinPayBean) {
                tvNewHYXQTwoStepsTotal.setText(newDingJinPayBean.getNr().getZjine());
                tvNewHYXQTwoStepsDingJin.setText(newDingJinPayBean.getNr().getDingjin()+"");

            }
        });
    }





    /*支付宝支付*/
    public void zhiFuBaoPay(){
         String outTradeNo = getOutTradeNo();
/*Toast.makeText(activity, " onCompleted mPopView:"+goodsName+price, Toast.LENGTH_LONG).show();*/
        String price = (String) tvNewHYXQTwoStepsDingJin.getText();
        if(price == null){
            price = "";
        }

        /*String passback_params = "{ \"dingdanid\" = \""+price"+\",\n \"lx\" = \"1\"\n}";*/



        String passback_params = "{\"dingdanid\":\""+baojiaId+"\",\"lx\":\"1\"}";
        try {
            passback_params = URLEncoder.encode(passback_params, "utf8");
            /*Toast.makeText(activity,passback_params,Toast.LENGTH_LONG).show();*/
            zhiFuBaoUtil.payV2(null, "定金支付", ""+price,outTradeNo,passback_params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        zhiFuBaoUtil.setOnPaySuccessfulListener(new ZhiFuBaoUtil.OnPaySuccessfulListener() {
            @Override
            public void isSuccessful(boolean isSuccessful) {


            }
        });
                    /*去支付金钱*/
                    /*Toast.makeText(activity," 我成功啦111 isSuccessful:"+helpMeBuyBean.getOrderNo(),Toast.LENGTH_LONG).show();*/


    }
    /*支付宝支付*/

    /**
     * 要求外部订单号必须唯一。
     * @return
     */
    private  String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }
}
