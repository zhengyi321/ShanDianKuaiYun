package com.shandian.lu.Main.IndexFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.shandian.lu.Main.BaseFragment;
import com.shandian.lu.Main.IndexFragment.CityChange.CityChangeActivity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewMainIndexFragment extends BaseFragment {
    @BindView(R.id.tv_main_index_city)
    TextView tvMainIndexCity;
    @BindView(R.id.rly_main_index_changecity)
    RelativeLayout rlyMainIndexChangeCity;
    @OnClick(R.id.rly_main_index_changecity)
    public void rlyMainIndexChangeCityOnclick(){
        return;/*
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        Intent intent = new Intent(view.getContext(), CityChangeActivity.class);
        String city = tvMainIndexCity.getText().toString();

        intent.putExtra("currentcity",city);
        xcCacheManager.writeCache(xcCacheSaveName.modlestatus,"index");

        ((Activity)view.getContext()).startActivity(intent);*/

    }


    private NewMainIndexController newMainIndexController;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isFirst = true;
    @Override
    public View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_main_index_lly, container, false);
        return view;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,view);
        initController();
        initBaidu();
    }

    private void initController(){
        newMainIndexController = new NewMainIndexController(view);
    }





    private void initBaidu( ){


        mLocationClient = new LocationClient(view.getContext());

        if(mLocationClient == null){
            return;
        }
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        //注册监听函数
        initLocation();
        mLocationClient.start();



    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=30000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location != null) {



           /*     if(isFirst) {*/
                    //获取定位结果

                String lat = location.getLatitude() + "";
                String lon = location.getLongitude() + "";
                XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
                String city = location.getCity();

               /* Toast.makeText(view1.getContext(),"city:"+ location.getLocType(),Toast.LENGTH_LONG).show();*/
               /* Toast.makeText(view.getContext(),"city:"+city,Toast.LENGTH_LONG).show();*/



                    if (city == null) {
                        city = "";
                    }
                    xcCacheManager.writeCache(xcCacheSaveName.currentCity, city);
                String area = location.getDistrict();

               /* Toast.makeText(view1.getContext(),"city:"+ location.getLocType(),Toast.LENGTH_LONG).show();*/
               /* Toast.makeText(view.getContext(),"city:"+city,Toast.LENGTH_LONG).show();*/



                    if (area == null) {
                        area = "";
                    }
                    xcCacheManager.writeCache(xcCacheSaveName.currentArea, area);
                    if (lat == null) {
                        lat ="";
                    }
                    xcCacheManager.writeCache(xcCacheSaveName.currentLat, lat);
                    if (lon == null) {
                        lon = "";
                    }
                    xcCacheManager.writeCache(xcCacheSaveName.currentlon, lon);
                    String currentLocRadius = location.getRadius()+"";
                    if (currentLocRadius == null) {
                        currentLocRadius = "";
                    }
                    xcCacheManager.writeCache(xcCacheSaveName.currentLocRadius, currentLocRadius);
                    String currentLocAddrStr = location.getAddrStr() + " " + location.getLocationDescribe();
                    if (currentLocAddrStr == null) {
                        currentLocAddrStr = "";
                    }
                    xcCacheManager.writeCache(xcCacheSaveName.currentLocAddrStr, currentLocAddrStr);
              /*      System.out.print("this is lat\n:"+lat);
                    System.out.print("this is lon\n:"+lon);
                    Log.i("this is lat",lat);
                    Log.i("this is lat",lat);
                    Log.i("this is lat",lat);
                    Log.i("this is lat",lat);
                    Log.i("this is lat",lat);
                    Log.i("this is lat",lat);
                    Log.i("this is lon",lon);
                    Log.i("this is lon",lon);
                    Log.i("this is lon",lon);
                    Log.i("this is lon",lon);
                    Log.i("this is lon",lon);*/
                   /* tvMainIndexCity.setText(city);*/
                 /*   isFirst = false;*/
        /*        }*/
            }

        }

    }




    @Override
    public void onResume(){
        super.onResume();
    /*    if(!isFirst) {*/
            selectResult();
     /*   }*/

    }
    private void selectResult(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String area = xcCacheManager.readCache(xcCacheSaveName.currentArea);



        xcCacheManager.writeCache(xcCacheSaveName.modlestatus,"");
        if(area != null){
         /*   int indexCity2 = city.indexOf("全");
            if (indexCity2 >= 0) {
                city = city.substring(indexCity2 + 1, city.length());
            }*/
            tvMainIndexCity.setText(area);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();   //添加这句就行了
    }


}
