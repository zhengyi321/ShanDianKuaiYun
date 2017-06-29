package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

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

import com.example.mynewslayoutlib.Bean.NewAdsBean;
import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.Main.IndexFragment.NewAdsDetail.NewAdsDetailActivity;
import com.shandian.lu.NetWork.AdsNetWork;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewCheYuanDetailOtherV2Controller extends BaseController {

    private String bLat,bLon,eLat,eLon,cheLat,cheLon,cheTouXiang;
    private String adsUrl = "";
    private String bAddr = "",eAddr="";
    @BindView(R.id.rly_new_other_cyxq_back)
    RelativeLayout rlyNewOtherCYXQBack;
    @OnClick(R.id.rly_new_other_cyxq_back)
    public void rlyNewOtherCYXQBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.tv_new_other_cyxq_city_area_b)
    TextView tvNewOtherCYXQCityAreaB;
    @OnClick(R.id.tv_new_other_cyxq_city_area_b)
    public void tvNewOtherCYXQCityAreaBOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看起点位置
        intent.putExtra("czlat",bLat);
        intent.putExtra("czlon",bLon);
        intent.putExtra("baddr",bAddr);
        intent.putExtra("title","qdwz");
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_new_other_cyxq_city_area_e)
    TextView tvNewOtherCYXQCityAreaE;
    @OnClick(R.id.tv_new_other_cyxq_city_area_e)
    public void tvNewOtherCYXQCityAreaEOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看终点位置
        intent.putExtra("czlat",eLat);
        intent.putExtra("czlon",eLon);
        intent.putExtra("eaddr",eAddr);
        intent.putExtra("title","zdwz");
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_new_other_cyxq_fbtime)
    TextView tvNewOtherCYXQFBTime;

    @BindView(R.id.rly_new_other_cyxq_sjbj)
    RelativeLayout rlyNewOtherCYXQSJBJ;

    @BindView(R.id.lly_new_other_cyxq_sjbj)
    LinearLayout llyNewOtherCYXQSJBJ;

    @BindView(R.id.tv_new_other_cyxq_sjbj)
    TextView tvNewOtherCYXQSJBJ;
    @BindView(R.id.tv_new_other_cyxq_sjbj_price)
    TextView tvNewOtherCYXQSJBJPrice;
    @BindView(R.id.tv_new_other_cyxq_cjj)
    TextView tvNewOtherCYXQCJJ;
    @BindView(R.id.tv_new_other_cyxq_hyckj)
    TextView tvNewOtherCYXQHYCKJ;

    @BindView(R.id.ib_new_other_cyxq_ads)
    ImageButton ibNewOtherCYXQAds;
    @OnClick(R.id.ib_new_other_cyxq_ads)
    public void ibNewOtherCYXQAdsOnclick(){
        Intent intent = new Intent(activity, NewAdsDetailActivity.class);
        intent.putExtra("url",adsUrl);
        activity.startActivity(intent);
    }
    @BindView(R.id.tv_new_other_cyxq_car_useing_time)
    TextView tvnewOtherCYXQCarUseingTime;
    @BindView(R.id.tv_new_other_cyxq_carlangth)
    TextView tvNewOtherCYXQCarLangth;
    @BindView(R.id.tv_new_other_cyxq_cartype)
    TextView tvNewOtherCYXQCarType;
    @BindView(R.id.tv_new_other_cyxq_mile)
    TextView tvNewOtherCYXQMile;
    private String iphone;
    CallTelDialog callTelDialog;
    @BindView(R.id.rly_new_other_cyxq_bottom_tel)
    RelativeLayout rlyNewOtherCYXQBottomTel;
    @OnClick(R.id.rly_new_other_cyxq_bottom_tel)
    public void rlyNewOtherCYXQBottomTelOnclick(){
        if(( iphone== null)||(iphone.isEmpty())){
            return;
        }
        callTelDialog = new CallTelDialog(activity,iphone).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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

    public void showTelDialog() {
        if (callTelDialog != null && !callTelDialog.isShowing())
            callTelDialog.show();
    }

    public void dimssTelDialog() {
        if (callTelDialog != null && callTelDialog.isShowing())
            callTelDialog.dismiss();
    }



    @BindView(R.id.tv_new_other_cyxq_remark)
    TextView tvNewOtherCYXQRemark;
    @BindView(R.id.rv_new_other_cyxq_img)
    RecyclerView rvNewOtherCYXQImg;

    @BindView(R.id.tv_new_other_cyxq_bottom_tgbj_submit)
    TextView tvNewOtherCYXQBottomTGBJSubmit;
    @BindView(R.id.lly_new_other_cyxq_bottom)
    LinearLayout llyNewOtherCYXQBottom;

    @BindView(R.id.rly_new_other_cyxq_hc_loc)
    RelativeLayout rlyNewOtherCYXQHCLoc;
    @OnClick(R.id.rly_new_other_cyxq_hc_loc)
    public void rlyNewOtherCYXQHCLocOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("czTouXiang",cheTouXiang);
        intent.putExtra("title","hcdw");
        activity.startActivity(intent);
    }


    @BindView(R.id.rly_new_other_cyxq_mapline)
    RelativeLayout rlyNewOtherCYXQMapLine;
    @OnClick(R.id.rly_new_other_cyxq_mapline)
    public void rlyNewOtherCYXQMapLineOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        if(bLat == null){
            bLat = "0.0";
        }
        if(bLon == null){
            bLon = "0.0";
        }
        if(eLat == null){
            eLat = "0.0";
        }
        if(eLon == null){
            eLon = "0.0";
        }
        intent.putExtra("blat",bLat);
        intent.putExtra("blon",bLon);
        intent.putExtra("elat",eLat);
        intent.putExtra("elon",eLon);
        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("baddr",bAddr);
        intent.putExtra("eaddr",eAddr);
        intent.putExtra("czTouXiang",cheTouXiang);
        activity.startActivity(intent);
    }
    private String cyId ;
    private NewCheYuanDetailImgRVAdapter adapter;
    private List<String> imgList;
    public NewCheYuanDetailOtherV2Controller(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getCyId();
        getAdsFromNet();
        initRV();
        getNewCheYuanDetailFromNet();
    }

    private void getCyId(){
        cyId = activity.getIntent().getStringExtra("cyid");
        if(cyId == null){
            cyId = "";
        }
       /* Toast.makeText(activity,"cyId:"+cyId,Toast.LENGTH_LONG).show();*/
    }
    private void initRV(){
        imgList = new ArrayList<>();
        adapter = new NewCheYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewOtherCYXQImg.setAdapter(adapter);
        rvNewOtherCYXQImg.setLayoutManager(linearLayoutManager);
    }
    private void getNewCheYuanDetailFromNet(){
        NewCheHuoListNetWork cheHuoListNetWork = new NewCheHuoListNetWork();
        cheHuoListNetWork.getCheYuanDetailFromNet(cyId, new Observer<NewCheYuanDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewCheYuanDetailBean newCheYuanDetailBean) {
                initDetail(newCheYuanDetailBean);
            }
        });
    }


    private void initDetail(NewCheYuanDetailBean newCheYuanDetailBean){
        tvNewOtherCYXQCityAreaB.setText(newCheYuanDetailBean.getNr().getCfshi()+newCheYuanDetailBean.getNr().getCfqu());
        tvNewOtherCYXQCityAreaE.setText(newCheYuanDetailBean.getNr().getDashi()+newCheYuanDetailBean.getNr().getDaqu());
        tvNewOtherCYXQFBTime.setText(newCheYuanDetailBean.getNr().getTime());

        tvnewOtherCYXQCarUseingTime.setText(newCheYuanDetailBean.getNr().getFcsj());
        tvNewOtherCYXQMile.setText(newCheYuanDetailBean.getNr().getJuli());
        tvNewOtherCYXQCarType.setText(newCheYuanDetailBean.getNr().getCar_type());
        tvNewOtherCYXQCarLangth.setText(newCheYuanDetailBean.getNr().getCar_lange());
        tvNewOtherCYXQRemark.setText(newCheYuanDetailBean.getNr().getContent());

        adapter.setAdapter(newCheYuanDetailBean.getNr().getImgtu());
        bLat = newCheYuanDetailBean.getNr().getCflat();
        bLon = newCheYuanDetailBean.getNr().getCflng();
        eLat = newCheYuanDetailBean.getNr().getDalat();
        eLon = newCheYuanDetailBean.getNr().getDalng();
        cheLat = newCheYuanDetailBean.getNr().getCzlat();
        cheLon = newCheYuanDetailBean.getNr().getCzlng();
        cheTouXiang = newCheYuanDetailBean.getNr().getCztouxiang();
        if(newCheYuanDetailBean.getNr().getZt().equals("0")){
            tvNewOtherCYXQBottomTGBJSubmit.setText("接单中");
            llyNewOtherCYXQBottom.setBackgroundResource(R.mipmap.bottom_gray);
        }else{
            tvNewOtherCYXQBottomTGBJSubmit.setText("运输中");
            llyNewOtherCYXQBottom.setBackgroundResource(R.mipmap.tgbj_orange_white_redius_bg);
        }

    /*    String dingjin = newCheYuanDetailBean.getNr().getDingjin();
        if(dingjin.equals("0")){
            rlyNewOtherCYXQSJBJ.setVisibility(View.GONE);
        }else {
            rlyNewOtherCYXQSJBJ.setVisibility(View.VISIBLE);
        }
        String cjj = newCheYuanDetailBean.getNr().getChengjiao();
        if(cjj.equals("0")){
            tvNewOtherCYXQCJJ.setText("暂未成交");
        }else {
            tvNewOtherCYXQCJJ.setText(cjj);
        }

        String hyckj = newCheYuanDetailBean.getNr().getCankao();
        tvNewOtherCYXQHYCKJ.setText(hyckj);*/
        iphone = newCheYuanDetailBean.getNr().getIphone();
        bAddr = newCheYuanDetailBean.getNr().getCfdizhi();
        eAddr = newCheYuanDetailBean.getNr().getDadizhi();
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
                ImageLoader.getInstance().displayImage(newAdsBean.getNr().getImg(),ibNewOtherCYXQAds, ImageLoaderUtils.options1);
                adsUrl = newAdsBean.getNr().getUrl();
            }
        });
    }

}
