package com.shandian.lu.Main.IndexFragment.RenRenWuLiu;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AboutCarSourceNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarLengthDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;
import com.zhyan.shandiankuaiyunlib.Bean.ZhuanXianWuliuCarSourceBean;
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
 * Created by az on 2017/4/27.
 */

public class RenRenWuLiuController extends BaseController{

    @BindView(R.id.rly_main_index_renrenwuliu_back)
    RelativeLayout rlyMainIndexRenRenWuLiuBack;
    @OnClick(R.id.rly_main_index_renrenwuliu_back)
    public void rlyMainIndexRenRenWuLiuBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.xrv_main_index_renrenwuliu)
    XRecyclerView xrvMainIndexRenRenWuLiu;



    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;

    @BindView(R.id.tv_main_index_renrenwuliu_content_reach)
    TextView tvMainIndexRenRenWuLiuContentReach;
    @BindView(R.id.tv_main_index_renrenwuliu_content_begin)
    TextView tvMainIndexRenRenWuLiuContentBegin;
    @BindView(R.id.tv_main_index_renrenwuliu_content_cartype)
    TextView tvMainIndexRenRenWuLiuContentCarType;
    @BindView(R.id.rly_main_index_renrenwuliu_content_cartype)
    RelativeLayout rlyMainIndexRenRenWuLiuContentCarType;
    @OnClick(R.id.rly_main_index_renrenwuliu_content_cartype)
    public void rlyMainIndexRenRenWuLiuContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainIndexRenRenWuLiuContentCarType).Build.build(activity);
        showDialog();
    }
    public void showDialog() {
        if (carTypeDialog != null && !carTypeDialog.isShowing())
            carTypeDialog.show();
    }

    public void dissmissDialog() {
        if (carTypeDialog != null && carTypeDialog.isShowing())
            carTypeDialog.dismiss();
    }
    @BindView(R.id.tv_main_index_renrenwuliu_content_carlength)
    TextView tvMainIndexRenRenWuLiuContentCarLength;
    @BindView(R.id.rly_main_index_renrenwuliu_content_carlength)
    RelativeLayout rlyMainIndexRenRenWuLiuContentCarLength;
    @OnClick(R.id.rly_main_index_renrenwuliu_content_carlength)
    public void rlyMainIndexRenRenWuLiuContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainIndexRenRenWuLiuContentCarLength).Build.build(activity);
        showDialog2();
    }
    public void showDialog2() {
        if (carLengthDialog != null && !carLengthDialog.isShowing())
            carLengthDialog.show();
    }

    @BindView(R.id.cb_main_index_renrenwuliu_bottom_all)
    CheckBox cbMainIndexRenRenWuLiuBottomAll;
    @OnClick(R.id.cb_main_index_renrenwuliu_bottom_all)
    public void cbMainIndexRenRenWuLiuBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.rly_main_index_renrenwuliu_bottom_all)
    RelativeLayout rlyMainIndexRenRenWuLiuBottomAll;
    @OnClick(R.id.rly_main_index_renrenwuliu_bottom_all)
    public void rlyMainIndexRenRenWuLiuBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.cb_main_index_renrenwuliu_bottom_near)
    CheckBox cbMainIndexRenRenWuLiuBottomNear;
    @OnClick(R.id.cb_main_index_renrenwuliu_bottom_near)
    public void cbMainIndexRenRenWuLiuBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.rly_main_index_renrenwuliu_bottom_near)
    RelativeLayout rlyMainIndexRenRenWuLiuBottomNear;
    @OnClick(R.id.rly_main_index_renrenwuliu_bottom_near)
    public void rlyMainIndexRenRenWuLiuBottomNearOnclick(){
        isAllOrNear("near");
    }
    int page = 0;
    RenRenWuLiuXRVAdapter renRenWuLiuXRVAdapter;


    public RenRenWuLiuController(Activity activity1){
        activity = activity1;
        init();
        initXRV();
        isAllOrNear("all");
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
    private void initXRV(){
        List<ZhuanXianWuliuCarSourceBean.ContentBean> renRenWuLiuBeanList = new ArrayList<>();
         renRenWuLiuXRVAdapter = new RenRenWuLiuXRVAdapter(activity,renRenWuLiuBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvMainIndexRenRenWuLiu.setLayoutManager(layoutManager);
        xrvMainIndexRenRenWuLiu.setAdapter(renRenWuLiuXRVAdapter);
        xrvMainIndexRenRenWuLiu.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (page > 0) {
                    page--;
                    getDataFromNet(true);
                } else {
                    getDataFromNet(true);
                }
                xrvMainIndexRenRenWuLiu.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getDataFromNet(true);
                xrvMainIndexRenRenWuLiu.loadMoreComplete();
            }
        });
   /*     DividerLine dividerLine = new DividerLine();
        dividerLine.setColor(R.color.color_main_index_renrenwuliu_xrv_item_split_line);
        dividerLine.setSize(1);*/
    }










    private Map<String,String> getParam(){
        Map<String,String> paramMap = new HashMap<>();
        String set_name = tvMainIndexRenRenWuLiuContentBegin.getText().toString().trim();
        if((set_name == null)||(set_name.equals("出发地"))){
            set_name = "";
        }else {
            set_name = set_name.replaceAll(" ", "");
        }
        paramMap.put("set_name",set_name);
        String out_name = tvMainIndexRenRenWuLiuContentReach.getText().toString().trim();
        if((out_name == null)||(out_name.equals("目的地"))){
            out_name = "";
        }else {
            out_name = out_name.replace(" ", "");
        }
        paramMap.put("out_name",out_name);
        String car_type = tvMainIndexRenRenWuLiuContentCarType.getText().toString().trim();
        if((car_type == null)||(car_type.equals("车型"))){
            car_type = "";
        }else {
            car_type = car_type.replaceAll(" ", "");
        }
        paramMap.put("car_type",car_type);
        String car_lange = tvMainIndexRenRenWuLiuContentCarLength.getText().toString().trim();
        if((car_lange == null)||(car_lange.equals("车长"))){
            car_lange = "";
        }else {
            car_lange = car_lange.replaceAll(" ", "");
        }
        paramMap.put("car_lange",car_lange);
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentCity = xcCacheManager.readCache(xcCacheSaveName.currentCity);
        if(currentCity != null){
            currentCity = currentCity.replaceAll(" ","").trim();

        }else {
            currentCity = "";
        }
        paramMap.put("city_name",currentCity);
        paramMap.put("type_name","人人快递");
        paramMap.put("page",page+"");
        return paramMap;
    }


    public void getDataFromNet(boolean isXRVLoding){
        if(!isXRVLoding) {
            renRenWuLiuXRVAdapter.clean();
        }
        AboutCarSourceNetWork aboutCarSourceNetWork = new AboutCarSourceNetWork();
        aboutCarSourceNetWork.getZhuanXianWuLiuCarSourceFromNet(getParam(), new Observer<ZhuanXianWuliuCarSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ZhuanXianWuliuCarSourceBean zhuanXianWuliuCarSourceBean) {
                if((zhuanXianWuliuCarSourceBean.getStatus() == 0)&&(zhuanXianWuliuCarSourceBean.getContent() != null)){
                    renRenWuLiuXRVAdapter.setAdapter(zhuanXianWuliuCarSourceBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getNearBy(){
        renRenWuLiuXRVAdapter.clean();
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
        String type_name = "人人快递";
        String type = "0";
        AboutCarSourceNetWork aboutCarSourceNetWork = new AboutCarSourceNetWork();
        aboutCarSourceNetWork.getZhuanXianWuLiuCarSourceNearByFromNet(lon, lat, type_name, type, new Observer<ZhuanXianWuliuCarSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ZhuanXianWuliuCarSourceBean zhuanXianWuliuCarSourceBean) {
                if((zhuanXianWuliuCarSourceBean.getStatus() == 0)&&(zhuanXianWuliuCarSourceBean.getContent() != null)) {
                    renRenWuLiuXRVAdapter.setAdapter(zhuanXianWuliuCarSourceBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void isAllOrNear(String type){
        switch (type){
            case "all":
                if(!cbMainIndexRenRenWuLiuBottomAll.isChecked()) {
                    cbMainIndexRenRenWuLiuBottomAll.setChecked(true);
                }
                if(cbMainIndexRenRenWuLiuBottomNear.isChecked()) {
                    cbMainIndexRenRenWuLiuBottomNear.setChecked(false);
                }
                getDataFromNet(false);
                break;
            case "near":
                if(cbMainIndexRenRenWuLiuBottomAll.isChecked()) {
                    cbMainIndexRenRenWuLiuBottomAll.setChecked(false);
                }
                if(!cbMainIndexRenRenWuLiuBottomNear.isChecked()) {
                    cbMainIndexRenRenWuLiuBottomNear.setChecked(true);
                }
                getNearBy();
                break;
        }
    }
}
