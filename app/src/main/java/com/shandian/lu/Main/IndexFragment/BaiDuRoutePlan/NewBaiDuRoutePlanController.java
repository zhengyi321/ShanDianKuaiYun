package com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.mynewslayoutlib.Bean.NewSiJiLocBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaiDu.DrivingRouteOverlay;
import com.shandian.lu.BaiDu.OverlayManager;
import com.shandian.lu.BaiDu.RouteLineAdapter;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MainActivity;
import com.shandian.lu.Main.SplashActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Message;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by zhyan on 2017/6/11.
 */

public class NewBaiDuRoutePlanController extends BaseController implements BaiduMap.OnMapClickListener,OnGetRoutePlanResultListener {

    @BindView(R.id.rly_new_baidu_route_plan_back)
    RelativeLayout rlyNewBaiDuRoutePlanBack;
    @OnClick(R.id.rly_new_baidu_route_plan_back)
    public void rlyNewBaiDuRoutePlanBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_new_baidu_route_plan_topbar_title)
    TextView tvNewBaiDuRoutePlanTopBarTitle;

    @BindView(R.id.lly_new_baidu_route_plan_baddr)
    LinearLayout llyNewBaiDuRoutePlanBAddr;
    @BindView(R.id.tv_new_baidu_route_plan_baddr)
    TextView tvNewBaiDuRoutePlanBAddr;
    @BindView(R.id.lly_new_baidu_route_plan_eaddr)
    LinearLayout llyNewBaiDuRoutePlanEAddr;
    @BindView(R.id.tv_new_baidu_route_plan_eaddr)
    TextView tvNewBaiDuRoutePlanEAddr;
    String cheTouXiang;
    @BindView(R.id.mv_new_baidu_route_plan)
    MapView mvNewBaiDuRoutePlan;
    private BaiduMap mBaidumap;
    DrivingRouteResult nowResultdrive = null;
    RouteLine route = null;
    OverlayManager routeOverlay = null;
    boolean hasShownDialogue = false;
    boolean useDefaultIcon = false;
    // 搜索相关
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    int nowSearchType = -1 ; // 当前进行的检索，供判断浏览节点时结果使用。
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    private LatLng bLl,eLl;
    private boolean isFirstLoc = true;

    private  double i = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                  /*  Toast.makeText(activity,"this is message",Toast.LENGTH_LONG).show();*/
                    siJiLoc();
                    break;
            }
        }
    };

    private LocationMode mCurrentMode;
    public NewBaiDuRoutePlanController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        mBaidumap = mvNewBaiDuRoutePlan.getMap();
        mvNewBaiDuRoutePlan.showZoomControls(false);
        mBaidumap.setMyLocationEnabled(true);// 开启定位图层
        mBaidumap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 地图点击事件处理
        mBaidumap.setOnMapClickListener(this);
        initGetTitle();
        initRouteOverLay();
        initLLg();
        searchProcessByLLG(bLl,eLl);

