package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
 * Created by Administrator on 2017/7/12.
 */

public class NewBanJiaDetailController extends BaseController {


    private String bLat,bLon,eLat,eLon,cheLat,cheLon,cheTouXiang;
    private String bAddr = "",eAddr="";

    @BindView(R.id.tv_new_banjiadetail_title)
    TextView tvNewBanJiaDetailTitle;
    @BindView(R.id.tv_new_banjiadetail_city_area_b)
    TextView tvNewBanJiaDetailCityAreaB;
    @OnClick(R.id.tv_new_banjiadetail_city_area_b)
    public void tvNewBanJiaDetailCityAreaBOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看起点位置
        intent.putExtra("czlat",bLat);
        intent.putExtra("czlon",bLon);
        intent.putExtra("baddr",bAddr);
        intent.putExtra("czid",czid);
        intent.putExtra("title","qdwz");
        activity.startActivity(intent);
    }
    @BindView(R.id.tv_new_banjiadetail_city_area_e)
    TextView tvNewBanJiaDetailCityAreaE;
    @OnClick(R.id.tv_new_banjiadetail_city_area_e)
    public void tvNewBanJiaDetailCityAreaEOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看终点位置
        intent.putExtra("czlat",eLat);
        intent.putExtra("czlon",eLon);
        intent.putExtra("eaddr",eAddr);
        intent.putExtra("czid",czid);
        intent.putExtra("title","zdwz");
        activity.startActivity(intent);
    }
    @BindView(R.id.tv_new_banjiadetail_detailservice_contact_by_tel)
    TextView tvNewBanJiaDetailDetailServiceContactByTel;
    @BindView(R.id.rly_new_banjiadetail_hc_loc)
    RelativeLayout rlyNewBanJiaDetailHCLoc;
    @OnClick(R.id.rly_new_banjiadetail_hc_loc)
    public void rlyNewBanJiaDetailHCLocOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("czTouXiang",cheTouXiang);
        intent.putExtra("czid",czid);
        intent.putExtra("title","hcdw");
        activity.startActivity(intent);
    }
    @BindView(R.id.rv_new_banjiadetail_cartype)
    RecyclerView rvNewBanJiaDetailCarType;
    @BindView(R.id.tv_new_banjiadetail_company_name)
    TextView tvNewBanJiaDetailCompanyName;
    @BindView(R.id.tv_new_banjiadetail_company_introdu)
    TextView tvNewBanJiaDetailCompanyIntrodu;
    @BindView(R.id.tv_new_banjiadetail_remark)
    TextView tvNewBanJiaDetailRemark;
    @BindView(R.id.ib_new_banjiadetail_ads)
    ImageButton ibNewBanJiaDetailAds;
    @OnClick(R.id.ib_new_banjiadetail_ads)
    public void ibNewBanJiaDetailAdsOnclick(){
        Intent intent = new Intent(activity, NewAdsDetailActivity.class);
        intent.putExtra("url",adsUrl);
        activity.startActivity(intent);

    }
    @BindView(R.id.tv_new_banjiadetail_tjbj_submit)
    TextView tvNewBanJiaDetailTJBJSubmit;
    CallTelDialog callTelDialog;
    @BindView(R.id.rly_new_banjiadetail_tel)
    RelativeLayout rlyNewBanJiaDetailTel;
    @OnClick(R.id.rly_new_banjiadetail_tel)
    public void rlyNewBanJiaDetailTelOnclick(){
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
    @BindView(R.id.rly_new_banjiadetail_chat)
    RelativeLayout rlyNewBanJiaDetailChat;
    @OnClick(R.id.rly_new_banjiadetail_chat)
    public void rlyNewBanJiaDetailChatOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            activity.startActivity(new Intent(activity,LoginActivity.class));
            return;
        }

        activity.startActivity(new Intent(activity,ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, id));
    }



    @BindView(R.id.rly_new_banjiadetail_back)
    RelativeLayout rlyNewBanJiaDetailBack;
    @OnClick(R.id.rly_new_banjiadetail_back)
    public void rlyNewBanJiaDetailBackOnclick(){
        activity.finish();
    }
    private String cyId,adsUrl ;
    private String iphone;
    private String czid = "";
    private String id = "0";
    NewBanJiaDetailRVAdapter banJiaDetailRVAdapter;
    List<NewCheYuanDetailBean.NrBean.BjcxixniBean> dataList;
    public NewBanJiaDetailController(Activity activity1){
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

    private void getCyId() {
        cyId = activity.getIntent().getStringExtra("cyid");
        if (cyId == null) {
            cyId = "";
        }
    }

    private void initRV(){
        dataList = new ArrayList<>();
        banJiaDetailRVAdapter = new NewBanJiaDetailRVAdapter(activity,dataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewBanJiaDetailCarType.setAdapter(banJiaDetailRVAdapter);
        rvNewBanJiaDetailCarType.setLayoutManager(gridLayoutManager);
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
        tvNewBanJiaDetailCityAreaB.setText(newCheYuanDetailBean.getNr().getCfshi()+newCheYuanDetailBean.getNr().getCfqu());
        tvNewBanJiaDetailCityAreaE.setText(newCheYuanDetailBean.getNr().getDashi()+newCheYuanDetailBean.getNr().getDaqu());
  /*      tvNewBanJiaDetailDetailServiceContactByTel.setText(newCheYuanDetailBean.getNr().get());*/
        tvNewBanJiaDetailCompanyName.setText(newCheYuanDetailBean.getNr().getPeople());
        tvNewBanJiaDetailCompanyIntrodu.setText(newCheYuanDetailBean.getNr().getJianjie());
        tvNewBanJiaDetailRemark.setText(newCheYuanDetailBean.getNr().getContent());
        bLat = newCheYuanDetailBean.getNr().getCflat();
        bLon = newCheYuanDetailBean.getNr().getCflng();
        eLat = newCheYuanDetailBean.getNr().getDalat();
        eLon = newCheYuanDetailBean.getNr().getDalng();
        cheLat = newCheYuanDetailBean.getNr().getCzlat();
        cheLon = newCheYuanDetailBean.getNr().getCzlng();
        cheTouXiang = newCheYuanDetailBean.getNr().getCztouxiang();
        if(newCheYuanDetailBean.getNr().getZt().equals("0")){
            tvNewBanJiaDetailTJBJSubmit.setText("空车");
           /* llyNewOtherCYXQBottom.setBackgroundResource(R.mipmap.bottom_gray);*/
        }else{
            tvNewBanJiaDetailTJBJSubmit.setText("拉货中");
           /* llyNewOtherCYXQBottom.setBackgroundResource(R.mipmap.tgbj_orange_white_redius_bg);*/
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
        czid = newCheYuanDetailBean.getNr().getLogin_id();
        id = newCheYuanDetailBean.getNr().getLogin_id();
        banJiaDetailRVAdapter.setAdapterDataList(newCheYuanDetailBean.getNr().getBjcxixni());
        tvNewBanJiaDetailTitle.setText(newCheYuanDetailBean.getNr().getCar_title());
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
                ImageLoader.getInstance().displayImage(newAdsBean.getNr().getImg(),ibNewBanJiaDetailAds, ImageLoaderUtils.options1);
                adsUrl = newAdsBean.getNr().getUrl();
            }
        });
    }
}
