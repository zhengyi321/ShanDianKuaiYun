package com.shandian.lu.Main.IndexFragment.NearByDriver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.shandian.lu.Main.IndexFragment.CheYuanList.CheYuanListActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.CommonXRVDetail.CarSourceXRVDetailActivity;
import com.shandian.lu.Main.ReleaseFragment.ZuCheHuoYun.ZuCheHuoYunActivity;
import com.shandian.lu.NetWork.MainIndexNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.NearByDriverBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

import static com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy;

/**
 * Created by az on 2017/5/23.
 */

public class NearByDriverController extends BaseController {


    @BindView(R.id.rly_main_index_nearby_driver_back)
    RelativeLayout rlyMainIndexNearByDriverBack;
    @OnClick(R.id.rly_main_index_nearby_driver_back)
    public void rlyMainIndexNearByDriverBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.rly_main_index_nearby_driver_listmodel)
    RelativeLayout rlyMainIndexNearByDriverListModel;
    @OnClick(R.id.rly_main_index_nearby_driver_listmodel)
    public void  rlyMainIndexNearByDriverListModelOnclick(){
        Intent intent = new Intent(activity, CheYuanListActivity.class);
        intent.putExtra("typeName","2");
        activity.startActivity(intent);
        activity.finish();
    }

    private MyLocationConfiguration.LocationMode mCurrentMode;
    private LocationClient locationClient=null;
    private BDLocationListener locationListener= new MyLocationListener();
    private  final int accuracyCircleFillColor = 0xAAFFFF88;
    private  final int accuracyCircleStrokeColor = 0xAA00FF00;
    @BindView(R.id.mv_main_index_nearby_driver_content)
    MapView mvMainIndexNearByDriverContent;

    public BaiduMap mBaiduMap;
    private boolean isFirst = true;
    public NearByDriverController (Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initBaiDu();
    }


    private void initBaiDu(){

        mBaiduMap = mvMainIndexNearByDriverContent.getMap();
        mvMainIndexNearByDriverContent.showZoomControls(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        locationClient=new LocationClient(activity);
        locationClient.registerLocationListener(locationListener);
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        initLocation1();
        locationClient.start();
    }
    /**配置定位参数**/
    private void initLocation1(){
        LocationClientOption option=new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setLocationMode(Hight_Accuracy);//设置定位模式
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);//返回地址
        option.setIsNeedLocationDescribe(true);//返回地址周边描述
        option.setEnableSimulateGps(false);
        locationClient.setLocOption(option);
    }
    /**接收异步返回的定位结果**/
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            if (location == null || mvMainIndexNearByDriverContent == null) {
                return;
            }


            showCurrentPosition(location);

            /*showCurrentPosition(location);*/
        }
    }



    /**定位**/
    private void showCurrentPosition(BDLocation location1){
        TextView textView = new TextView(activity);
        Drawable drawable1 = activity.getResources().getDrawable(R.mipmap.loc_arrow);
        drawable1.setBounds(0, 0, 40, 45);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        textView.setCompoundDrawables(drawable1,null,null,null);
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromView(textView);
        /*定位蓝色点*/
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location1.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(location1.getLatitude())
                .longitude(location1.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                0, 0));
        /*定位蓝色点*/
        /*LatLng latLng = new LatLng(location1.getLatitude(),location1.getLongitude());*/
        /*location(latLng);*/
       /* String addressLocation = location1.getAddrStr() *//*+ " " + location1.getLocationDescribe()*//*;
        tvMainIndexCommonMySelfLocAddr.setText(addressLocation);*/
        LatLng lng = new LatLng(location1.getLatitude(),location1.getLongitude());

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(lng).zoom(18.0f);
        if(isFirst) {
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            getNearByDriverFromNet(location1);
            isFirst = false;
        }

    }
    private void getNearByDriverFromNet(BDLocation location1){
        MainIndexNetWork mainIndexNetWork = new MainIndexNetWork();
        mainIndexNetWork.getNearByDriverFromNet(location1.getLatitude(), location1.getLongitude(), new Observer<NearByDriverBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NearByDriverBean nearByDriverBean) {
                getPoisFromKeyWordSearch(nearByDriverBean);
            }
        });
    }


    /*搜索附近的关键词*/
    private void getPoisFromKeyWordSearch(final NearByDriverBean nearByDriverBean){
        /*ArrayList<MarkerOptions> markerOptionsList = new ArrayList<MarkerOptions>();*/
        Log.i("getPois","begin");
        final List<Marker> markerList = new ArrayList<>();
        mBaiduMap.clear();
        if((nearByDriverBean.getNr() != null)&&(nearByDriverBean.getNr().size() > 0)) {
            Log.i("getPois","poiInfoList not null");
            for(int i =0;i<nearByDriverBean.getNr().size();i++) {

                LatLng ll = new LatLng(nearByDriverBean.getNr().get(i).getLat(),nearByDriverBean.getNr().get(i).getLng());
                View view = LayoutInflater.from(activity).inflate(R.layout.dialog_nearby_driver_lly, null);
                RelativeLayout rly = (RelativeLayout) view.findViewById(R.id.rly_dialog_nearby_driver_name_bg);
                TextView textView1 = (TextView) view.findViewById(R.id.tv_dialog_nearby_driver_name);
                textView1.setText(nearByDriverBean.getNr().get(i).getName());
                BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromBitmap(getViewBitmap(view));
                OverlayOptions oo = new MarkerOptions().position(ll).icon(markerIcon).zIndex(9).draggable(true);
                /*BitmapDescriptor bitmap = null;*/
             /*   rly.setOnClickListener(new MyOnClickListener(nearByDriverBean.getNr().get(i)));
                textView1.setOnClickListener(new MyOnClickListener(nearByDriverBean.getNr().get(i)));*/

                //获取添加的 marker 这样便于后续的操作
                Marker marker = (Marker) mBaiduMap.addOverlay(oo);
                markerList.add(marker);
              /*  Button button = new Button(activity);
                button.setText(nearByDriverBean.getNr().get(i).getName());
                InfoWindow mInfoWindow = new InfoWindow( button, ll, 0);
                mBaiduMap.showMapPoi(true);
                mBaiduMap.showMapIndoorPoi(true);*/
                /*markerOptionsList.add(markerOptions);*/
            }

            Log.i("getPois","poiInfoList not null");
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    int pos = 0;
                    for(int i=0;i<markerList.size();i++){
                        if(markerList.get(i) == marker) {
                            pos = i   ;
                            break;
                        }
                    }
                    XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
                    XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                    String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
                    if((loginId == null)||(loginId.isEmpty())){

                        return false;
                    }
                    Intent intent = new Intent(activity, CarSourceXRVDetailActivity.class);
                    intent.putExtra("id",""+nearByDriverBean.getNr().get(pos).getCid());
                    activity.startActivity(intent);
                    /*Toast.makeText(activity,"id:"+nearByDriverBean.getNr().get(pos).getCid(),Toast.LENGTH_LONG).show();*/
                   /* Button button = new Button(activity);
                    button.setText(nearByDriverBean.getNr().get(pos).getName());
                    LatLng ll = marker.getPosition();
                    button.setOnClickListener(new MyOnClickListener(nearByDriverBean.getNr().get(pos)));
                    InfoWindow mInfoWindow = new InfoWindow(button, ll, -47);*/
                    /*
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    for(int i=0;i<markerList.size();i++) {
                        if(((Marker)markerList.get(i)) == marker) {
                            Button button = new Button(activity);
                            button.setText(poiInfoList.get(i).address);
                            LatLng ll = marker.getPosition();
                            button.setOnClickListener(new MyOnclickListener(poiInfoList.get(i)));
                            InfoWindow mInfoWindow = new InfoWindow(button, ll, -47);
                            mBaiduMap.showInfoWindow(mInfoWindow);
                        }
                    }*/
                    return false;
                }
            });
        }

    }

    class MyOnClickListener implements View.OnClickListener {


        private NearByDriverBean.NrBean nrBean;
        public MyOnClickListener(NearByDriverBean.NrBean  nrBean1){

            nrBean = nrBean1;
        }

        @Override
        public void onClick(View v) {

            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
            System.out.print("\nid:"+nrBean.getCid());
           /* Toast.makeText(activity,"id:"+nrBean.getCid(),Toast.LENGTH_LONG).show();*/
        }
    }

    /**
     * Gets the view bitmap.
     *
     * @param addViewContent
     *            the add view content
     * @return the view bitmap
     */
    private Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);

        addViewContent.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0, addViewContent.getMeasuredWidth(), addViewContent.getMeasuredHeight());

        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }
    /* class MyOnclickListener implements View.OnClickListener{
        NearByDriverBean.NrBean nrBean;
        public MyOnclickListener(NearByDriverBean.NrBean nrBean1){
            nrBean= nrBean1;
        }

        @Override
        public void onClick(View v) {
            if(nrBean != null) {
            *//*    blat = poiInfo.location.latitude;
                blon = poiInfo.location.longitude;*//*
               *//* etMainAddressManageAddShopContentAddress.setText(poiInfo.address);*//*
            }
          *//*  mBaiduMap.hideInfoWindow();*//*
        }
    }*/

   /* class NearByDriverThread extends Thread{

        @Override
        public void run(){

        }
    }*/

    protected void onDestroy(){
        if(mBaiduMap != null) {
            mBaiduMap.clear();
            mBaiduMap = null;
        }

        isFirst = true;


        if(locationClient!=null){
            locationClient.stop();
            locationClient.unRegisterLocationListener(locationListener);
        }
        mvMainIndexNearByDriverContent.onDestroy();

    }

}
