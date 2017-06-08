package com.shandian.lu.Main.ReleaseFragment.SelectAddAddress;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/8.
 */

public class SelectAddAddressController extends BaseController implements OnGetGeoCoderResultListener,OnGetPoiSearchResultListener,TextWatcher {

    @BindView(R.id.et_new_selectaddress_keyword)
    EditText etNewSelectAddressKeyWord;
    @BindView(R.id.rly_new_selectaddress_select)
    RelativeLayout rlyNewSelectAddressSelect;
    @OnClick(R.id.rly_new_selectaddress_select)
    public void rlyNewSelectAddressSelectOnclick(){
        String address = etNewSelectAddressKeyWord.getText().toString();
        if(address != null) {
            address = address.trim();
            beginSearchLalByAddress(address);
            isSearch = true;
        }
    }
    @BindView(R.id.et_new_selectaddress_addr_detail)
    EditText etNewSelectAddressDetail;
    @BindView(R.id.rly_new_selectaddress_collect)
    RelativeLayout rlyNewSelectAddressCollect;

    @BindView(R.id.mv_new_selectaddress)
    MapView mvNewSelectAddress;
    @BindView(R.id.ib_new_selectaddress_get_addr)
    ImageButton ibNewSelectAddressGetAddr;
    @BindView(R.id.ib_new_selectaddress_get_loc)
    ImageButton ibNewSelectAddressGetLoc;
    @OnClick(R.id.ib_new_selectaddress_get_loc)
    public void ibNewSelectAddressGetLocOnclick(){
        locMySelf();
    }
    private String province,city,area;



    /*定位自己*/
    private void locMySelf(){
        location(new LatLng(selfLat,selfLon));
    }
    private String addressLocation = "";
    public double lat,lon;
    public BaiduMap mBaiduMap;

