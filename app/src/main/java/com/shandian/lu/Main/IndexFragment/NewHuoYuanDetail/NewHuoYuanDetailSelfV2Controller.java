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

import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.example.mynewslayoutlib.Bean.NewAdsBean;
import com.example.mynewslayoutlib.Bean.NewBaoJiaListBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailSelfBean;
import com.example.mynewslayoutlib.Utils.OpenLocalMapUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.Main.IndexFragment.NewAdsDetail.NewAdsDetailActivity;
import com.shandian.lu.Main.MineFragment.PaySubmit.TwoStepPaySubmitActivity;
import com.shandian.lu.NetWork.AdsNetWork;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.LookBaoJiaDialog;
import com.shandian.lu.Widget.Dialog.NewEditBaoJiaDialog;
import com.shandian.lu.Widget.Dialog.NewHuoZhuEditBaoJiaDialog;
import com.shandian.lu.Widget.Dialog.NewMapDaoHangDialog;
import com.shandian.lu.Widget.Dialog.NewQueryDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.shandian.lu.Widget.Dialog.NewMapDaoHangDialog.DialogCallBackListener;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanDetailSelfV2Controller extends BaseController {
    private String hyId="", baojiaId="";
    private String bAddr="",eAddr="",bCity="";
    private String currentLat="",currentLon="",currentAddr="";
    private boolean isOpened = false;
    private  String APP_NAME = "ShanDianKuaiYun";
    private String bLat, bLon, eLat, eLon;
    private String cheLat, cheLon, cheTouXiang;
    private String czid="";
    String status;
    NewHuoZhuEditBaoJiaDialog newHuoZhuEditBaoJiaDialog;

    @BindView(R.id.rly_new_self_hyxq_back)
    RelativeLayout rlyNewSelfHYXQBack;

    @OnClick(R.id.rly_new_self_hyxq_back)
    public void rlyNewSelfHYXQBackOnclick() {
        activity.finish();
    }

    String adsUrl = "";
    @BindView(R.id.ib_new_self_hyxq_ads)
    ImageButton ibNewSelfHYXQAds;

    @OnClick(R.id.ib_new_self_hyxq_ads)
    public void ibNewSelfHYXQAdsOnclick() {
        Intent intent = new Intent(activity, NewAdsDetailActivity.class);
        intent.putExtra("url",adsUrl);
        activity.startActivity(intent);
    }

/*
    @BindView(R.id.rly_new_self_hyxq_bottom_tel)
    RelativeLayout rlyNewSelfHYXQBottomTel;*/

    @BindView(R.id.lly_new_self_hyxq_bottom_type1)
    LinearLayout llyNewSelfHYXQBottomType1;
    @BindView(R.id.lly_new_self_hyxq_bottom_type2)
    LinearLayout llyNewSelfHYXQBottomType2;

    @BindView(R.id.rly_new_self_hyxq_bottom_tgbj_submit)
    RelativeLayout rlyNewSelfHYXQBottomTGBJSubmit;
    @OnClick(R.id.rly_new_self_hyxq_bottom_tgbj_submit)
    public void rlyNewSelfHYXQBottomTGBJSubmitOnclick() {
        payByStatus();
    }

    @BindView(R.id.tv_new_self_hyxq_bottom_tgbj_submit)
    TextView tvNewSelfHYXQBottomTGBJSubmit;
    @OnClick(R.id.tv_new_self_hyxq_bottom_tgbj_submit)
    public void tvNewSelfHYXQBottomTGBJSubmitOnclick(){
        payByStatus();
    }
    @BindView(R.id.lly_new_self_hyxq_bottom_item)
    LinearLayout llyNewSelfHYXQBottomItem;
/*    @BindView(R.id.rly_new_self_hyxq_bottom_message)
    RelativeLayout rlyNewSelfHYXQBottomMessage;*/

    @BindView(R.id.tv_new_self_hyxq_city_area_b)
    TextView tvNewSelfHYXQCityAreaB;
    @OnClick(R.id.tv_new_self_hyxq_city_area_b)
    public void tvNewSelfHYXQCityAreaBOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看起点位置
        intent.putExtra("czlat",bLat);
        intent.putExtra("czlon",bLon);
        intent.putExtra("baddr",bAddr);
        intent.putExtra("czid",czid);
        intent.putExtra("title","qdwz");
        activity.startActivity(intent);
    }


    @BindView(R.id.tv_new_self_hyxq_city_area_e)
    TextView tvNewSelfHYXQCityAreaE;
    @OnClick(R.id.tv_new_self_hyxq_city_area_e)
    public void tvNewSelfHYXQCityAreaEOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看终点位置
        intent.putExtra("czlat",eLat);
        intent.putExtra("czlon",eLon);
        intent.putExtra("eaddr",eAddr);
        intent.putExtra("czid",czid);
        intent.putExtra("title","zdwz");
        activity.startActivity(intent);
    }
    @BindView(R.id.tv_new_self_hyxq_fbtime)
    TextView tvNewSelfHYXQFBTime;
    LookBaoJiaDialog lookBaoJiaDialog;
    @BindView(R.id.rly_new_self_hyxq_sjbj)
    RelativeLayout rlyNewSelfHYXQSJBJ;

    @OnClick(R.id.rly_new_self_hyxq_sjbj)
    public void rlyNewSelfHYXQSJBJOnclick() {
        lookBaoJiaDialog = new LookBaoJiaDialog(activity, hyId, baojiaId).Build.build(activity);
       /* Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
        showDialog();
    }


    @BindView(R.id.lly_new_self_hyxq_sjbj)
    LinearLayout llyNewSelfHYXQSJBJ;
    @BindView(R.id.tv_new_self_hyxq_sjbj)
    TextView tvNewSelfHYXQSJBJ;
    @BindView(R.id.tv_new_self_hyxq_sjbj_price)
    TextView tvNewSelfHYXQSJBJPrice;
    @BindView(R.id.tv_new_self_hyxq_sjbj_status)
    TextView tvNewSelfHYXQSJBJStatus;
    @BindView(R.id.tv_new_self_hyxq_hzjg)
    TextView tvNewSelfHYXQHZJG;
    @BindView(R.id.tv_new_self_hyxq_cjj)
    TextView tvNewSelfHYXQCJJ;
    @BindView(R.id.tv_new_self_hyxq_car_useing_time)
    TextView tvNewSelfHYXQCarUseingTime;
    @BindView(R.id.tv_new_self_hyxq_name)
    TextView tvNewSelfHYXQName;
    @BindView(R.id.tv_new_self_hyxq_tiji)
    TextView tvNewSelfHYXQTiJi;
    @BindView(R.id.tv_new_self_hyxq_goodsnums)
    TextView tvNewSelfHYXQGoodNums;
    @BindView(R.id.tv_new_self_hyxq_weight)
    TextView tvNewSelfHYXQWeight;
    @BindView(R.id.tv_new_self_hyxq_mile)
    TextView tvNewSelfHYXQMile;
    @BindView(R.id.rv_new_self_hyxq_img)
    RecyclerView rvNewSelfHYXQImg;
    @BindView(R.id.tv_new_self_hyxq_remark)
    TextView tvNewSelfHYXQRemark;


    NewMapDaoHangDialog newMapDaoHangDialog;
    @BindView(R.id.rly_new_self_hyxq_dh_map)
    RelativeLayout rlyNewSelfHYXQDHMap;
    @OnClick(R.id.rly_new_self_hyxq_dh_map)
    public void rlyNewSelfHYXQDHMapOnclick() {
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String curLat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        String curLon = xcCacheManager.readCache(xcCacheSaveName.currentlon);
        String curAddr = xcCacheManager.readCache(xcCacheSaveName.currentLocAddrStr);

        if((curAddr == null)){
            curAddr= "";
        }

        if(bAddr == null){
            bAddr = "";
        }
        if(eAddr == null){
            eAddr = "";
        }
        if(bCity == null){
            bCity = "";
        }
        double bbLat ,bbLon,eeLat,eeLon,ccLat,ccLon;
        if((bLat == null)||(bLat.isEmpty())){
           bLat= "0.0";
        }
        bbLat = Double.parseDouble(bLat);
        if((bLon == null)||(bLon.isEmpty())){
            bLon= "0.0";
        }
        bbLon = Double.parseDouble(bLon);
        if((eLat == null)||(eLat.isEmpty())){
            eLat= "0.0";
        }
        eeLat = Double.parseDouble(eLat);
        if((eLon == null)||(eLon.isEmpty())){
            eLon= "0.0";
        }
        eeLon = Double.parseDouble(eLon);

        if((curLat == null)||(curLat.isEmpty())){
            curLat= "0.0";
        }
        ccLat = Double.parseDouble(curLat);
        if((curLon == null)||(curLon.isEmpty())){
            curLon= "0.0";
        }
        ccLon = Double.parseDouble(curLon);
        chooseOpenMap(ccLat,ccLon,curAddr,bbLat,bbLon,bAddr,bCity);
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
        intent.putExtra("baddr",bAddr);
        intent.putExtra("eaddr",eAddr);
        intent.putExtra("czid",czid);
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
       /* if((tel == null)||(tel.isEmpty())){
            return;
        }
        doSendSMSTo(tel,"" );*/
       Toast.makeText(activity,"不能和自己聊天",Toast.LENGTH_SHORT).show();
    }
    @BindView(R.id.rly_new_self_hyxq_bottom_message2)
    RelativeLayout rlyNewSelfHYXQBottomMessage2;
    @OnClick(R.id.rly_new_self_hyxq_bottom_message2)
    public void rlyNewSelfHYXQBottomMessage2Onclick(){
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
    @BindView(R.id.rly_new_self_hyxq_bottom_tel2)
    RelativeLayout rlyNewSelfHYXQBottomTel2;
    @OnClick(R.id.rly_new_self_hyxq_bottom_tel2)
    public void rlyNewSelfHYXQBottomTel2Onclick(){

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
    private ArrayList<String> imgList;
    public NewHuoYuanDetailSelfV2Controller(Activity activity1){
        activity = activity1;

        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);

        initRV();
        getCyId();
        initSJBJ();
        getNewCheYuanDetailFromNet();
        getAdsFromNet();
        initstatus();
    }




    private void getCyId(){
        hyId = activity.getIntent().getStringExtra("hyid");
        if(hyId == null){
            hyId = "";
        }

         status = activity.getIntent().getStringExtra("status");
    /*    Toast.makeText(activity,"status:"+status,Toast.LENGTH_LONG).show();*/
      /*  if((status != null)&&(!status.isEmpty())){

        }*/

    }
    private void initstatus(){

/*        Toast.makeText(activity,"status"+status,Toast.LENGTH_LONG).show();*/
        switch (status){
            case "0":


               /* llyNewSelfHYXQBottomItem.setVisibility(View.GONE);*/
               /* String czbj = newHuoYuanDetailBean.getNr().getCzbj();
                rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj);
                llyNewSelfHYXQSJBJ.setVisibility(View.GONE);
                tvNewSelfHYXQSJBJStatus.setVisibility(View.VISIBLE);
                tvNewSelfHYXQSJBJStatus.setText(czbj);*/
             /*  Toast.makeText(activity,"修改价格",Toast.LENGTH_LONG).show();*/
                tvNewSelfHYXQBottomTGBJSubmit.setText("修改价格");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(true);
               /* rlyNewSelfHYXQSJBJ.setClickable(true);*/

                break;
            case "1":
                rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj_success);
                llyNewSelfHYXQSJBJ.setVisibility(View.GONE);
                tvNewSelfHYXQSJBJStatus.setVisibility(View.VISIBLE);
                tvNewSelfHYXQSJBJStatus.setText("交易成功");
                tvNewSelfHYXQSJBJStatus.setTextColor(activity.getResources().getColor(R.color.white));
                rlyNewSelfHYXQSJBJ.setClickable(false);
                /*rlyNewSelfHYXQBottomTGBJSubmit.setBackgroundResource(R.mipmap.weibaojia_submit_orange_bg);*/
                tvNewSelfHYXQBottomTGBJSubmit.setText("支付定金");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(true);
                break;
            case "2":
                rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj_success);
                llyNewSelfHYXQSJBJ.setVisibility(View.GONE);
                tvNewSelfHYXQSJBJStatus.setVisibility(View.VISIBLE);
                tvNewSelfHYXQSJBJStatus.setText("交易成功");
                tvNewSelfHYXQSJBJStatus.setTextColor(activity.getResources().getColor(R.color.white));
                rlyNewSelfHYXQSJBJ.setClickable(false);
                llyNewSelfHYXQBottomType1.setVisibility(View.VISIBLE);
                llyNewSelfHYXQBottomType2.setVisibility(View.GONE);
                tvNewSelfHYXQBottomTGBJSubmit.setText("待接货");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "3":
                rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj_success);
                llyNewSelfHYXQSJBJ.setVisibility(View.GONE);
                tvNewSelfHYXQSJBJStatus.setVisibility(View.VISIBLE);
                tvNewSelfHYXQSJBJStatus.setText("交易成功");
                tvNewSelfHYXQSJBJStatus.setTextColor(activity.getResources().getColor(R.color.white));
                rlyNewSelfHYXQSJBJ.setClickable(false);
                llyNewSelfHYXQBottomType1.setVisibility(View.VISIBLE);
                llyNewSelfHYXQBottomType2.setVisibility(View.GONE);
                tvNewSelfHYXQBottomTGBJSubmit.setText("运输中");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "4":
                rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj_success);
                llyNewSelfHYXQSJBJ.setVisibility(View.GONE);
                tvNewSelfHYXQSJBJStatus.setVisibility(View.VISIBLE);
                tvNewSelfHYXQSJBJStatus.setText("交易成功");
                tvNewSelfHYXQSJBJStatus.setTextColor(activity.getResources().getColor(R.color.white));
                rlyNewSelfHYXQSJBJ.setClickable(false);
                llyNewSelfHYXQBottomType1.setVisibility(View.VISIBLE);
                llyNewSelfHYXQBottomType2.setVisibility(View.GONE);
                tvNewSelfHYXQBottomTGBJSubmit.setText("支付尾款");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(true);
                break;
            case "5":
                rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj_success);
                llyNewSelfHYXQSJBJ.setVisibility(View.GONE);
                tvNewSelfHYXQSJBJStatus.setVisibility(View.VISIBLE);
                tvNewSelfHYXQSJBJStatus.setTextColor(activity.getResources().getColor(R.color.white));
                rlyNewSelfHYXQSJBJ.setClickable(false);
                llyNewSelfHYXQBottomType1.setVisibility(View.VISIBLE);
                llyNewSelfHYXQBottomType2.setVisibility(View.GONE);
                tvNewSelfHYXQBottomTGBJSubmit.setText("交易成功");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "notice":
                tvNewSelfHYXQBottomTGBJSubmit.setText("修改价格");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(true);
           /*     llyNewSelfHYXQBottomItem.setVisibility(View.GONE);*/
                lookBaoJiaDialog = new LookBaoJiaDialog(activity,hyId,baojiaId).Build.build(activity);
       /* Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
                showDialog();
                break;
            default:
                tvNewSelfHYXQBottomTGBJSubmit.setText("修改价格");
                rlyNewSelfHYXQBottomTGBJSubmit.setClickable(true);
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

            case "0":

                newHuoZhuEditBaoJiaDialog = new NewHuoZhuEditBaoJiaDialog(activity,hyId,status).Build.build(activity);

                showHuoZhuDialog();


                break;

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
    public void showHuoZhuDialog() {
        if (newHuoZhuEditBaoJiaDialog != null && !newHuoZhuEditBaoJiaDialog.isShowing())
            newHuoZhuEditBaoJiaDialog.show();
    }

    public void dissmissHuoZhuDialog() {
        if (newHuoZhuEditBaoJiaDialog != null && newHuoZhuEditBaoJiaDialog.isShowing())
            newHuoZhuEditBaoJiaDialog.dismiss();
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
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)){
            loginId="";
        }


        NewCheHuoListNetWork cheHuoListNetWork = new NewCheHuoListNetWork();
      /*  Toast.makeText(activity,"hyid"+hyId,Toast.LENGTH_LONG).show();*/
        cheHuoListNetWork.getHuoYuanDetailSelfV2FromNet(hyId, loginId, new Observer<NewHuoYuanDetailSelfBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanDetailSelfBean newHuoYuanDetailSelfBean) {
                initDetail(newHuoYuanDetailSelfBean);
            }
        });
    }


    private void initDetail(NewHuoYuanDetailSelfBean newHuoYuanDetailBean){
        tvNewSelfHYXQCityAreaB.setText(newHuoYuanDetailBean.getNr().getCfshi()+newHuoYuanDetailBean.getNr().getCfqu());
        tvNewSelfHYXQCityAreaE.setText(newHuoYuanDetailBean.getNr().getDashi()+newHuoYuanDetailBean.getNr().getDaqu());
        tvNewSelfHYXQFBTime.setText(newHuoYuanDetailBean.getNr().getTime());




        tvNewSelfHYXQHZJG.setText(newHuoYuanDetailBean.getNr().getHyjiage());
        tvNewSelfHYXQCJJ.setText(newHuoYuanDetailBean.getNr().getBjnr());
        tvNewSelfHYXQCarUseingTime.setText(newHuoYuanDetailBean.getNr().getYcsj());
        tvNewSelfHYXQName.setText(newHuoYuanDetailBean.getNr().getHuowulx());
        tvNewSelfHYXQTiJi.setText(newHuoYuanDetailBean.getNr().getTiji());
        tvNewSelfHYXQGoodNums.setText(newHuoYuanDetailBean.getNr().getXiangshu());
        tvNewSelfHYXQWeight.setText(newHuoYuanDetailBean.getNr().getWeight());
        tvNewSelfHYXQMile.setText(newHuoYuanDetailBean.getNr().getJuli());
        baojiaId = newHuoYuanDetailBean.getNr().getBaojiaid();
       /* ImageLoader.getInstance().displayImage(newHuoYuanDetailBean.getNr().getGg().getImg(),ibNewSelfHYXQAds, ImageLoaderUtils.options1);*/
      /*  imgUrl = newHuoYuanDetailBean.getNr().getGg().getUrl();*/
      /*  Toast.makeText(activity,"imgList:"+newHuoYuanDetailBean.getNr().getImgtu().size(),Toast.LENGTH_LONG).show();*/

        adapter.setAdapter(newHuoYuanDetailBean.getNr().getImgtu());
        bLat = newHuoYuanDetailBean.getNr().getCflat();
        bLon = newHuoYuanDetailBean.getNr().getCflng();
        eLat = newHuoYuanDetailBean.getNr().getDalat();
        eLon = newHuoYuanDetailBean.getNr().getDalng();
        tel = newHuoYuanDetailBean.getNr().getIphone();
        cheLat = (String)newHuoYuanDetailBean.getNr().getCzlat();
        cheLon = (String) newHuoYuanDetailBean.getNr().getCzlng();
        cheTouXiang = newHuoYuanDetailBean.getNr().getTouxiang();
        bAddr = newHuoYuanDetailBean.getNr().getCfdizhi();
        eAddr = newHuoYuanDetailBean.getNr().getDadizhi();
        bCity = newHuoYuanDetailBean.getNr().getCfshi();
        czid = newHuoYuanDetailBean.getNr().getLogin_id();
    }
    private void getAdsFromNet(){
        AdsNetWork adsNetWork = new AdsNetWork();
        adsNetWork.getAdsFromNet("2", new Observer<NewAdsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewAdsBean newAdsBean) {
                ImageLoader.getInstance().displayImage(newAdsBean.getNr().getImg(),ibNewSelfHYXQAds, ImageLoaderUtils.options1);
                adsUrl = newAdsBean.getNr().getUrl();
            }
        });
    }
    private void initSJBJ(){

        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getBaoJiaListFromNet(hyId, new Observer<NewBaoJiaListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewBaoJiaListBean newBaoJiaListBean) {
                if(newBaoJiaListBean.getStatus().equals("0")){
                    int size =newBaoJiaListBean.getNr().getList().size();
                    if(status.equals("0")) {
                        if (size == 0) {
                            rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj);
                            llyNewSelfHYXQSJBJ.setVisibility(View.GONE);
                            tvNewSelfHYXQSJBJStatus.setVisibility(View.VISIBLE);
                            tvNewSelfHYXQSJBJStatus.setText("暂无报价");
                        } else {
                            rlyNewSelfHYXQSJBJ.setBackgroundResource(R.mipmap.sjbj_success);
                            llyNewSelfHYXQSJBJ.setVisibility(View.VISIBLE);
                            tvNewSelfHYXQSJBJStatus.setVisibility(View.GONE);
                            tvNewSelfHYXQSJBJ.setTextColor(activity.getResources().getColor(R.color.white));
                            tvNewSelfHYXQSJBJPrice.setTextColor(activity.getResources().getColor(R.color.white));
                            tvNewSelfHYXQSJBJPrice.setText("点击查看(" + size + "条)");
                            rlyNewSelfHYXQSJBJ.setClickable(true);
                        }
                    }

                }
            }
        });
    }
















