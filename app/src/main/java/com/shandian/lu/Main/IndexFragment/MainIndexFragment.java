package com.shandian.lu.Main.IndexFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.shandian.lu.Main.BaseFragment;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import com.shandian.lu.Main.IndexFragment.CityChange.CityChangeActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/25.
 */

public class MainIndexFragment extends BaseFragment {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isFirst = true;
    public final int CITY_CHANGE_SELECTED = 99;
    @BindView(R.id.tv_main_index_city)
    TextView tvMainIndexCity;
    @BindView(R.id.rly_main_index_changecity)
    RelativeLayout rlyMainIndexChangeCity;
    @OnClick(R.id.rly_main_index_changecity)
    public void rlyMainIndexChangeCityOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view1.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        Intent intent = new Intent(view1.getContext(), CityChangeActivity.class);
        String city = tvMainIndexCity.getText().toString();
        intent.putExtra("currentcity",city);
        xcCacheManager.writeCache(xcCacheSaveName.modlestatus,"index");

        ((Activity)view1.getContext()).startActivity(intent);

    }
    private MainIndexController mainIndexController;

    private View view1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_index_lly, container, false);
        init(view);
        view1 = view;
        return view;
    }



    private void init(View view){
        ButterKnife.bind(this,view);
        initController(view);
        initBaidu(view);

    }
    private void initController(View view){
        mainIndexController = new MainIndexController(view);
    }



    private void initBaidu(View view){
        mLocationClient = new LocationClient(view.getContext());
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

               /* Toast.makeText(view1.getContext(),"city:"+ location.getLocType(),Toast.LENGTH_LONG).show();*/
               /* Toast.makeText(view1.getContext(),"city:"+city,Toast.LENGTH_LONG).show();*/
                if(city == null){
                    return;
                }
                if(isFirst) {
                    int indexCity = city.indexOf("市");
                    /*Toast.makeText(view1.getContext(),"indexcity:"+indexCity,Toast.LENGTH_LONG).show();*/
                    if(indexCity >= 0){
                        city = city.substring(0,indexCity);
                    }
                    int indexCity2 = city.indexOf("全");
                    if(indexCity2 >= 0){
                        city = city.substring(indexCity2+1,city.length());
                    }
                    String lat = location.getLatitude()+"";
                    String lon = location.getLongitude()+"";
                    XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                    XCCacheManager xcCacheManager = XCCacheManager.getInstance(getContext());
                    xcCacheManager.writeCache(xcCacheSaveName.currentCity,city);
                    xcCacheManager.writeCache(xcCacheSaveName.currentLat,lat);
                    xcCacheManager.writeCache(xcCacheSaveName.currentlon,lon);
                    System.out.print("this is lat\n:"+lat);
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
                    Log.i("this is lon",lon);
                   /* tvMainIndexCity.setText(city);*/
                    isFirst = false;
                }
            }
        }

    }






    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();   //添加这句就行了
    }







    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(view1.getContext(),"this is onActivityResult",Toast.LENGTH_LONG).show();
        switch (resultCode){
            case CITY_CHANGE_SELECTED:
                Bundle b=data.getExtras(); //data为B中回传的Intent
                String city=b.getString("city");//str即为回传的值
                String id1 = b.getString("aid");
                String id2 = b.getString("cid");
                String id3 = b.getString("tid");
                if((city == null)||(!city.isEmpty())) {
                    tvMainIndexCity.setText(city);
                }

        }


    }*/

    @Override
    public void onResume(){
        super.onResume();
        selectResult();
    }
    private void selectResult(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view1.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String city = xcCacheManager.readCache(xcCacheSaveName.currentCity);
        if(city == null){
            return;
        }
        xcCacheManager.writeCache(xcCacheSaveName.modlestatus,"");
        if(city != null){

            tvMainIndexCity.setText(city);
        }
    }


}
