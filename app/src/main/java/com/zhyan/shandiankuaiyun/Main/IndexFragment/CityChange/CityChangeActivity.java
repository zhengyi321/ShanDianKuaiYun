package com.zhyan.shandiankuaiyun.Main.IndexFragment.CityChange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.MainIndexFragment;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/11.
 */

public class CityChangeActivity extends BaseActivity {

    public LocationClient mLocationClient = null;

    public BDLocationListener myListener = new MyLocationListener();
    private CityChangeController cityChangeController;
    @BindView(R.id.tv_main_index_citychange_currentcity)
    TextView tvMainIndexCityChangeCurrentCity;

    @Override
    protected void setContentView() {
        setContentView(R.layout.main_index_changecity_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        initBaidu();
    }

    private void initController(){
        cityChangeController = new CityChangeController(this);
    }




    private void initBaidu(){
        mLocationClient = new LocationClient(this);
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        //注册监听函数
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);// 设置定位模式
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=3000;
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
            if(location != null) {
                //获取定位结果
                String city = location.getDistrict();
                System.out.print("\n"+location.getProvince());
                System.out.print("\n"+location.getAddrStr());
                System.out.print("\n"+location.getBuildingName());
                System.out.print("\n"+location.getBuildingID());
                System.out.print("\n"+location.getCity());
                System.out.print("\n"+location.getCityCode());
                System.out.print("\n"+location.getCoorType());
                System.out.print("\n"+location.getCountry());
                System.out.print("\n"+location.getCountryCode());
                System.out.print("\n"+location.getDistrict());
                System.out.print("\n"+location.getLocType());
                System.out.print("\n"+location.getSatelliteNumber());
                System.out.print("\n"+location.getFloor());
                if(city == null){
                    return;
                }

                    int indexCity = city.indexOf("市");
                    /*Toast.makeText(view1.getContext(),"indexcity:"+indexCity,Toast.LENGTH_LONG).show();*/
                    if(indexCity >= 0){
                        city = city.substring(0,indexCity);
                    }
                tvMainIndexCityChangeCurrentCity.setText(city);


            }
        }

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
    }
}
