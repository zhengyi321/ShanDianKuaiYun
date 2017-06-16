package com.shandian.lu.Main.MineFragment.PaySubmit;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewDingJinPayBean;
import com.example.mynewslayoutlib.Bean.NewWeiKuanPayBean;
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
    private String hyId,baojiaId,status;
    private String type="zfbpay";
    private String payTitle = "";
    private String price = "";
    private String orderInfo = "";
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
    @BindView(R.id.tv_new_hyxq_two_steps_other)//#ff6100
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
        status = activity.getIntent().getStringExtra("status");
        if(status == null){
            status = "";
        }
        getPayType();
    }

    private void getPayType(){
        switch (status){
            case "dingjin":
                payTitle = "定金支付";
                tvNewHYXQTwoStepsDingJin.setTextColor(activity.getResources().getColor(R.color.pay_price_text_orange_color));
                tvNewHYXQTwoStepsOther.setTextColor(activity.getResources().getColor(R.color.black));
                price = (String) tvNewHYXQTwoStepsDingJin.getText();
                if(price == null){
                    price = "";
                }
                getDingJinFromNet();
                break;
            case "weikuan":
                payTitle = "尾款支付";
                tvNewHYXQTwoStepsDingJin.setTextColor(activity.getResources().getColor(R.color.black));
                tvNewHYXQTwoStepsOther.setTextColor(activity.getResources().getColor(R.color.pay_price_text_orange_color));
                price = (String) tvNewHYXQTwoStepsOther.getText();
                if(price == null){
                    price = "";
                }
                getWeiKuanFromNet();
                break;
        }
    }

/*    private void getWeiKuanFromNet(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("baojiaid",baojiaId);
        paramMap.put("hyid",hyId);
    }*/

    private void getDingJinFromNet(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("hyid",hyId);
        paramMap.put("baojiaid",baojiaId);
  /*      Toast.makeText(activity,"baojiaid:"+baojiaId+" hyid:"+hyId,Toast.LENGTH_LONG).show();*/
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
                String msg = newDingJinPayBean.getMsg();
                if(!msg.isEmpty()){
                    Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
                }

                tvNewHYXQTwoStepsTotal.setText(newDingJinPayBean.getNr().getZjine());
                tvNewHYXQTwoStepsDingJin.setText(newDingJinPayBean.getNr().getDingjin()+"");
                tvNewHYXQTwoStepsOther.setText(newDingJinPayBean.getNr().getWeikuan()+"");
                orderInfo = newDingJinPayBean.getNr().getZfcs();
            }
        });
    }
    private void getWeiKuanFromNet(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("hyid",hyId);
        paramMap.put("baojiaid",baojiaId);
  /*      Toast.makeText(activity,"baojiaid:"+baojiaId+" hyid:"+hyId,Toast.LENGTH_LONG).show();*/
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getWeiKuanFromNet(paramMap, new Observer<NewWeiKuanPayBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewWeiKuanPayBean newWeiKuanPayBean) {
                String msg = newWeiKuanPayBean.getMsg();
                if(!msg.isEmpty()){
                    Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
                }

                tvNewHYXQTwoStepsTotal.setText(newWeiKuanPayBean.getNr().getZjine());
                tvNewHYXQTwoStepsDingJin.setText(newWeiKuanPayBean.getNr().getDingjin()+"");
                tvNewHYXQTwoStepsOther.setText(newWeiKuanPayBean.getNr().getWeikuan()+"");
                orderInfo = newWeiKuanPayBean.getNr().getZfcs();
            }
        });
    }





    /*支付宝支付*/
    public void zhiFuBaoPay(){
         String outTradeNo = getOutTradeNo();
/*Toast.makeText(activity, " onCompleted mPopView:"+goodsName+price, Toast.LENGTH_LONG).show();*/


        /*String passback_params = "{ \"dingdanid\" = \""+price"+\",\n \"lx\" = \"1\"\n}";*/



        String passback_params = "{\"dingdanid\":\""+baojiaId+"\",\"lx\":\"1\"}";
        try {
            passback_params = URLEncoder.encode(passback_params, "utf8");
            /*Toast.makeText(activity,passback_params,Toast.LENGTH_LONG).show();*/

            /*zhiFuBaoUtil.payV2(null, "定金支付", "0.1",outTradeNo,passback_params);*/
            if((orderInfo != null)&&(!orderInfo.isEmpty())) {
                zhiFuBaoUtil.payV3(null, orderInfo);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        zhiFuBaoUtil.setOnPaySuccessfulListener(new ZhiFuBaoUtil.OnPaySuccessfulListener() {
            @Override
            public void isSuccessful(boolean isSuccessful) {

            activity.finish();
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
