package com.shandian.lu.Main.IndexFragment.ZhuanXianWuLiu;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shandian.lu.Main.IndexFragment.CheYuanList.CheYuanListActivity;
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
 * Created by az on 2017/5/12.
 */

public class ZhuanXianWuLiuController extends BaseController {

    @BindView(R.id.rly_main_index_zhuanxianwuliu_back)
    RelativeLayout rlyMainIndexZhuanXianWuLiuBack;
    @OnClick(R.id.rly_main_index_zhuanxianwuliu_back)
    public void rlyMainIndexZhuanXianWuLiuBackOnclick(){
        activity.finish();
    }


    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;

    @BindView(R.id.tv_main_index_zhuanxianwuliu_content_reach)
    TextView tvMainIndexZhuanXianWuLiuContentReach;
    @BindView(R.id.tv_main_index_zhuanxianwuliu_content_begin)
    TextView tvMainIndexZhuanXianWuLiuContentBegin;
    @BindView(R.id.tv_main_index_zhuanxianwuliu_content_cartype)
    TextView tvMainIndexZhuanXianWuLiuContentCarType;
    @BindView(R.id.rly_main_index_zhuanxianwuliu_content_cartype)
    RelativeLayout rlyMainIndexZhuanXianWuLiuContentCarType;
    @OnClick(R.id.rly_main_index_zhuanxianwuliu_content_cartype)
    public void rlyMainIndexZhuanXianWuLiuContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainIndexZhuanXianWuLiuContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_index_zhuanxianwuliu_content_carlength)
    TextView tvMainIndexZhuanXianWuLiuContentCarLength;
    @BindView(R.id.rly_main_index_zhuanxianwuliu_content_carlength)
    RelativeLayout rlyMainIndexZhuanXianWuLiuContentCarLength;
    @OnClick(R.id.rly_main_index_zhuanxianwuliu_content_carlength)
    public void rlyMainIndexZhuanXianWuLiuContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainIndexZhuanXianWuLiuContentCarLength).Build.build(activity);
        showDialog2();
    }
    public void showDialog2() {
        if (carLengthDialog != null && !carLengthDialog.isShowing())
            carLengthDialog.show();
    }

    @BindView(R.id.cb_main_index_zhuanxianwuliu_bottom_all)
    CheckBox cbMainIndexZhuanXianWuLiuBottomAll;
    @OnClick(R.id.cb_main_index_zhuanxianwuliu_bottom_all)
    public void cbMainIndexZhuanXianWuLiuBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.rly_main_index_zhuanxianwuliu_bottom_all)
    RelativeLayout rlyMainIndexZhuanXianWuLiuBottomAll;
    @OnClick(R.id.rly_main_index_zhuanxianwuliu_bottom_all)
    public void rlyMainIndexZhuanXianWuLiuBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.cb_main_index_zhuanxianwuliu_bottom_near)
    CheckBox cbMainIndexZhuanXianWuLiuBottomNear;
    @OnClick(R.id.cb_main_index_zhuanxianwuliu_bottom_near)
    public void cbMainIndexZhuanXianWuLiuBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.rly_main_index_zhuanxianwuliu_bottom_near)
    RelativeLayout rlyMainIndexZhuanXianWuLiuBottomNear;
    @OnClick(R.id.rly_main_index_zhuanxianwuliu_bottom_near)
    public void rlyMainIndexZhuanXianWuLiuBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.xrv_main_index_zhuanxianwuliu)
    XRecyclerView xrvMainIndexZhuanXianWuLiu;
    int page = 0;
    private int times = 0;
    private int refreshTime = 0;
    private ZhuanXianWuLiuXRVAdapter zhuanXianWuLiuXRVAdapter;
    List<ZhuanXianWuliuCarSourceBean.ContentBean> stringList;

    @BindView(R.id.rly_main_index_zhuanxianwuliu_change)
    RelativeLayout rlyMainIndexZhuanXianWuLiuChange;
    @OnClick(R.id.rly_main_index_zhuanxianwuliu_change)
    public void rlyMainIndexZhuanXianWuLiuChangeOnclick(){
        Intent intent = new Intent(activity, CheYuanListActivity.class);
        intent.putExtra("typeName","4");
        activity.startActivity(intent);
        activity.finish();
    }

    public ZhuanXianWuLiuController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        isAllOrNear("all");
    }


    private void initXRV() {


        stringList = new ArrayList<>();
        zhuanXianWuLiuXRVAdapter = new ZhuanXianWuLiuXRVAdapter(activity, stringList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        /*DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setColor(R.color.color_main_index_zhuanxianwuliu_xrv_item_split_line);
        dividerLine.setSize(1);*/
        xrvMainIndexZhuanXianWuLiu.setAdapter(zhuanXianWuLiuXRVAdapter);
        xrvMainIndexZhuanXianWuLiu.setLayoutManager(layoutManager);
        xrvMainIndexZhuanXianWuLiu.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (page > 0) {
                            page--;
                            getDataFromNet(true);
                        } else {
                            getDataFromNet(true);
                        }
                        xrvMainIndexZhuanXianWuLiu.refreshComplete();
                    }
                },1000);
            }
            @Override
            public void onLoadMore() {

                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            page++;
                            getDataFromNet(true);
                            xrvMainIndexZhuanXianWuLiu.loadMoreComplete();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            page++;
                            getDataFromNet(true);
                            xrvMainIndexZhuanXianWuLiu.setNoMore(true);

                        }
                    }, 1000);
                }
                times ++;




            }
        });
    }
        /*
        xrvMainIndexZhuanXianWuLiu.addItemDecoration(dividerLine);*/

    private Map<String,String> getParam(){
        Map<String,String> paramMap = new HashMap<>();
        String set_name = tvMainIndexZhuanXianWuLiuContentBegin.getText().toString().trim();
        if((set_name == null)||(set_name.equals("出发地"))){
            set_name = "";
        }else {
            set_name = set_name.replaceAll(" ", "");
        }
        paramMap.put("set_name",set_name);
        String out_name = tvMainIndexZhuanXianWuLiuContentReach.getText().toString().trim();
        if((out_name == null)||(out_name.equals("目的地"))){
            out_name = "";
        }else {
            out_name = out_name.replace(" ", "");
        }
        paramMap.put("out_name",out_name);
        String car_type = tvMainIndexZhuanXianWuLiuContentCarType.getText().toString().trim();
        if((car_type == null)||(car_type.equals("车型"))){
            car_type = "";
        }else {
            car_type = car_type.replaceAll(" ", "");
        }
        paramMap.put("car_type",car_type);
        String car_lange = tvMainIndexZhuanXianWuLiuContentCarLength.getText().toString().trim();
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
        paramMap.put("type_name","专线物流");
        paramMap.put("page",page+"");
        return paramMap;
    }
    public void getDataFromNet(boolean isXRVLoding){
        if(!isXRVLoding) {
            zhuanXianWuLiuXRVAdapter.clean();
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
                if((zhuanXianWuliuCarSourceBean.getStatus() == 0)&&(zhuanXianWuliuCarSourceBean.getContent() != null)) {
                    zhuanXianWuLiuXRVAdapter.setAdapter(zhuanXianWuliuCarSourceBean.getContent());
                }else{
                 /*   Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();*/
                }
            }
        });
    }

    private void isAllOrNear(String type){
        switch (type){
            case "all":
                if(!cbMainIndexZhuanXianWuLiuBottomAll.isChecked()) {
                    cbMainIndexZhuanXianWuLiuBottomAll.setChecked(true);
                }
                if(cbMainIndexZhuanXianWuLiuBottomNear.isChecked()) {
                    cbMainIndexZhuanXianWuLiuBottomNear.setChecked(false);
                }
                getDataFromNet(false);
                break;
            case "near":
                if(cbMainIndexZhuanXianWuLiuBottomAll.isChecked()) {
                    cbMainIndexZhuanXianWuLiuBottomAll.setChecked(false);
                }
                if(!cbMainIndexZhuanXianWuLiuBottomNear.isChecked()) {
                    cbMainIndexZhuanXianWuLiuBottomNear.setChecked(true);
                }
                getNearBy();
                break;
        }
    }

    private void getNearBy(){
        zhuanXianWuLiuXRVAdapter.clean();
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
        String type_name = "";
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
                if((zhuanXianWuliuCarSourceBean.getStatus() == 0)&&(zhuanXianWuliuCarSourceBean.getContent() != null)){
                    zhuanXianWuLiuXRVAdapter.setAdapter(zhuanXianWuliuCarSourceBean.getContent());
                }else{
                 /*   Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();*/
                }
            }
        });
    }
}