/*        initSiJiLoc();*/
    }


    public class SiJiLocThread extends Thread{
        @Override
        public void run(){
            while(true){
                try {

                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void initGetTitle(){
        String title = activity.getIntent().getStringExtra("title");
        String bAddr = activity.getIntent().getStringExtra("baddr");
        cheTouXiang = activity.getIntent().getStringExtra("czTouXiang");
        if(cheTouXiang == null){
            cheTouXiang = "";
        }
        if(bAddr != null){
            tvNewBaiDuRoutePlanBAddr.setText(bAddr);
        }
        String eAddr = activity.getIntent().getStringExtra("eaddr");
        if(eAddr != null){
            tvNewBaiDuRoutePlanEAddr.setText(eAddr);
        }
        if((title != null)){
            if(title.equals("hcdw")) {
                tvNewBaiDuRoutePlanTopBarTitle.setText("货车定位");
                llyNewBaiDuRoutePlanBAddr.setVisibility(View.GONE);
                llyNewBaiDuRoutePlanEAddr.setVisibility(View.GONE);
                Thread siJiThread = new SiJiLocThread();
                siJiThread.start();
            }else if(title.equals("qdwz")){
                tvNewBaiDuRoutePlanTopBarTitle.setText("起点位置");
                llyNewBaiDuRoutePlanBAddr.setVisibility(View.VISIBLE);
                llyNewBaiDuRoutePlanEAddr.setVisibility(View.GONE);
                initSiJiLoc();
            }else if(title.equals("zdwz")){
                tvNewBaiDuRoutePlanTopBarTitle.setText("终点位置");
                llyNewBaiDuRoutePlanBAddr.setVisibility(View.GONE);
                llyNewBaiDuRoutePlanEAddr.setVisibility(View.VISIBLE);
                initSiJiLoc();
            }
        }else {
           /* Thread siJiThread = new SiJiLocThread();
            siJiThread.start();*/
        }


    }

    private void initSiJiLoc(){

/*        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("czTouXiang",cheTouXiang);*/
        String cheLat = activity.getIntent().getStringExtra("czlat");
        if((cheLat == null)||(cheLat.isEmpty())){
            cheLat = "0";
            return;
        }
        String cheLon = activity.getIntent().getStringExtra("czlon");
        if((cheLon == null)||(cheLon.isEmpty())){
            cheLon = "0";
        }
        String cheTouXiang = activity.getIntent().getStringExtra("czTouXiang");
        if(cheTouXiang == null){
            cheTouXiang = "";
        }
       /* Toast.makeText(activity,"chelat:"+cheLat+" chelon:"+cheLon+" chetouxiang:"+cheTouXiang,Toast.LENGTH_LONG).show();*/
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_che_loc_lly, null);
        RoundImageView touxiang = (RoundImageView) view.findViewById(R.id.riv_dialog_loc_touxiang);
        if(cheTouXiang.isEmpty()){
            touxiang.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage(cheTouXiang,touxiang, ImageLoaderUtils.options1);
     /*   BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromBitmap(getViewBitmap(view));*/

        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromView(view);

        double cLat = Double.parseDouble(cheLat);
        double cLon = Double.parseDouble(cheLon);
        /*定位蓝色点*/
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(100)
                // 此处设置开发者获取到的方向信息，顺时针0-360  lat:28.122708 lon:120.981995
                .direction(0).latitude(cLat)
                .longitude(cLon).build();
        mBaidumap.setMyLocationData(locData);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaidumap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                0, 0));
        LatLng lng = new LatLng(cLat,cLon);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(lng).zoom(16.0f);

        mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

       /* Toast.makeText(activity,"sijilat:"+cheLat+" sijilon:"+cheLon,Toast.LENGTH_LONG).show();*/


    }

    private void siJiLoc(){
        String czid = activity.getIntent().getStringExtra("czid");
        if(czid == null){
            czid = "";
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("login_id",czid);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.getSiJiLocFromNet(paramMap, new Observer<NewSiJiLocBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewSiJiLocBean newSiJiLocBean) {
               /* if(newSiJiLocBean.equals("0")){*/
                    String lat = newSiJiLocBean.getNr().getLat();
                    String lon = newSiJiLocBean.getNr().getLng();
                  /*  Toast.makeText(activity,"lat:"+lat,Toast.LENGTH_LONG).show();
                    Toast.makeText(activity,"lon:"+lat,Toast.LENGTH_LONG).show();*/

                    initSiJiLogo(lat,lon);
               /* }*/
            }
        });
    }
    private void initSiJiLogo(String lat,String lon){
      /*  Toast.makeText(activity,"this is sijiloc"+lat+" "+lon,3000).show();*/
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_che_loc_lly, null);
        RoundImageView touxiang = (RoundImageView) view.findViewById(R.id.riv_dialog_loc_touxiang);
        if(cheTouXiang.isEmpty()){
            touxiang.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage(cheTouXiang,touxiang, ImageLoaderUtils.options1);
     /*   BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromBitmap(getViewBitmap(view));*/

        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromView(view);

        double cLat = Double.parseDouble(lat);
        double cLon = Double.parseDouble(lon);

/*        cLat += i;
        cLon += i;
        i = (i+ 0.001);
        Toast.makeText(activity,"i:"+i,Toast.LENGTH_LONG).show();*/
        /*定位蓝色点*/
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(100)
                // 此处设置开发者获取到的方向信息，顺时针0-360  lat:28.122708 lon:120.981995
                .direction(0).latitude(cLat)
                .longitude(cLon).build();
        mBaidumap.setMyLocationData(locData);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaidumap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                0, 0));
        LatLng lng = new LatLng(cLat,cLon);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(lng).zoom(15.0f);
       /* builder.target(lng);*/

       if(isFirstLoc) {
           mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
           isFirstLoc = false;
       }
