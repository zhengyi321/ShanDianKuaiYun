package com.shandian.lu.Main.IndexFragment.ZhengCheHuoYun;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AboutGoodsSourceNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.GoodsSourceBean;
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

public class ZhengCheHuoYunController extends BaseController {

    int page = 0;














    @BindView(R.id.rly_main_index_zhengchehuoyun_back)
    RelativeLayout rlyMainIndexZhengCheHuoYunBack;
    @OnClick(R.id.rly_main_index_zhengchehuoyun_back)
    public void rlyMainIndexZhengCheHuoYunBackOnclick(){
        activity.finish();
    }










    @BindView(R.id.cb_main_index_zhengchehuoyun_bottom_all)
    CheckBox cbMainIndexZhengCheHuoYunBottomAll;
    @OnClick(R.id.cb_main_index_zhengchehuoyun_bottom_all)
    public void cbMainIndexZhengCheHuoYunBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.rly_main_index_zhengchehuoyun_bottom_all)
    RelativeLayout rlyMainIndexZhengCheHuoYunBottomAll;
    @OnClick(R.id.rly_main_index_zhengchehuoyun_bottom_all)
    public void rlyMainIndexZhengCheHuoYunBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.cb_main_index_zhengchehuoyun_bottom_near)
    CheckBox cbMainIndexZhengCheHuoYunBottomNear;
    @OnClick(R.id.cb_main_index_zhengchehuoyun_bottom_near)
    public void cbMainIndexZhengCheHuoYunBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.rly_main_index_zhengchehuoyun_bottom_near)
    RelativeLayout rlyMainIndexZhengCheHuoYunBottomNear;
    @OnClick(R.id.rly_main_index_zhengchehuoyun_bottom_near)
    public void rlyMainIndexZhengCheHuoYunBottomNearOnclick(){
        isAllOrNear("near");
    }

    @BindView(R.id.tv_main_index_zhengchehuoyun_begin)
    TextView tvMainIndexZhengCheHuoYunBegin;


    @BindView(R.id.tv_main_index_zhengchehuoyun_reach)
    TextView tvMainIndexZhengCheHuoYunReach;

    @BindView(R.id.xrv_main_index_zhengchehuoyun)
    XRecyclerView xrvMainIndexZhengCheHuoYun;

    ZhengCheHuoYunXRVAdapter zhengCheHuoYunXRVAdapter;

    public ZhengCheHuoYunController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        isAllOrNear("all");
    }



    private void initXRV(){
        List<GoodsSourceBean.ContentBean> stringList = new ArrayList<>();
        zhengCheHuoYunXRVAdapter = new ZhengCheHuoYunXRVAdapter(activity,stringList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvMainIndexZhengCheHuoYun.setAdapter(zhengCheHuoYunXRVAdapter);
        xrvMainIndexZhengCheHuoYun.setLayoutManager(linearLayoutManager);
        xrvMainIndexZhengCheHuoYun.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if(page > 0){
                    page--;
                    getGoodsSourceFromNet(true);
                }else{
                    getGoodsSourceFromNet(true);
                }
                xrvMainIndexZhengCheHuoYun.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getGoodsSourceFromNet(true);
                xrvMainIndexZhengCheHuoYun.loadMoreComplete();
            }
        });
    }

    private Map<String,String> getParam(){
        Map<String,String> mapParam = new HashMap<>();
        String set_name = tvMainIndexZhengCheHuoYunBegin.getText().toString().trim();
        if((set_name == null)||(set_name.equals("出发地"))){
            set_name = "";
        }else {
            set_name = set_name.replaceAll(" ", "");
        }
        mapParam.put("set_name",set_name);
        String out_name = tvMainIndexZhengCheHuoYunReach.getText().toString().trim();
        if((out_name == null)||(out_name.equals("目的地"))){
            out_name = "";
        }else {
            out_name = out_name.replace(" ", "");
        }
        mapParam.put("out_name",out_name);
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentCity = xcCacheManager.readCache(xcCacheSaveName.currentCity).trim();
        if(currentCity != null){
            currentCity = currentCity.replaceAll(" ","");

        }else {
            currentCity = "";
        }
        mapParam.put("city_name",currentCity);
        mapParam.put("page",page+"");
        return mapParam;
    }




    private void isAllOrNear(String type){
        switch (type){
            case "all":
                if(!cbMainIndexZhengCheHuoYunBottomAll.isChecked()) {
                    cbMainIndexZhengCheHuoYunBottomAll.setChecked(true);
                }
                if(cbMainIndexZhengCheHuoYunBottomNear.isChecked()) {
                    cbMainIndexZhengCheHuoYunBottomNear.setChecked(false);
                }
                getGoodsSourceFromNet(false);
                break;
            case "near":
                if(cbMainIndexZhengCheHuoYunBottomAll.isChecked()) {
                    cbMainIndexZhengCheHuoYunBottomAll.setChecked(false);
                }
                if(!cbMainIndexZhengCheHuoYunBottomNear.isChecked()) {
                    cbMainIndexZhengCheHuoYunBottomNear.setChecked(true);
                }
                getNearBy();
                break;
        }
    }

    public void getGoodsSourceFromNet(boolean isXRVLoading){
        if(!isXRVLoading){
            zhengCheHuoYunXRVAdapter.clean();
        }
        AboutGoodsSourceNetWork aboutGoodsSourceNetWork = new AboutGoodsSourceNetWork();
        aboutGoodsSourceNetWork.getGoodsSourceFromNet(getParam(), new Observer<GoodsSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GoodsSourceBean goodsSourceBean) {
                if((goodsSourceBean.getStatus() == 0)&&(goodsSourceBean.getContent() != null)){
                    zhengCheHuoYunXRVAdapter.setAdapter(goodsSourceBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void getNearBy(){
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

        AboutGoodsSourceNetWork aboutGoodsSourceNetWork = new AboutGoodsSourceNetWork();
        aboutGoodsSourceNetWork.getGoodsSourceNearByFromNet(lon, lat, new Observer<GoodsSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GoodsSourceBean goodsSourceBean) {
                if((goodsSourceBean.getStatus() == 0)&&(goodsSourceBean.getContent() != null)){
                    zhengCheHuoYunXRVAdapter.setAdapter(goodsSourceBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
