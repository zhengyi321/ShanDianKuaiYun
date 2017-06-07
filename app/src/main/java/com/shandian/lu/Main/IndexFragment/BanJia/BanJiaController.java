package com.shandian.lu.Main.IndexFragment.BanJia;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.WindowUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AboutHomeMovNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.HomeMovingBean;
import com.zhyan.shandiankuaiyunlib.Bean.StreetListBean;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/18.
 */

public class BanJiaController extends BaseController implements TextWatcher{


    @BindView(R.id.rly_main_index_banjia_back)
    RelativeLayout rlyMainIndexBanJiaBack;
    @OnClick(R.id.rly_main_index_banjia_back)
    public void rlyMainIndexBanJiaBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.xrv_main_index_banjia)
    XRecyclerView xrvMainIndexBanJia;






    @BindView(R.id.cb_main_index_banjia_bottom_all)
    CheckBox cbMainIndexBanJiaBottomAll;
    @OnClick(R.id.cb_main_index_banjia_bottom_all)
    public void cbMainIndexBanJiaBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.rly_main_index_banjia_bottom_all)
    RelativeLayout rlyMainIndexBanJiaBottomAll;
    @OnClick(R.id.rly_main_index_banjia_bottom_all)
    public void rlyMainIndexBanJiaBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.cb_main_index_banjia_bottom_near)
    CheckBox cbMainIndexBanJiaBottomNear;
    @OnClick(R.id.cb_main_index_banjia_bottom_near)
    public void cbMainIndexBanJiaBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.rly_main_index_banjia_bottom_near)
    RelativeLayout rlyMainIndexBanJiaBottomNear;
    @OnClick(R.id.rly_main_index_banjia_bottom_near)
    public void rlyMainIndexBanJiaBottomNearOnclick(){
        isAllOrNear("near");
    }












    PopupWindow popwindow;
    View view,view1;
    int cityStatus = 0;
    int leiXingStatus = 0;
    int page = 0;
    @BindView(R.id.iv_main_index_banjia_tab_splite_line)
    ImageView ivMainIndexBanJiaTabSpliteLine;

    @BindView(R.id.tv_main_index_banjia_city)
    TextView tvMainIndexBanJiaCity;
    @BindView(R.id.iv_main_index_banjia_city)
    ImageView ivMainIndexBanJiaCity;

    @BindView(R.id.rly_main_index_banjia_city)
    RelativeLayout rlyMainIndexBanJiaCity;
    @OnClick(R.id.rly_main_index_banjia_city)
    public void rlyMainIndexBanJiaCityOnclick(){
        popupType("city");
    }

    @BindView(R.id.tv_main_index_banjia_leixing)
    TextView tvMainIndexBanJiaLeiXing;
    @BindView(R.id.iv_main_index_banjia_leixing)
    ImageView ivMainIndexBanJiaLeiXing;

    @BindView(R.id.rly_main_index_banjia_leixing)
    RelativeLayout rlyMainIndexBanJiaLeiXing;
    @OnClick(R.id.rly_main_index_banjia_leixing)
    public void rlyMainIndexBanJiaLeiXingOnclick(){
        popupType("leixing");
    }

    RecyclerView leiXingRV;
    RecyclerView cityRV;
    RecyclerView areaRV;
    PopupWindow cityPopup;
    BanJiaLeiXingRVAdapter banJiaLeiXingRVAdapter;
    BanJiaCityAreaRVAdapter banJiaCityAreaRVAdapter;
    BanJiaCityRVAdapter banJiaCityRVAdapter;
    BanJiaXRVAdapter banJiaXRVAdapter;
    public BanJiaController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initCity();
        initLeiXingViews();
        initCityViews();
        initXRV();

        tvMainIndexBanJiaCity.addTextChangedListener(this);
        tvMainIndexBanJiaLeiXing.addTextChangedListener(this);
    }
    private void initCity(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String name = xcCacheManager.readCache(xcCacheSaveName.currentCity);
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","").trim();
        }
        tvMainIndexBanJiaCity.setText(name);
    }
    private void initXRV(){
        List<HomeMovingBean.ContentBean.CarInfoBean> carInfoBeanList = new ArrayList<>();
        banJiaXRVAdapter = new BanJiaXRVAdapter(activity,carInfoBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        xrvMainIndexBanJia.setAdapter(banJiaXRVAdapter);
        xrvMainIndexBanJia.setLayoutManager(linearLayoutManager);
        getDataFromNet(false);
        xrvMainIndexBanJia.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if(page > 0){
                    page--;
                    getDataFromNet(true);
                }else{
                    getDataFromNet(true);
                }

            }

            @Override
            public void onLoadMore() {
                page++;
                getDataFromNet(true);

            }
        });
    }

    public void getDataFromNet(boolean isXRVLoding){
        if(!isXRVLoding) {
            /*if(banJiaCityRVAdapter.getItemCount() > 20) {*/
            if(banJiaXRVAdapter != null) {
                banJiaXRVAdapter.clean();
            }
            /*};*/
        }
        AboutHomeMovNetWork aboutHomeMovNetWork = new AboutHomeMovNetWork();
        aboutHomeMovNetWork.getHomeMovingDataFromNet(getParamMap(), new Observer<HomeMovingBean>() {
            @Override
            public void onCompleted() {
                xrvMainIndexBanJia.refreshComplete();
                xrvMainIndexBanJia.loadMoreComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HomeMovingBean homeMovingBean) {
                /*Toast.makeText(activity,"homeMovingBean"+homeMovingBean.getStatus(),Toast.LENGTH_LONG).show();*/
                if((homeMovingBean.getStatus() == 0)&&(homeMovingBean.getContent().getCar_info().get(0) != null))  {

                    banJiaXRVAdapter.setAdapter(homeMovingBean.getContent().getCar_info());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String name = xcCacheManager.readCache(xcCacheSaveName.currentCity);
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","").trim();
        }
        paramMap.put("name",name);
        String type_name = tvMainIndexBanJiaLeiXing.getText().toString().trim();
        if((type_name == null)||(type_name.equals("类型"))){
            type_name = "";
        }else{
            type_name = type_name.replaceAll(" ","");
        }
        paramMap.put("type_name",type_name);
        String city_name = tvMainIndexBanJiaCity.getText().toString();
        if(city_name == null){
            city_name = "";
        }else{
            city_name = city_name.replaceAll(" ","").trim();
        }
        paramMap.put("city_name",city_name);
        String type = "1";
        paramMap.put("type",type);
        paramMap.put("page",page+"");


        return paramMap;
    }

    private void initLeiXingViews(){
        WindowUtils windowUtils = new WindowUtils(activity);
        view= LayoutInflater.from(activity).inflate(R.layout.popupwindow_main_index_banjia_leixing_downdrop_lly, null);
        leiXingRV = (RecyclerView) view.findViewById(R.id.rv_pop_main_index_banjia_leixing_down) ;
        popwindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,(int) (windowUtils.getWindowHeight()*0.4));
    /*    popwindow.setBackgroundDrawable(new BitmapDrawable());*/
        popwindow.setAnimationStyle(R.style.PopupWindowAnimation);
        popwindow.setOutsideTouchable(false);
        popwindow.setFocusable(false);
        popwindow.setTouchable(true);

        popwindow.update();
        List<String> stringList = new ArrayList<>();
        stringList.add("居民搬家");
        stringList.add("公司搬家");
        stringList.add("小型搬家");
        stringList.add("长途搬家货运");
        stringList.add("空调移机");
        stringList.add("设备拆迁");
        stringList.add("起重吊装");
        stringList.add("搬家搬场");
        stringList.add("钢琴搬运");
        stringList.add("家具拆装");
        stringList.add("国际搬家");
        banJiaLeiXingRVAdapter = new BanJiaLeiXingRVAdapter(activity,stringList,tvMainIndexBanJiaLeiXing,popwindow,ivMainIndexBanJiaLeiXing,leiXingStatus);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leiXingRV.setLayoutManager(linearLayoutManager);
        leiXingRV.setAdapter( banJiaLeiXingRVAdapter);
    }

    private void initCityViews(){
        List<StreetListBean.ContentBean.CityBean> cityBeen = new ArrayList<>();
        List<StreetListBean.ContentBean.CityBean.TreaBean> treaBeen = new ArrayList<>();
        cityBeen.add(new StreetListBean.ContentBean.CityBean());
        cityBeen.add(new StreetListBean.ContentBean.CityBean());
        cityBeen.add(new StreetListBean.ContentBean.CityBean());
        cityBeen.add(new StreetListBean.ContentBean.CityBean());
        WindowUtils windowUtils = new WindowUtils(activity);
        view1= LayoutInflater.from(activity).inflate(R.layout.popupwindow_main_index_banjia_city_downdrop_lly, null);
        cityRV = (RecyclerView) view1.findViewById(R.id.rv_pop_main_index_banjia_city) ;
        areaRV = (RecyclerView) view1.findViewById(R.id.rv_pop_main_index_banjia_city_area) ;
        cityPopup = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT,(int) (windowUtils.getWindowHeight()*0.4));
    /*    popwindow.setBackgroundDrawable(new BitmapDrawable());*/
        cityPopup.setAnimationStyle(R.style.PopupWindowAnimation);
        cityPopup.setOutsideTouchable(false);
        cityPopup.setFocusable(false);
        cityPopup.setTouchable(true);
        cityPopup.update();

        banJiaCityAreaRVAdapter = new BanJiaCityAreaRVAdapter(activity,treaBeen,tvMainIndexBanJiaCity,cityPopup,ivMainIndexBanJiaCity,cityStatus);
        banJiaCityRVAdapter = new BanJiaCityRVAdapter(activity,cityBeen,tvMainIndexBanJiaCity,banJiaCityAreaRVAdapter,ivMainIndexBanJiaCity,cityPopup,cityStatus);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        cityRV.setAdapter(banJiaCityRVAdapter);
        cityRV.setLayoutManager(linearLayoutManager);
        areaRV.setAdapter(banJiaCityAreaRVAdapter);
        areaRV.setLayoutManager(linearLayoutManager1);
        getCityDataFromNet();
    }

    public void getCityDataFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String city = xcCacheManager.readCache(xcCacheSaveName.currentCity);
        if(city == null){
            return;
        }
        AboutHomeMovNetWork aboutHomeMovNetWork = new AboutHomeMovNetWork();
        aboutHomeMovNetWork.getStreetListFromNet("2", city, new Observer<StreetListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               /* Toast.makeText(activity,"Throwable"+e,Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNext(StreetListBean streetListBean) {
               /* Toast.makeText(activity,streetListBean.getMsg(),Toast.LENGTH_LONG).show();*/
                if(streetListBean.getStatus() == 0){
                    banJiaCityRVAdapter.setAdapter(streetListBean.getContent().getCity());
                }
            }
        });
    }

   /* private void expandPopView() {

        if (cbMainIndexBanJiaCity.isChecked()||cbMainIndexBanJiaLeiXing.isChecked()) {
            if (!popwindow.isShowing()) {
                showPopView();
            } else {
                popwindow.dismiss();
            }
        } else {
            if (popwindow.isShowing()) {
                popwindow.dismiss();
            }
        }

    }*/
    public void showLeiXingPopView(){
        if (popwindow.getContentView() !=view) {
            popwindow.setContentView(view);
        }
        popwindow.showAsDropDown(ivMainIndexBanJiaTabSpliteLine, 0, 0);

    }
    public  void showCityPopView(){
        if (cityPopup.getContentView() !=view1) {
            cityPopup.setContentView(view1);
        }
        cityPopup.showAsDropDown(ivMainIndexBanJiaTabSpliteLine, 0, 0);
    }
    private void popupType(String type){
        switch (type){
            case "city":
                if(cityStatus == 0) {
                    cityStatus = 1;
                }else{
                    cityStatus = 0;
                }
                if(cityStatus == 0) {
                    leiXingStatus = 0;
                    popwindow.dismiss();
                    cityPopup.dismiss();
                    tvMainIndexBanJiaCity.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
                    ivMainIndexBanJiaCity.setImageResource(R.mipmap.black_down);
                    tvMainIndexBanJiaLeiXing.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
                    ivMainIndexBanJiaLeiXing.setImageResource(R.mipmap.black_down);
                }else{
                    leiXingStatus = 0;
                    popwindow.dismiss();
                    showCityPopView();
                    tvMainIndexBanJiaCity.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_orange_word_textcolor));
                    ivMainIndexBanJiaCity.setImageResource(R.mipmap.orange_up);
                    tvMainIndexBanJiaLeiXing.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
                    ivMainIndexBanJiaLeiXing.setImageResource(R.mipmap.black_down);
                }
                break;
            case "leixing":

                if(leiXingStatus == 0) {
                    leiXingStatus = 1;
                }else{
                    leiXingStatus = 0;
                }
                if(leiXingStatus == 0) {
                    cityStatus = 0;
                    popwindow.dismiss();
                    cityPopup.dismiss();
                    tvMainIndexBanJiaCity.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
                    ivMainIndexBanJiaCity.setImageResource(R.mipmap.black_down);
                    tvMainIndexBanJiaLeiXing.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
                    ivMainIndexBanJiaLeiXing.setImageResource(R.mipmap.black_down);

                }else{
                    cityPopup.dismiss();
                    cityStatus = 0;
                    showLeiXingPopView();
                    tvMainIndexBanJiaCity.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
                    ivMainIndexBanJiaCity.setImageResource(R.mipmap.black_down);
                    tvMainIndexBanJiaLeiXing.setTextColor(activity.getResources().getColor(R.color.color_main_index_banjia_bottom_orange_word_textcolor));
                    ivMainIndexBanJiaLeiXing.setImageResource(R.mipmap.orange_up);

                }



                break;
        }
    }



    private void isAllOrNear(String type){
        switch (type){
            case "all":
                if(!cbMainIndexBanJiaBottomAll.isChecked()) {
                    cbMainIndexBanJiaBottomAll.setChecked(true);
                }
                if(cbMainIndexBanJiaBottomNear.isChecked()) {
                    cbMainIndexBanJiaBottomNear.setChecked(false);
                }
                getDataFromNet(false);
                break;
            case "near":
                if(cbMainIndexBanJiaBottomAll.isChecked()) {
                    cbMainIndexBanJiaBottomAll.setChecked(false);
                }
                if(!cbMainIndexBanJiaBottomNear.isChecked()) {
                    cbMainIndexBanJiaBottomNear.setChecked(true);
                }
                getNearBy();
                break;
        }
    }

    private void getNearBy(){
        banJiaXRVAdapter.clean();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String lat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        if(lat == null){
            return;
        }
        String lon = xcCacheManager.readCache(xcCacheSaveName.currentlon);
        if(lon == null){
            return;
        }
        String type_name = tvMainIndexBanJiaLeiXing.getText().toString().trim();
        if((type_name == null)||(type_name.equals("类型"))){
            type_name = "";
        }else{
            type_name = type_name.replaceAll(" ","");
        }

        String type = "0";
        AboutHomeMovNetWork aboutHomeMovNetWork = new AboutHomeMovNetWork();
        aboutHomeMovNetWork.getNearByFromNet(lon, lat, type_name, type, new Observer<HomeMovingBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HomeMovingBean homeMovingBean) {
                if((homeMovingBean.getStatus() == 0)&&(homeMovingBean.getContent().getCar_info() != null)) {
                    banJiaXRVAdapter.setAdapter(homeMovingBean.getContent().getCar_info());
                }else{

                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        getDataFromNet(false);
    }
/* Toast.makeText(activity,carSourceNearByBean.getMsg(),Toast.LENGTH_LONG).show();*/

}
