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
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewAdsBean;
import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.Main.IndexFragment.NewAdsDetail.NewAdsDetailActivity;
import com.shandian.lu.Main.MessageFragment.Chat.ChatActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.AdsNetWork;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.myhuanxin.EaseConstant;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
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

public class NewCheYuanDetailSelfV2Controller extends BaseController {

    private String bLat,bLon,eLat,eLon,cheLat,cheLon,cheTouXiang;
    private String adsUrl = "";
    private String bAddr="",eAddr="";
    private String id = "";
    @BindView(R.id.rly_new_self_cyxq_back)
    RelativeLayout rlyNewSelfCYXQBack;
    @OnClick(R.id.rly_new_self_cyxq_back)
    public void rlyNewSelfCYXQBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.tv_new_self_cyxq_city_area_b)
    TextView tvNewSelfCYXQCityAreaB;
    @OnClick(R.id.tv_new_self_cyxq_city_area_b)
    public void tvNewSelfCYXQCityAreaBOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看起点位置
        intent.putExtra("czlat",bLat);
        intent.putExtra("czlon",bLon);
        intent.putExtra("baddr",bAddr);
        intent.putExtra("title","qdwz");
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_new_self_cyxq_city_area_e)
    TextView tvNewSelfCYXQCityAreaE;
    @OnClick(R.id.tv_new_self_cyxq_city_area_e)
    public void tvNewSelfCYXQCityAreaEOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看终点位置
        intent.putExtra("czlat",eLat);
        intent.putExtra("czlon",eLon);
        intent.putExtra("eaddr",eAddr);
        intent.putExtra("title","zdwz");
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_new_self_cyxq_fbtime)
    TextView tvNewSelfCYXQFBTime;

    @BindView(R.id.rly_new_self_cyxq_sjbj)
    RelativeLayout rlyNewSelfCYXQSJBJ;

    @BindView(R.id.lly_new_self_cyxq_sjbj)
    LinearLayout llyNewSelfCYXQSJBJ;

    @BindView(R.id.tv_new_self_cyxq_sjbj)
    TextView tvNewSelfCYXQSJBJ;
    @BindView(R.id.tv_new_self_cyxq_sjbj_price)
    TextView tvNewSelfCYXQSJBJPrice;
    @BindView(R.id.tv_new_self_cyxq_cjj)
    TextView tvNewSelfCYXQCJJ;
    @BindView(R.id.tv_new_self_cyxq_hyckj)
    TextView tvNewSelfCYXQHYCKJ;

    @BindView(R.id.tv_new_self_cyxq_car_useing_time)
    TextView tvnewSelfCYXQCarUseingTime;
    @BindView(R.id.tv_new_self_cyxq_carlangth)
    TextView tvNewSelfCYXQCarLangth;
    @BindView(R.id.tv_new_self_cyxq_cartype)
    TextView tvNewSelfCYXQCarType;
    @BindView(R.id.tv_new_self_cyxq_mile)
    TextView tvNewSelfCYXQMile;
    private String iphone;
    CallTelDialog callTelDialog;
    @BindView(R.id.rly_new_self_cyxq_bottom_tel)
    RelativeLayout rlyNewSelfCYXQBottomTel;
    @OnClick(R.id.rly_new_self_cyxq_bottom_tel)
    public void rlyNewSelfCYXQBottomTelOnclick(){
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
    @BindView(R.id.rly_new_self_cyxq_bottom_message)
    RelativeLayout rlyNewSelfCYXQBottomMessage;
    @OnClick(R.id.rly_new_self_cyxq_bottom_message)
    public void rlyNewSelfCYXQBottomMessageOnclick(){
       /* XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            activity.startActivity(new Intent(activity,LoginActivity.class));
            return;
        }

        activity.startActivity(new Intent(activity,ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, id));*/
       Toast.makeText(activity,"不能与自己聊天",Toast.LENGTH_SHORT).show();
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

    @BindView(R.id.ib_new_self_cyxq_ads)
    ImageButton ibNewSelfCYXQAds;
    @OnClick(R.id.ib_new_self_cyxq_ads)
    public void ibNewSelfCYXQAdsOnclick(){
        Intent intent = new Intent(activity, NewAdsDetailActivity.class);
        intent.putExtra("url",adsUrl);
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_new_self_cyxq_remark)
    TextView tvNewSelfCYXQRemark;
    @BindView(R.id.rv_new_self_cyxq_img)
    RecyclerView rvNewSelfCYXQImg;

    @BindView(R.id.tv_new_self_cyxq_bottom_tgbj_submit)
    TextView tvNewSelfCYXQBottomTGBJSubmit;
    @BindView(R.id.lly_new_self_cyxq_bottom)
    LinearLayout llyNewSelfCYXQBottom;

    @BindView(R.id.rly_new_self_cyxq_hc_loc)
    RelativeLayout rlyNewSelfCYXQHCLoc;
    @OnClick(R.id.rly_new_self_cyxq_hc_loc)
    public void rlyNewSelfCYXQHCLocOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("czTouXiang",cheTouXiang);
        intent.putExtra("title","hcdw");
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_new_self_cyxq_title)
    TextView tvNewSelfCYXQTitle;
    @BindView(R.id.rly_new_self_cyxq_mapline)
    RelativeLayout rlyNewOtherCYXQMapLine;
    @OnClick(R.id.rly_new_self_cyxq_mapline)
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
    public NewCheYuanDetailSelfV2Controller(Activity activity1){
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
        String typeName = activity.getIntent().getStringExtra("type_name");
/*        Toast.makeText(activity,"self:"+typeName,Toast.LENGTH_LONG).show();*/
        if((typeName != null)&&(typeName.equals("5"))){
            tvNewSelfCYXQTitle.setText("快递详情");
        }if((typeName != null)&&(typeName.equals("6"))){
            tvNewSelfCYXQTitle.setText("搬家详情");
        }
       /* Toast.makeText(activity,"cyId:"+cyId,Toast.LENGTH_LONG).show();*/
    }
    private void initRV(){
        imgList = new ArrayList<>();
        adapter = new NewCheYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewSelfCYXQImg.setAdapter(adapter);
        rvNewSelfCYXQImg.setLayoutManager(linearLayoutManager);
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
        tvNewSelfCYXQCityAreaB.setText(newCheYuanDetailBean.getNr().getCfshi()+newCheYuanDetailBean.getNr().getCfqu());
        tvNewSelfCYXQCityAreaE.setText(newCheYuanDetailBean.getNr().getDashi()+newCheYuanDetailBean.getNr().getDaqu());
        tvNewSelfCYXQFBTime.setText(newCheYuanDetailBean.getNr().getTime());

        tvnewSelfCYXQCarUseingTime.setText(newCheYuanDetailBean.getNr().getFcsj());
        tvNewSelfCYXQMile.setText(newCheYuanDetailBean.getNr().getJuli());
        tvNewSelfCYXQCarType.setText(newCheYuanDetailBean.getNr().getCar_type());
        tvNewSelfCYXQCarLangth.setText(newCheYuanDetailBean.getNr().getCar_lange());
        tvNewSelfCYXQRemark.setText(newCheYuanDetailBean.getNr().getContent());

        adapter.setAdapter(newCheYuanDetailBean.getNr().getImgtu());
        bLat = newCheYuanDetailBean.getNr().getCflat();
        bLon = newCheYuanDetailBean.getNr().getCflng();
        eLat = newCheYuanDetailBean.getNr().getDalat();
        eLon = newCheYuanDetailBean.getNr().getDalng();
        cheLat = newCheYuanDetailBean.getNr().getCzlat();
        cheLon = newCheYuanDetailBean.getNr().getCzlng();
        cheTouXiang = newCheYuanDetailBean.getNr().getCztouxiang();
        if(newCheYuanDetailBean.getNr().getZt().equals("0")){
            tvNewSelfCYXQBottomTGBJSubmit.setText("空车");
          /*  llyNewSelfCYXQBottom.setBackgroundResource(R.mipmap.bottom_gray);*/
        }else{
            tvNewSelfCYXQBottomTGBJSubmit.setText("拉货中");
          /*  llyNewSelfCYXQBottom.setBackgroundResource(R.mipmap.tgbj_orange_white_redius_bg);*/
        }

        String dingjin = newCheYuanDetailBean.getNr().getDingjin();
        if(dingjin.equals("0")){
            rlyNewSelfCYXQSJBJ.setVisibility(View.GONE);
        }else {
            rlyNewSelfCYXQSJBJ.setVisibility(View.VISIBLE);
        }
        String cjj = newCheYuanDetailBean.getNr().getChengjiao();
        if(cjj.equals("0")){
            tvNewSelfCYXQCJJ.setText("暂未成交");
        }else {
            tvNewSelfCYXQCJJ.setText(cjj);
        }

        String hyckj = newCheYuanDetailBean.getNr().getCankao();
        tvNewSelfCYXQHYCKJ.setText(hyckj);
        iphone = newCheYuanDetailBean.getNr().getIphone();
        bAddr = newCheYuanDetailBean.getNr().getCfdizhi();
        eAddr = newCheYuanDetailBean.getNr().getDadizhi();
        id = newCheYuanDetailBean.getNr().getId();
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
                ImageLoader.getInstance().displayImage(newAdsBean.getNr().getImg(),ibNewSelfCYXQAds, ImageLoaderUtils.options1);
                adsUrl = newAdsBean.getNr().getUrl();
            }
        });
    }

}
