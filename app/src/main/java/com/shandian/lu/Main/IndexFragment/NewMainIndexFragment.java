package com.shandian.lu.Main.IndexFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.mynewslayoutlib.Bean.NewUpSelfLocToNetBean;
import com.example.mynewslayoutlib.Utils.DeviceUtil;
import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.Main.BaseFragment;
import com.shandian.lu.Main.IndexFragment.CityChange.CityChangeActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.GeRenXinXiActivity;
import com.shandian.lu.NetWork.MainIndexNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import retrofit2.http.Query;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewMainIndexFragment extends BaseFragment {


    private boolean isFirstSetJpushAlias = true;
    private final int MSG_SET_ALIAS = 1001;
    protected final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    if(view1 == null){
                        return;
                    }
                    /*JPushInterface.setAliasAndTags(view1.getContext(), (String) msg.obj, null, mAliasCallback);*/
                    JPushInterface.setAliasAndTags(view1.getContext(), (String) msg.obj,null,  mAliasCallback);
                    break;



                default:

            }
        }
    };


    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {


        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("success",logs);
                 /*   Toast.makeText(activity,"here is success:"+alias+" "+tags,Toast.LENGTH_LONG).show();*/
               /*     NotificationCompat.Builder	notification = new NotificationCompat.Builder(activity).setSmallIcon(R.mipmap.logo)
                            .setSound(Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.shandian));*/
                            /*.setContentText(title);*/
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";


                    break;

                default:
                    logs = "Failed with errorCode = " + code;

            }


        }

    };
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

    private View view1;
    private NewMainIndexController newMainIndexController;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isFirst = true;
    @Override
    public View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_main_index_lly, container, false);
        view1 = view;
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

        int span=5000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        //可选，默认false,设置是否使用gps
        SystemUtils systemUtils = new SystemUtils((Activity) view1.getContext());
        int workType = systemUtils.getNetWorkType(view1.getContext());
        switch (workType){
            case -1:
                option.setOpenGps(false);
            case 0:
                option.setOpenGps(false);
                break;
            case 1:
                option.setPriority(LocationClientOption.GpsFirst); //设置gps优先
                option.setOpenGps(true);
                break;
            case 2:
                option.setPriority(LocationClientOption.GpsFirst); //设置gps优先
                option.setOpenGps(true);
                break;
            case 3:
                option.setPriority(LocationClientOption.GpsFirst); //设置gps优先
                option.setOpenGps(true);
                break;
            case 4:
                option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
                option.setOpenGps(false);
                break;

        }


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
                String prov = location.getProvince();
               /* Toast.makeText(view1.getContext(),"city:"+ location.getLocType(),Toast.LENGTH_LONG).show();*/
               /* Toast.makeText(view.getContext(),"city:"+city,Toast.LENGTH_LONG).show();*/

                if (prov == null) {
                    prov = "";
                }
                xcCacheManager.writeCache(xcCacheSaveName.currentProvince, prov);
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
                xcCacheManager.writeCache(xcCacheSaveName.currentLon, lon);
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
                String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
                if((loginId == null)||(loginId.isEmpty())){
                    return;
                }
                MainIndexNetWork mainIndexNetWork = new MainIndexNetWork();
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());/*
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());
                System.out.print("\nlat:"+location.getLatitude()+" lon:"+location.getLongitude());*/
                DeviceUtil deviceUtil = new DeviceUtil();
                String deviceId = deviceUtil.getDeviceId(view.getContext());
                if((deviceId== null)||(deviceId.isEmpty())){
                    return;
                }
                Map<String,String> paramMap = new HashMap<>();
                paramMap.put("login_id",loginId);
                paramMap.put("lat",location.getLatitude()+"");
                paramMap.put("lng",location.getLongitude()+"");
                paramMap.put("cfsheng",location.getProvince()+"");
                paramMap.put("cfshi",location.getCity()+"");
                paramMap.put("cfqu",location.getDistrict()+"");
                paramMap.put("sbh",deviceId);
                mainIndexNetWork.upSelfLocToNet(paramMap, new Observer<NewUpSelfLocToNetBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewUpSelfLocToNetBean newUpSelfLocToNetBean) {
                       /* Toast.makeText(getContext(),newUpSelfLocToNetBean.getMsg(),Toast.LENGTH_LONG).show();*/
                       if(newUpSelfLocToNetBean.getNr().getDlzt().equals("0")){
                           if(isFirstSetJpushAlias) {
                               loginOut();
                               isFirstSetJpushAlias = false;
                           }
                       }else {
                           if(isFirstSetJpushAlias) {
                               initAliasJpush();
                               isFirstSetJpushAlias = false;
                           }
                       }
                    }
                });
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

    private void loginOut(){
        if(view1 == null){
            return;
        }

        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view1.getContext());
        xcCacheManager.writeCache(xcCacheSaveName.logId,"");
        xcCacheManager.writeCache(xcCacheSaveName.loginStatus,"no");
        xcCacheManager.writeCache(xcCacheSaveName.userName,"");
        xcCacheManager.writeCache(xcCacheSaveName.userTel,"");
        xcCacheManager.writeCache(xcCacheSaveName.userHeadImgUrl,"");
        Toast.makeText(view1.getContext(),"您的手机已在别处登录",Toast.LENGTH_LONG).show();
        SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils();
        sharedPreferencesUtils.setParam(view1.getContext(),xcCacheSaveName.logId, "");
        sharedPreferencesUtils.setParam(view1.getContext(),xcCacheSaveName.loginStatus,"no");
        sharedPreferencesUtils.setParam(view1.getContext(),xcCacheSaveName.userName,"");
        sharedPreferencesUtils.setParam(view1.getContext(),xcCacheSaveName.userTel,"");
        sharedPreferencesUtils.setParam(view1.getContext(),xcCacheSaveName.userHeadImgUrl,"");
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, ""));
        JPushInterface.stopPush(view.getContext());
        Intent intent=new Intent("loginOut");

        getContext().sendBroadcast(intent);


    }
    private void initAliasJpush(){
        if(view1 == null){
            return;
        }
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view1.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, ""));
            JPushInterface.stopPush(view1.getContext());
            return;
        }
        String alias = "SDKY"+loginId;
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
        JPushInterface.resumePush(view1.getContext());
        /*mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, "保证此处是唯一的标识"));*/
    }


    @Override
    public void onResume(){
        super.onResume();
        isFirstSetJpushAlias = true;
        newMainIndexController.onResume();
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
