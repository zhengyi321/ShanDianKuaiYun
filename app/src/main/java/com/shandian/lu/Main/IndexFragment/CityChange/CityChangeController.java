package com.shandian.lu.Main.IndexFragment.CityChange;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.MainIndexNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.CityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/11.
 */

public class CityChangeController extends BaseController {
    private final int CITY_CHANGE_SELECTED = 99;
   /* String[] city = {"北京","上海","天津","重庆"};*/

    @BindView(R.id.rly_main_index_citychange_current)
    RelativeLayout rlyMainIndexCityChangeCurrent;
    @BindView(R.id.rly_main_index_citychange_currentcity_total)
    RelativeLayout rlyMainIndexCityChangeCurrentCityTotal;
    @BindView(R.id.rly_main_index_citychange_hotcity)
    RelativeLayout rlyMainIndexCityChangeHotCity;
    @BindView(R.id.rly_main_index_citychange_hotcity_rv)
    RelativeLayout rlyMainIndexCityChangeHotCityRV;
    @BindView(R.id.rly_main_index_citychange_back)
    RelativeLayout rlyMainIndexCityChangeBack;
    @OnClick(R.id.rly_main_index_citychange_back)
    public void rlyMainIndexCityChangeBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_main_index_citychange_currentcity)
    TextView tvMainIndexCityChangeCurrentCity;
    @BindView(R.id.rly_main_index_citychange_currentcity)
    RelativeLayout rlyMainIndexCityChangeCurrentCity;
    @OnClick(R.id.rly_main_index_citychange_currentcity)
    public void rlyMainIndexCityChangeCurrentCityOnclick(){

        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        String city = tvMainIndexCityChangeCurrentCity.getText().toString().trim();
        int indexCity = city.indexOf("市");
                    /*Toast.makeText(view1.getContext(),"indexcity:"+indexCity,Toast.LENGTH_LONG).show();*/
        if(indexCity >= 0){
            city = city.substring(0,indexCity);
        }
        int indexCity2 = city.indexOf("全");
        if(indexCity2 >= 0){
            city = city.substring(indexCity2+1,city.length());
        }
                            /*bundle.putString("city",city[i]);*/
        bundle.putString("city",city);
        /*bundle.putString("aid",stringList.get(pos).getId());*/
        intent.putExtras(bundle);
        String model = xcCacheManager.readCache(xcCacheSaveName.modlestatus);
        if((model != null)&&(model.equals("index"))) {
            xcCacheManager.writeCache(xcCacheSaveName.currentCity, city);
            xcCacheManager.writeCache(xcCacheSaveName.currentAid, "");
            xcCacheManager.writeCache(xcCacheSaveName.currentCid, "");
            xcCacheManager.writeCache(xcCacheSaveName.currentTid, "");
        }
        activity.setResult(CITY_CHANGE_SELECTED, intent);
        activity.finish();

        /*int size = city.length;
        String curentCity = tvMainIndexCityChangeCurrentCity.getText().toString();

        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        for(int i = 0;i < size;i++){
            if(curentCity.equals(city[i])){
              *//*  Toast.makeText(activity,"this is current in",Toast.LENGTH_LONG).show();*//*
                bundle.putString("city",city[i]);

                intent.putExtras(bundle);
                activity.setResult(CITY_CHANGE_SELECTED, intent);
                activity.finish();
            }
        }
       *//* Toast.makeText(activity,"this is current out",Toast.LENGTH_LONG).show();*//*
        bundle.putString("city",curentCity);
        intent.putExtras(bundle);
        activity.setResult(CITY_CHANGE_SELECTED, intent);
        activity.finish();*/
    }
    @BindView(R.id.rv_main_index_citychange_hotcity)
    RecyclerView xrvMainIndexCityChangeHotCity;
    @BindView(R.id.rv_main_index_citychange_country)
    RecyclerView xrvMainIndexCityChangeCountry;
    private HotCityXRVAdapter hotCityAdapter;
    private CountryXRVAdapter countryAdapter;
    public CityChangeController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        /*initCity();*/
        isNeedGone();
        initXRV();
        getCityFromNet();
    }
    private void isNeedGone(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String model = xcCacheManager.readCache(xcCacheSaveName.modlestatus);
        if((model == null)||(!model.equals("index"))){
            rlyMainIndexCityChangeHotCityRV.setVisibility(View.GONE);
            rlyMainIndexCityChangeCurrentCityTotal.setVisibility(View.GONE);
            rlyMainIndexCityChangeHotCity.setVisibility(View.GONE);
            rlyMainIndexCityChangeCurrent.setVisibility(View.GONE);
        }
    }

    private void initCity(){
        String city = activity.getIntent().getStringExtra("currentcity");
        tvMainIndexCityChangeCurrentCity.setText(city);
    }
    private void initXRV(){
        GridLayoutManager hotCityGVM = new GridLayoutManager(activity,3);
        GridLayoutManager countryGVM = new GridLayoutManager(activity,3);
        List<CityBean.ContentBean.HotProvinceBean> stringList = new ArrayList<>();
        List<CityBean.ContentBean.OrdinaryProvinceBean> stringList1 = new ArrayList<>();
        stringList.add(new CityBean.ContentBean.HotProvinceBean());
        stringList.add(new CityBean.ContentBean.HotProvinceBean());
        stringList.add(new CityBean.ContentBean.HotProvinceBean());
        stringList1.add(new CityBean.ContentBean.OrdinaryProvinceBean());
        stringList1.add(new CityBean.ContentBean.OrdinaryProvinceBean());
        stringList1.add(new CityBean.ContentBean.OrdinaryProvinceBean());
        hotCityAdapter = new HotCityXRVAdapter(activity,stringList);
        countryAdapter = new CountryXRVAdapter(activity,stringList1);
        xrvMainIndexCityChangeHotCity.setAdapter(hotCityAdapter);
        xrvMainIndexCityChangeHotCity.setLayoutManager(hotCityGVM);
        xrvMainIndexCityChangeCountry.setAdapter(countryAdapter);
        xrvMainIndexCityChangeCountry.setLayoutManager(countryGVM);

    }

    private void getCityFromNet(){
        MainIndexNetWork mainIndexNetWork = new MainIndexNetWork();
        mainIndexNetWork.getCityFromNet(new Observer<CityBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CityBean cityBean) {
                hotCityAdapter.setAdapter(cityBean.getContent().getHot_province());
                countryAdapter.setAdapter(cityBean.getContent().getOrdinary_province());
            }
        });
    }






}
