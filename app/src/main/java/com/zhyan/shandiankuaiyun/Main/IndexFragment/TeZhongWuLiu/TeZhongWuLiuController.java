package com.zhyan.shandiankuaiyun.Main.IndexFragment.TeZhongWuLiu;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.ReleaseFragment.TeZhongYunShu.TeZhongYunShuActivity;
import com.zhyan.shandiankuaiyun.Main.ReleaseFragment.TeZhongYunShu.TeZhongYunShuController;
import com.zhyan.shandiankuaiyun.NetWork.AboutCarSourceNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarLengthDialog;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarTypeDialog;
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
 * Created by az on 2017/5/18.
 */

public class TeZhongWuLiuController extends BaseController{
    @BindView(R.id.rly_main_index_tezhongwuliu_back)
    RelativeLayout rlyMainIndexTeZhongWuLiuBack;
    @OnClick(R.id.rly_main_index_tezhongwuliu_back)
    public void  rlyMainIndexTeZhongWuLiuBackOnclick(){
        activity.finish();
    }






    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;

    @BindView(R.id.tv_main_index_tezhongwuliu_content_reach)
    TextView tvMainIndexTeZhongWuLiuContentReach;
    @BindView(R.id.tv_main_index_tezhongwuliu_content_begin)
    TextView tvMainIndexTeZhongWuLiuContentBegin;
    @BindView(R.id.tv_main_index_tezhongwuliu_content_cartype)
    TextView tvMainIndexTeZhongWuLiuContentCarType;
    @BindView(R.id.rly_main_index_tezhongwuliu_content_cartype)
    RelativeLayout rlyMainIndexTeZhongWuLiuContentCarType;
    @OnClick(R.id.rly_main_index_tezhongwuliu_content_cartype)
    public void rlyMainIndexTeZhongWuLiuContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainIndexTeZhongWuLiuContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_index_tezhongwuliu_content_carlength)
    TextView tvMainIndexTeZhongWuLiuContentCarLength;
    @BindView(R.id.rly_main_index_tezhongwuliu_content_carlength)
    RelativeLayout rlyMainIndexTeZhongWuLiuContentCarLength;
    @OnClick(R.id.rly_main_index_tezhongwuliu_content_carlength)
    public void rlyMainIndexTeZhongWuLiuContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainIndexTeZhongWuLiuContentCarLength).Build.build(activity);
        showDialog2();
    }
    public void showDialog2() {
        if (carLengthDialog != null && !carLengthDialog.isShowing())
            carLengthDialog.show();
    }

    @BindView(R.id.cb_main_index_tezhongwuliu_bottom_all)
    CheckBox cbMainIndexTeZhongWuLiuBottomAll;
    @OnClick(R.id.cb_main_index_tezhongwuliu_bottom_all)
    public void cbMainIndexTeZhongWuLiuBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.rly_main_index_tezhongwuliu_bottom_all)
    RelativeLayout rlyMainIndexTeZhongWuLiuBottomAll;
    @OnClick(R.id.rly_main_index_tezhongwuliu_bottom_all)
    public void rlyMainIndexTeZhongWuLiuBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.cb_main_index_tezhongwuliu_bottom_near)
    CheckBox cbMainIndexTeZhongWuLiuBottomNear;
    @OnClick(R.id.cb_main_index_tezhongwuliu_bottom_near)
    public void cbMainIndexTeZhongWuLiuBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.rly_main_index_tezhongwuliu_bottom_near)
    RelativeLayout rlyMainIndexTeZhongWuLiuBottomNear;
    @OnClick(R.id.rly_main_index_tezhongwuliu_bottom_near)
    public void rlyMainIndexTeZhongWuLiuBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.xrv_main_index_tezhongwuliu)
    XRecyclerView xrvMainIndexTeZhongWuLiu;
    int page = 0;

    TeZhongWuLiuXRVAdapter teZhongWuLiuXRVAdapter;







    public TeZhongWuLiuController(Activity activity1){
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


        List<ZhuanXianWuliuCarSourceBean.ContentBean> stringList = new ArrayList<>();
        teZhongWuLiuXRVAdapter = new TeZhongWuLiuXRVAdapter(activity, stringList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        /*DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setColor(R.color.color_main_index_zhuanxianwuliu_xrv_item_split_line);
        dividerLine.setSize(1);*/
        xrvMainIndexTeZhongWuLiu.setAdapter(teZhongWuLiuXRVAdapter);
        xrvMainIndexTeZhongWuLiu.setLayoutManager(layoutManager);

        xrvMainIndexTeZhongWuLiu.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (page > 0) {
                    page--;
                    getDataFromNet(true);
                } else {
                    getDataFromNet(true);
                }
                xrvMainIndexTeZhongWuLiu.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getDataFromNet(true);
                xrvMainIndexTeZhongWuLiu.loadMoreComplete();
            }
        });
    }

    private Map<String,String> getParam(){
        Map<String,String> paramMap = new HashMap<>();
        String set_name = tvMainIndexTeZhongWuLiuContentBegin.getText().toString().trim();
        if((set_name == null)||(set_name.equals("出发地"))){
            set_name = "";
        }else {
            set_name = set_name.replaceAll(" ", "");
        }
        paramMap.put("set_name",set_name);
        String out_name = tvMainIndexTeZhongWuLiuContentReach.getText().toString().trim();
        if((out_name == null)||(out_name.equals("目的地"))){
            out_name = "";
        }else {
            out_name = out_name.replace(" ", "");
        }
        paramMap.put("out_name",out_name);
        String car_type = tvMainIndexTeZhongWuLiuContentCarType.getText().toString().trim();
        if((car_type == null)||(car_type.equals("车型"))){
            car_type = "";
        }else {
            car_type = car_type.replaceAll(" ", "");
        }
        paramMap.put("car_type",car_type);
        String car_lange = tvMainIndexTeZhongWuLiuContentCarLength.getText().toString().trim();
        if((car_lange == null)||(car_lange.equals("车长"))){
            car_lange = "";
        }else {
            car_lange = car_lange.replaceAll(" ", "");
        }
        paramMap.put("car_lange",car_lange);
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentCity = xcCacheManager.readCache(xcCacheSaveName.currentCity).trim();
        if(currentCity != null){
            currentCity = currentCity.replaceAll(" ","");

        }else {
            currentCity = "";
        }
        paramMap.put("city_name",currentCity);
        paramMap.put("type_name","特种运输");
        paramMap.put("page",page+"");
        return paramMap;
    }


    public void getDataFromNet(boolean isXRVLoding){
        if(!isXRVLoding) {
            teZhongWuLiuXRVAdapter.clean();
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
                    teZhongWuLiuXRVAdapter.setAdapter(zhuanXianWuliuCarSourceBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getNearBy(){
        teZhongWuLiuXRVAdapter.clean();
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
        String type_name = "特种运输";
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
                    teZhongWuLiuXRVAdapter.setAdapter(zhuanXianWuliuCarSourceBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void isAllOrNear(String type){
        switch (type){
            case "all":
                if(!cbMainIndexTeZhongWuLiuBottomAll.isChecked()) {
                    cbMainIndexTeZhongWuLiuBottomAll.setChecked(true);
                }
                if(cbMainIndexTeZhongWuLiuBottomNear.isChecked()) {
                    cbMainIndexTeZhongWuLiuBottomNear.setChecked(false);
                }
                getDataFromNet(false);
                break;
            case "near":
                if(cbMainIndexTeZhongWuLiuBottomAll.isChecked()) {
                    cbMainIndexTeZhongWuLiuBottomAll.setChecked(false);
                }
                if(!cbMainIndexTeZhongWuLiuBottomNear.isChecked()) {
                    cbMainIndexTeZhongWuLiuBottomNear.setChecked(true);
                }
                getNearBy();
                break;
        }
    }
}
