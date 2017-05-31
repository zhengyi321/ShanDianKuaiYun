package com.zhyan.shandiankuaiyun.Main.IndexFragment.Common;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy;


/**
 * Created by az on 2017/5/23.
 */

public class MySelfLocController extends BaseController {

    private MyLocationConfiguration.LocationMode mCurrentMode;

    @BindView(R.id.rly_main_index_common_myselfloc_back)
    RelativeLayout rlyMainIndexCommonMySelfLocBack;
    @OnClick(R.id.rly_main_index_common_myselfloc_back)
    public void  rlyMainIndexCommonMySelfLocBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.tv_main_index_common_myselfloc_addr)
    TextView tvMainIndexCommonMySelfLocAddr;
    @BindView(R.id.mv_main_index_common_myself_loc_content)
    MapView mvMainIndexCommonMySelfLocContent;
    public BaiduMap mBaiduMap;
    private String addr="";
    private Double lat=0.0,lng=0.0;
    public MySelfLocController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getLatLngAddr();
        initBaiDu();


    }

    private void getLatLngAddr(){
        String lats = activity.getIntent().getStringExtra("lat");
        String lngs = activity.getIntent().getStringExtra("lng");
        addr = activity.getIntent().getStringExtra("addr");
        lat = Double.parseDouble(lats);
        lng = Double.parseDouble(lngs);

        Log.i("lat:",lat+"");
        Log.i("lat:",lat+"");
        Log.i("lat:",lat+"");
        Log.i("lat:",lat+"");
        Log.i("lng:",lng+"");
        Log.i("lng:",lng+"");
        Log.i("lng:",lng+"");
        Log.i("lng:",lng+"");
    }
    private void initBaiDu(){
        mBaiduMap = mvMainIndexCommonMySelfLocContent.getMap();
        mvMainIndexCommonMySelfLocContent.showZoomControls(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        showCurrentPosition();
    }





    private void showCurrentPosition(){
        TextView textView = new TextView(activity);
        Drawable drawable1 = activity.getResources().getDrawable(R.mipmap.loc_arrow);
        drawable1.setBounds(0, 0, 40, 45);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        textView.setCompoundDrawables(drawable1,null,null,null);
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromView(textView);
        /*定位蓝色点*/
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(0)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(lat)
                .longitude(lng).build();
        if(locData == null){
            return;
        }
        mBaiduMap.setMyLocationData(locData);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                0, 0));
        /*定位蓝色点*/
        /*LatLng latLng = new LatLng(location1.getLatitude(),location1.getLongitude());*/
        /*location(latLng);*/
       /* String addressLocation = location1.getAddrStr()*/ /*+ " " + location1.getLocationDescribe()*/;
        tvMainIndexCommonMySelfLocAddr.setText(addr);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(new LatLng(lat,lng)).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

    }
    /**经纬度地址动画显示在屏幕中间  有关mark网站的出处http://blog.csdn.net/callmesen/article/details/40540895**/
    public void location(LatLng latLng){
        /*只要调用画面 就能赋值*/
       /* rlat = latLng.latitude;
        rlon = latLng.longitude;*//*
        Toast.makeText(activity,"this is update1:"+rlat+" rlon:"+rlon,Toast.LENGTH_SHORT).show();*/
        /*无论哪个调用此动画 都将经纬度赋值*/
       /* mBaiduMap.clear();*/
        //定义地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(latLng).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        /*Toast.makeText(activity,"this is update2",Toast.LENGTH_SHORT).show();*/
    }

    protected void onDestroy(){
        if(mBaiduMap != null) {
            mBaiduMap.clear();
        }


        mvMainIndexCommonMySelfLocContent.onDestroy();



    }
}
