package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.app.Activity;
import android.content.Intent;
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
import com.shandian.lu.NetWork.AdsNetWork;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
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
    @BindView(R.id.rly_new_self_cyxq_back)
    RelativeLayout rlyNewSelfCYXQBack;
    @OnClick(R.id.rly_new_self_cyxq_back)
    public void rlyNewSelfCYXQBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.tv_new_self_cyxq_city_area_b)
    TextView tvNewSelfCYXQCityAreaB;

    @BindView(R.id.tv_new_self_cyxq_city_area_e)
    TextView tvNewSelfCYXQCityAreaE;

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

    @BindView(R.id.ib_new_self_cyxq_ads)
    ImageButton ibNewSelfCYXQAds;

    @BindView(R.id.tv_new_self_cyxq_remark)
    TextView tvNewSelfCYXQRemark;
    @BindView(R.id.rv_new_self_cyxq_img)
    RecyclerView rvNewSelfCYXQImg;


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
        tvNewSelfCYXQCityAreaB.setText(newCheYuanDetailBean.getNr().getCfshi()+"-"+newCheYuanDetailBean.getNr().getCfqu());
        tvNewSelfCYXQCityAreaE.setText(newCheYuanDetailBean.getNr().getDashi()+"-"+newCheYuanDetailBean.getNr().getDaqu());
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

            }
        });
    }

}
