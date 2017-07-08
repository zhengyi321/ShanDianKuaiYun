package com.shandian.lu.Main.IndexFragment.NewBanJiaRenRenKuaiDi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.shandian.lu.Main.IndexFragment.NewCheYuanDetail.NewCheYuanDetailImgRVAdapter;
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
 * Created by Administrator on 2017/6/28.
 */

public class NewBanJiaRenRenDetailController extends BaseController {
    private String bLat,bLon,eLat,eLon,cheLat,cheLon,cheTouXiang;
    private String adsUrl = "";
    private String bAddr="",eAddr="";
    private String cyId ,czid;
    private String iphone;
    private NewCheYuanDetailImgRVAdapter adapter;
    private List<String> imgList;
    @BindView(R.id.rly_new_bjrrxq_back)
    RelativeLayout rlyNewBJRRXQBack;
    @OnClick(R.id.rly_new_bjrrxq_back)
    public void rlyNewBJRRXQBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_new_bjrrxq_title)
    TextView tvNewBJRRXQTitle;
    @BindView(R.id.tv_new_bjrrxq_service)
    TextView tvNewBJRRXQService;
    @OnClick(R.id.tv_new_bjrrxq_service)
    public void tvNewBJRRXQServiceOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);//查看起点位置
        intent.putExtra("czlat",bLat);
        intent.putExtra("czlon",bLon);
        intent.putExtra("baddr",bAddr);
        intent.putExtra("title","qdwz");
        activity.startActivity(intent);
    }
    @BindView(R.id.tv_new_bjrrxq_fbtime)
    TextView tvNewBJRRXQFBTime;


    @BindView(R.id.rly_new_bjrrxq_hc_loc)
    RelativeLayout rlyNewBJRRXQHCLoc;
    @OnClick(R.id.rly_new_bjrrxq_hc_loc)
    public void rlyNewBJRRXQHCLocOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("czTouXiang",cheTouXiang);
        intent.putExtra("czid",czid);
        intent.putExtra("title","hcdw");
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_new_bjrrxq_cartype)
    TextView tvNewBJRRXQCarType;
    @BindView(R.id.tv_new_bjrrxq_carlangth)
    TextView tvNewBJRRXQCarLangth;
    @BindView(R.id.rv_new_bjrrxq_img)
    RecyclerView rvNewBJRRXQImg;
    @BindView(R.id.tv_new_bjrrxq_remark)
    TextView tvNewBJRRXQRemark;

    CallTelDialog callTelDialog;
    @BindView(R.id.rly_new_bjrrxq_bottom_tel)
    RelativeLayout rlyNewBJRRXQBottomTel;
    @OnClick(R.id.rly_new_bjrrxq_bottom_tel)
    public void rlyNewBJRRXQBottomTelOnclick(){
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
    @BindView(R.id.ib_new_bjrrxq_ads)
    ImageButton ibNewBJRRXQAds;
    @OnClick(R.id.ib_new_bjrrxq_ads)
    public void ibNewBJRRXQAdsOnclick(){
        Intent intent = new Intent(activity, NewAdsDetailActivity.class);
        intent.putExtra("url",adsUrl);
        activity.startActivity(intent);
    }
    public NewBanJiaRenRenDetailController(Activity activity1){
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
        czid = activity.getIntent().getStringExtra("czid");
        if(czid == null){
            czid = "";
        }

        String typeName = activity.getIntent().getStringExtra("type_name");
        if((typeName != null)&&(typeName.equals("5"))){
            tvNewBJRRXQTitle.setText("快递详情");
        }
        if((typeName != null)&&(typeName.equals("6"))){
            tvNewBJRRXQTitle.setText("搬家详情");
        }
       /* Toast.makeText(activity,"cyId:"+cyId,Toast.LENGTH_LONG).show();*/
    }
    private void initRV(){
        imgList = new ArrayList<>();
        adapter = new NewCheYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewBJRRXQImg.setAdapter(adapter);
        rvNewBJRRXQImg.setLayoutManager(linearLayoutManager);
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
        tvNewBJRRXQService.setText(newCheYuanDetailBean.getNr().getCfshi()+newCheYuanDetailBean.getNr().getCfqu());
        tvNewBJRRXQFBTime.setText(newCheYuanDetailBean.getNr().getTime());
        tvNewBJRRXQCarType.setText(newCheYuanDetailBean.getNr().getCar_type());
        tvNewBJRRXQCarLangth.setText(newCheYuanDetailBean.getNr().getCar_lange());
        tvNewBJRRXQRemark.setText(newCheYuanDetailBean.getNr().getContent());

        adapter.setAdapter(newCheYuanDetailBean.getNr().getImgtu());
        bLat = newCheYuanDetailBean.getNr().getCflat();
        bLon = newCheYuanDetailBean.getNr().getCflng();
        eLat = newCheYuanDetailBean.getNr().getDalat();
        eLon = newCheYuanDetailBean.getNr().getDalng();
        cheLat = newCheYuanDetailBean.getNr().getCzlat();
        cheLon = newCheYuanDetailBean.getNr().getCzlng();
        cheTouXiang = newCheYuanDetailBean.getNr().getCztouxiang();
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
                ImageLoader.getInstance().displayImage(newAdsBean.getNr().getImg(),ibNewBJRRXQAds, ImageLoaderUtils.options1);
                adsUrl = newAdsBean.getNr().getUrl();
            }
        });
    }
}