/*
    public void openLocalMap(View view) {
        openLocalMap(START_LATLON[0], START_LATLON[1], SNAME,  CITY);
    }

    public void openBaiduMap(View view) {
        openBaiduMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME, CITY);
    }

    public void openWebBaiduMap(View view) {
        openWebMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME, CITY);
    }

    public void openGaodeMap(View view) {
        openGaoDeMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME);
    }*/

    /**
     *https://github.com/zhengyi321/OPenLocalMapDemo
     * @param slat
     * @param slon
     * @param address 当前位置
     * @param city 所在城市
     */
    private void openLocalMap(double slat, double slon, String address,double elat,double elon,String eAddr ,String city) {
        OpenLocalMapUtil openLocalMapUtil= new OpenLocalMapUtil();
        if(openLocalMapUtil.isBaiduMapInstalled() && openLocalMapUtil.isGdMapInstalled()){
            chooseOpenMap(slat, slon, address,elat,elon,eAddr, city);
        } else {
            openBaiduMap(slat, slon, address, elat, elon, eAddr, city);

            if(!isOpened){
                openGaoDeMap(slat, slon, address, elat, elon, eAddr);
            }

            if(!isOpened){
                //打开网页地图
                openWebMap(slat, slon, address, elat, elon, eAddr, city);
            }
        }

    }

    /**
     * 如果两个地图都安装，提示选择
     * @param slat
     * @param slon
     * @param address
     * @param city
     */
    private void chooseOpenMap(final double slat, final double slon, final String address,final double elat, final double elon, final String eAddr, final String city) {
        newMapDaoHangDialog = new NewMapDaoHangDialog(activity).Build.setCallBackListener(new DialogCallBackListener() {
            @Override
            public void callBack(boolean isBaidu) {
                if (isBaidu) {
                    openBaiduMap(slat, slon, address, elat, elon, eAddr, city);
                } else {
                    openGaoDeMap(slat, slon, address,elat, elon, eAddr);
                }
           /*     dissmissMapDaoHangDialog();*/
            }
        }).build(activity);
        showMapDaoHangDialog();

    }




    public void showMapDaoHangDialog() {
        if (newMapDaoHangDialog != null && !newMapDaoHangDialog.isShowing())
            newMapDaoHangDialog.show();
    }

    public void dissmissMapDaoHangDialog() {
        if (newMapDaoHangDialog != null && newMapDaoHangDialog.isShowing()){
            newMapDaoHangDialog.dismiss();
        }
    }
    /**
     *  打开百度地图
     */
    private void openBaiduMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city) {
        OpenLocalMapUtil openLocalMapUtil= new OpenLocalMapUtil();
        if(openLocalMapUtil.isBaiduMapInstalled()){
            try {
             /*   Toast.makeText(activity,"this is baidu",Toast.LENGTH_LONG).show();*/
                String uri = openLocalMapUtil.getBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                        String.valueOf(dlat), String.valueOf(dlon), dname, city, APP_NAME);
                Intent intent = Intent.parseUri(uri, 0);
                activity.startActivity(intent); //启动调用

                isOpened = true;
            } catch (Exception e) {
                isOpened = false;
                e.printStackTrace();
            }
        } else{
            Toast.makeText(activity,"您未安装百度地图应用,请下载!",Toast.LENGTH_LONG).show();
            isOpened = false;
        }
    }

    /**
     * 打开高德地图
     */
    private void openGaoDeMap(double slat, double slon, String sname, double dlat, double dlon, String dname) {
        OpenLocalMapUtil openLocalMapUtil= new OpenLocalMapUtil();
        if(openLocalMapUtil.isGdMapInstalled()){
            try {
                CoordinateConverter converter= new CoordinateConverter(activity);
                converter.from(CoordinateConverter.CoordType.BAIDU);
                DPoint sPoint = null, dPoint = null;
                try {
                    converter.coord(new DPoint(slat, slon));
                    sPoint = converter.convert();
                    converter.coord(new DPoint(dlat, dlon));
                    dPoint = converter.convert();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (sPoint != null && dPoint != null) {
                    String uri = openLocalMapUtil.getGdMapUri(APP_NAME, String.valueOf(sPoint.getLatitude()), String.valueOf(sPoint.getLongitude()),
                            sname, String.valueOf(dPoint.getLatitude()), String.valueOf(dPoint.getLongitude()), dname);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.autonavi.minimap");
                    intent.setData(Uri.parse(uri));
                    activity.startActivity(intent); //启动调用

                    isOpened = true;
                }
            } catch (Exception e) {
                isOpened = false;
                e.printStackTrace();
            }
        } else{
            Toast.makeText(activity,"您未安装高德地图应用,请下载!",Toast.LENGTH_LONG).show();
            isOpened = false;
        }
    }

    /**
     * 打开浏览器进行百度地图导航
     */
    private void openWebMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city){
        OpenLocalMapUtil openLocalMapUtil= new OpenLocalMapUtil();
        Uri mapUri = Uri.parse(openLocalMapUtil.getWebBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                String.valueOf(dlat), String.valueOf(dlon),
                dname, city, APP_NAME));
        Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
        activity.startActivity(loction);
    }






















}