/*        mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));*/
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

    private void initLLg(){
        String blat = activity.getIntent().getStringExtra("blat");
        if(blat == null){
            blat = "0.0";
        }
        String blon = activity.getIntent().getStringExtra("blon");
        if(blon == null){
            blon = "0.0";
        }
        String elat = activity.getIntent().getStringExtra("elat");
        if(elat == null){
            elat = "0.0";
        }
        String elon = activity.getIntent().getStringExtra("elon");
        if(elon == null){
            elon = "0.0";
        }
       /* Toast.makeText(activity,"blat:"+blat+" blon"+blon+" elat"+elat+" elon"+elon,Toast.LENGTH_LONG).show();*/
        bLl = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
        eLl = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));
    }
    private void initRouteOverLay(){
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
    }
    private void searchProcessByLLG(LatLng begLlg ,LatLng endLlg){
        PlanNode stNode,enNode;
        stNode = PlanNode.withLocation(begLlg);
        enNode = PlanNode.withLocation(endLlg);

        mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));
    }


    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
           /* Toast.makeText(activity, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();*/
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;


            if (result.getRouteLines().size() > 1) {
                nowResultdrive = result;
                if (!hasShownDialogue) {
                    MyTransitDlg myTransitDlg = new MyTransitDlg(activity,
                            result.getRouteLines(),
                            RouteLineAdapter.Type.DRIVING_ROUTE);
                    myTransitDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            hasShownDialogue = false;

                        }
                    });
                    myTransitDlg.setOnItemInDlgClickLinster(new OnItemInDlgClickListener() {
                        public void onItemClick(int position) {
                            route = nowResultdrive.getRouteLines().get(position);
                            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaidumap);
                            mBaidumap.setOnMarkerClickListener(overlay);
                            routeOverlay = overlay;
                            overlay.setData(nowResultdrive.getRouteLines().get(position));
                            overlay.addToMap();
                            overlay.zoomToSpan();

                        }

                    });
                    myTransitDlg.show();
                    hasShownDialogue = true;
                }
            } else if (result.getRouteLines().size() == 1) {
                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaidumap);
                routeOverlay = overlay;
                mBaidumap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();


            } else {
                Log.d("route result", "结果数<0");

                return;
            }

        }
        /*initSiJiLoc();*/
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.start);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.end);
            }
            return null;
        }
    }
    // 供路线选择的Dialog
    class MyTransitDlg extends Dialog {

        private List<? extends RouteLine> mtransitRouteLines;
        private ListView transitRouteList;
        private RouteLineAdapter mTransitAdapter;

        OnItemInDlgClickListener onItemInDlgClickListener;

        public MyTransitDlg(Context context, int theme) {
            super(context, theme);
        }

        public MyTransitDlg(Context context, List<? extends RouteLine> transitRouteLines, RouteLineAdapter.Type
                type) {
            this(context, 0);
            mtransitRouteLines = transitRouteLines;
            mTransitAdapter = new RouteLineAdapter(context, mtransitRouteLines, type);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        @Override
        public void setOnDismissListener(OnDismissListener listener) {
            super.setOnDismissListener(listener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_transit_dialog);

            transitRouteList = (ListView) findViewById(R.id.transitList);
            transitRouteList.setAdapter(mTransitAdapter);

            transitRouteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onItemInDlgClickListener.onItemClick(position);

                    dismiss();
                    hasShownDialogue = false;
                }
            });
        }

        public void setOnItemInDlgClickLinster(OnItemInDlgClickListener itemListener) {
            onItemInDlgClickListener = itemListener;
        }

    }



    public void onPause() {
        mvNewBaiDuRoutePlan.onPause();

    }


    public void onResume() {
        mvNewBaiDuRoutePlan.onResume();

    }


    public void onDestroy() {
        if (mSearch != null) {
            mSearch.destroy();
        }
        mvNewBaiDuRoutePlan.onDestroy();


    }

    // 响应DLg中的List item 点击
    interface OnItemInDlgClickListener {
        public void onItemClick(int position);
    }
/*    private class MyDriverRouteOverlay extends RouteOverlay {




    }*/
}
