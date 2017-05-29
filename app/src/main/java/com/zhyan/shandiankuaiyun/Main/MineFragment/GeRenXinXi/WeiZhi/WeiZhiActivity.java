package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.WeiZhi;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.MainIndexFragment;
import com.zhyan.shandiankuaiyun.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy;

/**
 * Created by az on 2017/5/4.
 */

public class WeiZhiActivity extends BaseActivity {

    private WeiZhiController weiZhiController;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    @BindView(R.id.tv_main_mine_gerenxinxi_weizhi_addr)
    TextView tvMainMineGeRenXinXiWeiZhiAddr;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_weizhi_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        initBaidu();
    }


    private void initController(){
        weiZhiController = new WeiZhiController(this);
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
        LocationClientOption option=new LocationClientOption();
        option.setLocationMode(Hight_Accuracy);//设置定位模式
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);//返回地址
        option.setIsNeedLocationDescribe(true);//返回地址周边描述
        option.setEnableSimulateGps(false);
        //开启定位图层

        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if(location != null) {
                //获取定位结果
              /*  StringBuffer sb = new StringBuffer(256);

                sb.append("time : ");
                sb.append(location.getTime());    //获取定位时间

                sb.append("\nerror code : ");
                sb.append(location.getLocType());    //获取类型类型

                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());    //获取纬度信息

                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());    //获取经度信息

                sb.append("\nradius : ");
                sb.append(location.getRadius());    //获取定位精准度

                if (location.getLocType() == BDLocation.TypeGpsLocation){

                    // GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());    // 单位：公里每小时

                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());    //获取卫星数

                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                    sb.append("\ndirection : ");
                    sb.append(location.getDirection());    //获取方向信息，单位度

                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());    //获取地址信息

                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");

                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

                    // 网络定位结果
                    sb.append("\naddr : ");
                    sb.append(location.getAddrStr());    //获取地址信息

                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());    //获取运营商信息

                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");

                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                    // 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");

                } else if (location.getLocType() == BDLocation.TypeServerError) {

                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");

                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

                }

                sb.append("\nlocationdescribe : ");
                sb.append(location.getLocationDescribe());    //位置语义化信息

                List<Poi> list = location.getPoiList();    // POI数据
                if (list != null) {
                    sb.append("\npoilist size = : ");
                    sb.append(list.size());
                    for (Poi p : list) {
                        sb.append("\npoi= : ");
                        sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                    }
                }

                Log.i("BaiduLocationApiDem", sb.toString());*/

/*
                String addr = location.getLocationDescribe();
                String addr1 = location.getAddrStr();
                String addr2 = location.getDistrict();
                String addr3 = location.getAddress().district;
                String addr4 = location.getAddress().address;
                System.out.print("LocationDescribe:"+addr);
                System.out.print("\ngetAddrStr:"+addr1);
                System.out.print("\ngetDistrict:"+addr2);
                System.out.print("\ndistrict:"+addr3);
                System.out.print("\naddress:"+addr4);
                System.out.print("LocationDescribe:"+addr);
                System.out.print("\ngetAddrStr:"+addr1);
                System.out.print("\ngetDistrict:"+addr2);
                System.out.print("\ndistrict:"+addr3);
                System.out.print("\naddress:"+addr4);
                System.out.print("LocationDescribe:"+addr);
                System.out.print("\ngetAddrStr:"+addr1);
                System.out.print("\ngetDistrict:"+addr2);
                System.out.print("\ndistrict:"+addr3);
                System.out.print("\naddress:"+addr4);*/
               /* Toast.makeText(WeiZhiActivity.this,addr3,Toast.LENGTH_LONG).show();*/
                String addr = location.getAddrStr() + " " + location.getLocationDescribe();
                tvMainMineGeRenXinXiWeiZhiAddr.setText(addr);
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
    }

}
