package com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan;

import android.view.LayoutInflater;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.mynewslayoutlib.Bean.NewSiJiLocBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
/**
 * Created by zhyan on 2017/6/11.
 */

public class NewBaiDuRoutePlanActivity extends BaseActivity{
    @BindView(R.id.mv_new_baidu_route_plan)
    MapView mvNewBaiDuRoutePlan;
    private BaiduMap mBaidumap;
    private LocationMode mCurrentMode;
    private NewBaiDuRoutePlanController newBaiDuRoutePlanController;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_baidu_route_plan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        newBaiDuRoutePlanController = new NewBaiDuRoutePlanController(this);
    }



    @Override
    public void onPause() {
        super.onPause();
        newBaiDuRoutePlanController.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        newBaiDuRoutePlanController.onResume();

    }

    @Override
    public void onDestroy() {
       super.onDestroy();
        newBaiDuRoutePlanController.onDestroy();
    }
}