    private LocationClient locationClient=null;
    private BDLocationListener locationListener= new MyLocationListener();
    private BaiduMap.OnMapTouchListener mapTouchListener;
    /*地理编码检索*/
      /*关键字poi检索*/
    private PoiSearch poiSearch;
    /*关键字poi检索*/
    private GeoCoder mSearch;//地理编码 根据经纬度查找地址
    /*地理编码检索*/
    private Boolean isFirst = true;
    private boolean isSearch = false;
    private  final int accuracyCircleFillColor = 0xAAFFFF88;
    private  final int accuracyCircleStrokeColor = 0xAA00FF00;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private LatLng currentPt;
    double selfLat = 0,selfLon= 0;
    public SelectAddAddressController(Activity activity1){
        activity = activity1;
        init();
    }





    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        etNewSelectAddressDetail.setOnEditorActionListener(new MyEditorActionListener());
        initBaiDuMap();
    }
    private void initBaiDuMap(){
        /*监听输入框的变化*/
        /*监听输入框的变化*/
        initPoiSearch();
        mBaiduMap = mvNewSelectAddress.getMap();
        mvNewSelectAddress.showZoomControls(false);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        locationClient=new LocationClient(activity);
        locationClient.registerLocationListener(locationListener);
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        initOverlyWithMapView();

       /*地理编码初始化*/
        mSearch = GeoCoder.newInstance();
        /*地理编码初始化*/
        /*设置编码监听者*/
        mSearch.setOnGetGeoCodeResultListener(this);
        /*设置编码监听者*/
        initLocation1();
        locationClient.start();
    }

    /**配置定位参数**/
    private void initLocation1(){
        LocationClientOption option=new LocationClientOption();
        option.setOpenGps(true); // 打开gps
/*        option.setLocationMode(Hight_Accuracy);//设置定位模式*/
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);//返回地址
        option.setIsNeedLocationDescribe(true);//返回地址周边描述
        option.setEnableSimulateGps(false);
        locationClient.setLocOption(option);
    }
    /*经纬度转换为地址监听*/
    private void initPoiSearch(){
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);

    }
    /*经纬度转换为地址监听*/


    /*地图移动坐标不动*/
    private void initOverlyWithMapView(){
        mapTouchListener = new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                  /*滑动动作的时候设置为滑动状态*/

                /*滑动动作的时候设置为滑动状态*/

                int x = (int) ibNewSelectAddressGetAddr.getX();
                int y = (int) ibNewSelectAddressGetAddr.getY();
                Point point = new Point(x, y);
                //http://blog.csdn.net/sjf0115/article/details/7306284 获取控件在屏幕上的坐标
                if(point != null) {
                    currentPt = mBaiduMap.getProjection().fromScreenLocation(point);

                    mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(currentPt));
                    lat = currentPt.latitude;
                    lon = currentPt.longitude;
                }
            }
        };
        mBaiduMap.setOnMapTouchListener(mapTouchListener);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    /*地图移动坐标不动*/


    /**接收异步返回的定位结果**/
    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            if (location == null || mvNewSelectAddress == null) {
                return;
            }

            if(isFirst){
                showCurrentPosition(location);
                province = location.getProvince();
                city = location.getCity();
                area = location.getDistrict();
                isFirst = false;
            }
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
                .direction(100).latitude(location1.getLatitude())
                .longitude(location1.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                accuracyCircleFillColor, accuracyCircleStrokeColor));
        /*定位蓝色点*/
        LatLng latLng = new LatLng(location1.getLatitude(),location1.getLongitude());
        location(latLng);
    /*获取自己的坐标*/
        selfLat = location1.getLatitude();
        selfLon = location1.getLongitude();
        /*获取自己的坐标*/
        if((location1.getAddrStr()!= null)&&(location1.getLocationDescribe() != null)) {
            addressLocation = location1.getAddrStr() + " " + location1.getLocationDescribe();
            etNewSelectAddressDetail.setText(addressLocation);
          /*  beginSearchLalByAddress(addressLocation);*/
        }

    }
    /*根据地名开始查找经纬度*/
    public void beginSearchLalByAddress(String address){
       /* String address = etHelpMeBuyAddSellerAddressContentAddress.getText().toString();*/
       /* int indexBlank = address.indexOf(" ");
        if(indexBlank > 0) {
            address = address.substring(0, indexBlank+1);
        }*/
        int index = address.indexOf("市");
        try {
            if (index > 0) {
                String city = address.substring(0, index+1);
                address = address.substring(index+1,address.length());
                mSearch.geocode((new GeoCodeOption()).city(city).address(address));
            } else {
                mSearch.geocode((new GeoCodeOption()).city("浙江省温州市").address(address));
            }
        }catch (Exception e){

        }
    }


    /*地图移动到经纬度所表示的地方*/
    public void location(LatLng ll){

        /*无论哪个调用此动画 都将经纬度赋值*/
        lat = ll.latitude;
        lon = ll.longitude;
        /*无论哪个调用此动画 都将经纬度赋值*/
       /* mBaiduMap.clear();*/
        //定义地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

    }
    /*地图移动到经纬度所表示的地方*/



    /*软键盘监听*/
    public class MyEditorActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            String keyword = "";
            keyword = v.getText().toString();
            Log.i("editlistener","begin");
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.i("editlistener","search");


                beginSearchLalByAddress(keyword);
                isSearch = true;
                    /*beginSearchLalByAddress(address);*/

                Log.i("editlistener","hideInput");
                hideInput(activity);//隐藏软键盘
                return true;
            }
            return false;
        }
    }
    private InputMethodManager manager;
    private void hideInput(Activity activity) {
        // 输入法管理器 用户隐藏软键盘
        if(manager==null){
            manager = ((InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE));
        }

        manager.hideSoftInputFromWindow(( activity)
                        .getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    /*软键盘监听*/











    //经纬度转化为地址结果
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (geoCodeResult.getLocation() != null) {
            /*直接定位到具体地址*/
            location(geoCodeResult.getLocation());

        }
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(activity, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
            return;
        }
        Log.i("onGetReverseGeo","begin");
        LatLng latLng = result.getLocation();
        lon = latLng.longitude;
        lat = latLng.latitude;
        String addr = result.getAddress();
      /*  int indexOfProv = addr.indexOf()*/
      Log.i("addr",addr);
      Log.i("addr",addr);
      Log.i("addr",addr);
        if(isSearch) {
            Toast.makeText(activity,result.getAddress(),Toast.LENGTH_LONG).show();
            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
            isSearch = false;
        }
        addressLocation = result.getAddress()+ "  " + result.getSematicDescription();
        etNewSelectAddressDetail.setText(addressLocation );
        /*addressLocation = result.getAddress()+ "  " + result.getSematicDescription();
        etNewSelectAddressDetail.setText(addressLocation );*/
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        Toast.makeText(activity,"1",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        Toast.makeText(activity,"2",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        Toast.makeText(activity,"3",Toast.LENGTH_LONG).show();
    }
}
