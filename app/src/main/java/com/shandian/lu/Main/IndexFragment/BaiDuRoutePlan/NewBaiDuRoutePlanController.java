package com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
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
import com.shandian.lu.BaiDu.DrivingRouteOverlay;
import com.shandian.lu.BaiDu.OverlayManager;
import com.shandian.lu.BaiDu.RouteLineAdapter;
import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        // 地图点击事件处理
        mBaidumap.setOnMapClickListener(this);
        initRouteOverLay();
        initLLg();
        searchProcessByLLG(bLl,eLl);
    }
    private void initLLg(){
        String blat = activity.getIntent().getStringExtra("blat");
        if(blat == null){
            blat = "";
        }
        String blon = activity.getIntent().getStringExtra("blon");
        if(blon == null){
            blon = "";
        }
        String elat = activity.getIntent().getStringExtra("elat");
        if(elat == null){
            elat = "";
        }
        String elon = activity.getIntent().getStringExtra("elon");
        if(elon == null){
            elon = "";
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
            Toast.makeText(activity, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
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
