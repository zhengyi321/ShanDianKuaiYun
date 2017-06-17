package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.Main.MineFragment.PaySubmit.TwoStepPaySubmitActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.LookBaoJiaDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanDetailSelfController extends BaseController {
    private String hyId ,baojiaId;


    private String bLat,bLon,eLat,eLon;
    private String cheLat,cheLon,cheTouXiang;
    String status;


    @BindView(R.id.rly_new_self_hyxq_back)
    RelativeLayout rlyNewSelfHYXQBack;
    @OnClick(R.id.rly_new_self_hyxq_back)
    public void rlyNewSelfHYXQBackOnclick(){
        activity.finish();
    }


/*
    @BindView(R.id.rly_new_self_hyxq_bottom_tel)
    RelativeLayout rlyNewSelfHYXQBottomTel;*/
    @BindView(R.id.rly_new_self_hyxq_bottom_tgbj_submit)
    RelativeLayout rlyNewSelfHYXQBottomTGBJSubmit;
    @OnClick(R.id.rly_new_self_hyxq_bottom_tgbj_submit)
    public void rlyNewSelfHYXQBottomTGBJSubmitOnclick(){
        payByStatus();
    }
    @BindView(R.id.tv_new_self_hyxq_bottom_tgbj_submit)
    TextView tvNewSelfHYXQBottomTGBJSubmit;
    @BindView(R.id.lly_new_self_hyxq_bottom_item)
    LinearLayout llyNewSelfHYXQBottomItem;
/*    @BindView(R.id.rly_new_self_hyxq_bottom_message)
    RelativeLayout rlyNewSelfHYXQBottomMessage;*/

    @BindView(R.id.tv_new_self_hyxq_prov_city_area_b)
    TextView tvNewSelfHYXQProvCityAreaB;
    @BindView(R.id.tv_new_self_hyxq_addr_b)
    TextView tvNewSelfHYXQAddrB;

    @BindView(R.id.tv_new_self_hyxq_prov_city_area_e)
    TextView tvNewSelfHYXQProvCityAreaE;
    @BindView(R.id.tv_new_self_hyxq_addr_e)
    TextView tvNewSelfHYXQAddrE;

    @BindView(R.id.tv_new_self_hyxq_goodscontent)
    TextView tvNewSelfHYXQGoodsContent;
    @BindView(R.id.tv_new_self_hyxq_zxs)
    TextView tvNewSelfHYXQZXS;
    @BindView(R.id.tv_new_self_hyxq_remark)
    TextView tvNewSelfHYXQRemark;
    @BindView(R.id.rv_new_self_hyxq_img)
    RecyclerView rvNewSelfHYXQImg;
    @BindView(R.id.tv_new_self_hyxq_updatetime)
    TextView tvNewSelfHYXQUpdateTime;
    LookBaoJiaDialog lookBaoJiaDialog;
    @BindView(R.id.ib_new_self_hyxq_ckbj)
    ImageButton ibNewSelfHYXQCKBJ;
    @OnClick(R.id.ib_new_self_hyxq_ckbj)
    public void ibNewSelfHYXQCKBJOnclick(){
        lookBaoJiaDialog = new LookBaoJiaDialog(activity,hyId).Build.build(activity);
       /* Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
        showDialog();
    }
    public void showDialog() {
        if (lookBaoJiaDialog != null && !lookBaoJiaDialog.isShowing())
            lookBaoJiaDialog.show();
    }

    public void dissmissDialog() {
        if (lookBaoJiaDialog != null && lookBaoJiaDialog.isShowing())
            lookBaoJiaDialog.dismiss();
    }
    @BindView(R.id.rly_new_self_hyxq_mapline)
    RelativeLayout rlyNewSelfHYXQMapLine;
    @OnClick(R.id.rly_new_self_hyxq_mapline)
    public void rlyNewSelfHYXQMapLineOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        if(bLat == null){
            bLat = "";
        }
        if(bLon == null){
            bLon = "";
        }
        if(eLat == null){
            eLat = "";
        }
        if(eLon == null){
            eLon = "";
        }
        intent.putExtra("blat",bLat);
        intent.putExtra("blon",bLon);
        intent.putExtra("elat",eLat);
        intent.putExtra("elon",eLon);
        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("czTouXiang",cheTouXiang);
        activity.startActivity(intent);

    }

      /*  Intent intent = new Intent(this, NewFaBuHuoYuanActivity.class);*/
       /* Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);*/
      private  String tel;
    @BindView(R.id.rly_new_self_hyxq_bottom_message)
    RelativeLayout rlyNewSelfHYXQBottomMessage;
    @OnClick(R.id.rly_new_self_hyxq_bottom_message)
    public void rlyNewSelfHYXQBottomMessageOnclick(){
        if((tel == null)||(tel.isEmpty())){
            return;
        }
        doSendSMSTo(tel,"" );
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber
     * @param message
     */
    public void doSendSMSTo(String phoneNumber,String message){
       /* if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){*/
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
        intent.putExtra("sms_body", message);
        activity.startActivity(intent);
      /*  }*/
    }

    CallTelDialog callTelDialog;

    @BindView(R.id.rly_new_self_hyxq_bottom_tel)
    RelativeLayout rlyNewSelfHYXQBottomTel;
    @OnClick(R.id.rly_new_self_hyxq_bottom_tel)
    public void rlyNewSelfHYXQBottomTelOnclick(){

        if((tel == null)||(tel.isEmpty())){
            return;
        }
        callTelDialog = new CallTelDialog(activity,tel).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dimssTelDialog();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dimssTelDialog();
            }
        }).setCallBackListener(new CallTelDialog.DialogCallBackListener() {
            @Override
            public void callBack(String tel) {
                startCallTel(tel);
            }
        }).build(activity);
        showTelDialog();
    }
    private void startCallTel(String number) {
        /*PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        if((number != null)&&(phoneFormatCheckUtils.IsNumber(number))) {*/
        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));

        activity.startActivity(intent);
       /* }*/
    }

      /*  Intent intent = new Intent(this, NewFaBuHuoYuanActivity.class);*/
       /* Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);*/




    public void showTelDialog() {
        if (callTelDialog != null && !callTelDialog.isShowing())
            callTelDialog.show();
    }

    public void dimssTelDialog() {
        if (callTelDialog != null && callTelDialog.isShowing())
            callTelDialog.dismiss();
    }




    private NewHuoYuanDetailImgRVAdapter adapter;
    private List<String> imgList;
    public NewHuoYuanDetailSelfController(Activity activity1){
        activity = activity1;

        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getCyId();
        initRV();
        getNewCheYuanDetailFromNet();
    }




    private void getCyId(){
        hyId = activity.getIntent().getStringExtra("hyid");
        if(hyId == null){
            hyId = "";
        }
         status = activity.getIntent().getStringExtra("status");
        if((status != null)&&(!status.isEmpty())){

        }
        initstatus(status);
    }
    private void initstatus(String status){


        switch (status){
            case "0":
                llyNewSelfHYXQBottomItem.setVisibility(View.GONE);
                break;
            case "1":
                llyNewSelfHYXQBottomItem.setVisibility(View.VISIBLE);
                rlyNewSelfHYXQBottomTGBJSubmit.setBackgroundResource(R.mipmap.weibaojia_submit_orange_bg);
                tvNewSelfHYXQBottomTGBJSubmit.setText("支付定金");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(true);
                break;
            case "2":
                llyNewSelfHYXQBottomItem.setVisibility(View.VISIBLE);
                rlyNewSelfHYXQBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                tvNewSelfHYXQBottomTGBJSubmit.setText("待接货");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "3":
                llyNewSelfHYXQBottomItem.setVisibility(View.VISIBLE);
                rlyNewSelfHYXQBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                tvNewSelfHYXQBottomTGBJSubmit.setText("运输中");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "4":
                llyNewSelfHYXQBottomItem.setVisibility(View.VISIBLE);
                rlyNewSelfHYXQBottomTGBJSubmit.setBackgroundResource(R.mipmap.weibaojia_submit_orange_bg);
                tvNewSelfHYXQBottomTGBJSubmit.setText("支付尾款");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(true);
                break;
            case "5":
                llyNewSelfHYXQBottomItem.setVisibility(View.VISIBLE);
                rlyNewSelfHYXQBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                tvNewSelfHYXQBottomTGBJSubmit.setText("交易成功");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "notice":
                llyNewSelfHYXQBottomItem.setVisibility(View.GONE);
                lookBaoJiaDialog = new LookBaoJiaDialog(activity,hyId).Build.build(activity);
       /* Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
                showDialog();
                break;
            default:
                llyNewSelfHYXQBottomItem.setVisibility(View.GONE);
                break;
        }
    }
    private void payByStatus(){
        Intent intent;
        if(baojiaId == null){
            baojiaId = "";
        }
        switch (status) {

            case "1":
                intent = new Intent(activity, TwoStepPaySubmitActivity.class);
               /* System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());*/
                intent.putExtra("hyId",hyId);
                intent.putExtra("baojiaId",baojiaId);
                intent.putExtra("status","dingjin");

                activity.startActivity(intent);
                activity.finish();

                break;
            case "4":
                intent = new Intent(activity, TwoStepPaySubmitActivity.class);
                intent.putExtra("hyId",hyId);
                intent.putExtra("baojiaId",baojiaId);
                intent.putExtra("status","weikuan");
                activity.startActivity(intent);
                activity.finish();
                break;
        }
    }

    private void initRV(){
        imgList = new ArrayList<>();
        adapter = new NewHuoYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewSelfHYXQImg.setAdapter(adapter);
        rvNewSelfHYXQImg.setLayoutManager(linearLayoutManager);
    }
    private void getNewCheYuanDetailFromNet(){
        NewCheHuoListNetWork cheHuoListNetWork = new NewCheHuoListNetWork();
      /*  Toast.makeText(activity,"hyid"+hyId,Toast.LENGTH_LONG).show();*/
        cheHuoListNetWork.getHuoYuanDetailFromNet(hyId, new Observer<NewHuoYuanDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanDetailBean newHuoYuanDetailBean) {
                initDetail(newHuoYuanDetailBean);
            }
        });
    }


    private void initDetail(NewHuoYuanDetailBean newHuoYuanDetailBean){
        tvNewSelfHYXQProvCityAreaB.setText(newHuoYuanDetailBean.getNr().getCfsheng()+"-"+newHuoYuanDetailBean.getNr().getCfshi()+"-"+newHuoYuanDetailBean.getNr().getCfqu());
        tvNewSelfHYXQProvCityAreaE.setText(newHuoYuanDetailBean.getNr().getDasheng()+"-"+newHuoYuanDetailBean.getNr().getDashi()+"-"+newHuoYuanDetailBean.getNr().getDaqu());
        tvNewSelfHYXQAddrB.setText(newHuoYuanDetailBean.getNr().getCfdizhi());
        tvNewSelfHYXQAddrE.setText(newHuoYuanDetailBean.getNr().getDadizhi());
        tvNewSelfHYXQGoodsContent.setText(newHuoYuanDetailBean.getNr().getGood_name());
        tvNewSelfHYXQZXS.setText(newHuoYuanDetailBean.getNr().getNum()+"箱");
        tvNewSelfHYXQRemark.setText(newHuoYuanDetailBean.getNr().getContext());
        tvNewSelfHYXQUpdateTime.setText(newHuoYuanDetailBean.getNr().getTime());
        baojiaId = newHuoYuanDetailBean.getNr().getBaojiaid();
    /*    Toast.makeText(activity,"imgList:"+newHuoYuanDetailBean.getNr().getImgtu().size(),Toast.LENGTH_LONG).show();*/
        adapter.setAdapter(newHuoYuanDetailBean.getNr().getImgtu());
        bLat = newHuoYuanDetailBean.getNr().getCflat();
        bLon = newHuoYuanDetailBean.getNr().getCflng();
        eLat = newHuoYuanDetailBean.getNr().getDalat();
        eLon = newHuoYuanDetailBean.getNr().getDalng();
        tel = newHuoYuanDetailBean.getNr().getIphone();
        cheLat = newHuoYuanDetailBean.getNr().getCzlat();
        cheLon = newHuoYuanDetailBean.getNr().getCzlng();
        cheTouXiang = newHuoYuanDetailBean.getNr().getTouxiang();
    }

}
