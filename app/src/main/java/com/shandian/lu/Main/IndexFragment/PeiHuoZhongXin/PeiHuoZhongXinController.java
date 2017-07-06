package com.shandian.lu.Main.IndexFragment.PeiHuoZhongXin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shandian.lu.Main.IndexFragment.NewCheYuanList.CheYuanListActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AboutCarSourceNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarLengthDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceSelectBean;
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
 * Created by az on 2017/4/28.
 */

public class PeiHuoZhongXinController extends BaseController {
    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_main_index_peihuozhongxin_back)
    RelativeLayout rlyMainIndexPeiHuoZhongXinBack;
    @OnClick(R.id.rly_main_index_peihuozhongxin_back)
    public void rlyMainIndexPeiHuoZhongXinBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.rly_main_index_peihuozhongxin_change)
    RelativeLayout rlyMainIndexPeiHuoZhongXinChange;
    @OnClick(R.id.rly_main_index_peihuozhongxin_change)
    public void rlyMainIndexPeiHuoZhongXinChangeOnclick(){
        Intent intent = new Intent(activity, CheYuanListActivity.class);
        intent.putExtra("typeName","1");
        activity.startActivity(intent);
        activity.finish();
    }

    @BindView(R.id.tv_main_index_peihuozhongxin_content_reach)
    TextView tvMainIndexPeiHuoZhongXinContentReach;
    @BindView(R.id.tv_main_index_peihuozhongxin_content_begin)
    TextView tvMainIndexPeiHuoZhongXinContentBegin;
    @BindView(R.id.tv_main_index_peihuozhongxin_content_cartype)
    TextView tvMainIndexPeiHuoZhongXinContentCarType;
    @BindView(R.id.rly_main_index_peihuozhongxin_content_cartype)
    RelativeLayout rlyMainIndexPeiHuoZhongXinContentCarType;
    @OnClick(R.id.rly_main_index_peihuozhongxin_content_cartype)
    public void rlyMainIndexPeiHuoZhongXinContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainIndexPeiHuoZhongXinContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_index_peihuozhongxin_content_carlength)
    TextView tvMainIndexPeiHuoZhongXinContentCarLength;
    @BindView(R.id.rly_main_index_peihuozhongxin_content_carlength)
    RelativeLayout rlyMainIndexPeiHuoZhongXinContentCarLength;
    @OnClick(R.id.rly_main_index_peihuozhongxin_content_carlength)
    public void rlyMainIndexPeiHuoZhongXinContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainIndexPeiHuoZhongXinContentCarLength).Build.build(activity);
        showDialog2();
    }
    public void showDialog2() {
        if (carLengthDialog != null && !carLengthDialog.isShowing())
            carLengthDialog.show();
    }

    @BindView(R.id.cb_main_index_peihuozhongxin_bottom_all)
    CheckBox cbMainIndexPeiHuoZhongXinBottomAll;
    @OnClick(R.id.cb_main_index_peihuozhongxin_bottom_all)
    public void cbMainIndexPeiHuoZhongXinBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.rly_main_index_peihuozhongxin_bottom_all)
    RelativeLayout rlyMainIndexPeiHuoZhongXinBottomAll;
    @OnClick(R.id.rly_main_index_peihuozhongxin_bottom_all)
    public void rlyMainIndexPeiHuoZhongXinBottomAllOnclick(){
        isAllOrNear("all");
    }
    @BindView(R.id.cb_main_index_peihuozhongxin_bottom_near)
    CheckBox cbMainIndexPeiHuoZhongXinBottomNear;
    @OnClick(R.id.cb_main_index_peihuozhongxin_bottom_near)
    public void cbMainIndexPeiHuoZhongXinBottomNearOnclick(){
        isAllOrNear("near");
    }
    @BindView(R.id.rly_main_index_peihuozhongxin_bottom_near)
    RelativeLayout rlyMainIndexPeiHuoZhongXinBottomNear;
    @OnClick(R.id.rly_main_index_peihuozhongxin_bottom_near)
    public void rlyMainIndexPeiHuoZhongXinBottomNearOnclick(){
        isAllOrNear("near");
    }



    PeiHuoZhongXinXRVAdapter adapter;
    @BindView(R.id.xrv_main_index_peihuozhongxin)
    XRecyclerView xrvMainIndexPeiHuoZhongXin;
    int page = 0;




    public PeiHuoZhongXinController(Activity activity1){
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



        List<CarSourceSelectBean.ContentBean> stringList = new ArrayList<>();
         adapter = new PeiHuoZhongXinXRVAdapter(activity,stringList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        xrvMainIndexPeiHuoZhongXin.setAdapter(adapter);
        xrvMainIndexPeiHuoZhongXin.setLayoutManager(layoutManager);
        xrvMainIndexPeiHuoZhongXin.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if(page > 0){
                    page--;
                    getDataFromNet(true);
                }else{
                    getDataFromNet(true);
                }
                xrvMainIndexPeiHuoZhongXin.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getDataFromNet(true);
                xrvMainIndexPeiHuoZhongXin.loadMoreComplete();
            }
        });
        /*
        xrvMainIndexPeiHuoZhongXin.addItemDecoration(dividerLine);*/
    }

    private Map<String,String> getParam(){
        Map<String,String> paramMap = new HashMap<>();
        String set_name = tvMainIndexPeiHuoZhongXinContentBegin.getText().toString().trim();
        if((set_name == null)||(set_name.equals("出发地"))){
            set_name = "";
        }else {
            set_name = set_name.replaceAll(" ", "");
        }
        paramMap.put("set_name",set_name);
        String out_name = tvMainIndexPeiHuoZhongXinContentReach.getText().toString().trim();
        if((out_name == null)||(out_name.equals("目的地"))){
            out_name = "";
        }else {
            out_name = out_name.replace(" ", "");
        }
        paramMap.put("out_name",out_name);
        String car_type = tvMainIndexPeiHuoZhongXinContentCarType.getText().toString().trim();
        if((car_type == null)||(car_type.equals("车型"))){
            car_type = "";
        }else {
            car_type = car_type.replaceAll(" ", "");
        }
        paramMap.put("car_type",car_type);
        String car_lange = tvMainIndexPeiHuoZhongXinContentCarLength.getText().toString().trim();
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
        paramMap.put("currentCity",currentCity);
        paramMap.put("page",page+"");
        return paramMap;
    }

    public void getDataFromNet(boolean isXRVLoding){
        if(!isXRVLoding) {
            adapter.clean();
        }
        AboutCarSourceNetWork aboutCarSourceNetWork = new AboutCarSourceNetWork();
        aboutCarSourceNetWork.getCarSourceFromNet(getParam(), new Observer<CarSourceSelectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            /*    Toast.makeText(activity,"Throwable"+e,Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNext(CarSourceSelectBean carSourceSelectBean) {
             /*   Toast.makeText(activity,carSourceSelectBean.getMsg()+"status:"+carSourceSelectBean.getStatus(),Toast.LENGTH_LONG).show();*/
                if((carSourceSelectBean.getStatus() == 0)&&(carSourceSelectBean.getContent() != null)) {
                    adapter.setAdapter(carSourceSelectBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void isAllOrNear(String type){
        switch (type){
            case "all":
                if(!cbMainIndexPeiHuoZhongXinBottomAll.isChecked()) {
                    cbMainIndexPeiHuoZhongXinBottomAll.setChecked(true);
                }
                if(cbMainIndexPeiHuoZhongXinBottomNear.isChecked()) {
                    cbMainIndexPeiHuoZhongXinBottomNear.setChecked(false);
                }
                getDataFromNet(false);
                break;
            case "near":
                if(cbMainIndexPeiHuoZhongXinBottomAll.isChecked()) {
                    cbMainIndexPeiHuoZhongXinBottomAll.setChecked(false);
                }
                if(!cbMainIndexPeiHuoZhongXinBottomNear.isChecked()) {
                    cbMainIndexPeiHuoZhongXinBottomNear.setChecked(true);
                }
                getNearBy();
                break;
        }
    }

    private void getNearBy(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String lat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        if(lat == null){
            return;
        }
        String lon = xcCacheManager.readCache(xcCacheSaveName.currentLon);
        if(lon == null){
            return;
        }
        String type_name = "";
        String type = "0";
        AboutCarSourceNetWork aboutCarSourceNetWork = new AboutCarSourceNetWork();
        aboutCarSourceNetWork.getCarSourceNearByFromNet(lon, lat, type_name, type, new Observer<CarSourceSelectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CarSourceSelectBean carSourceNearByBean) {
               /* Toast.makeText(activity,carSourceNearByBean.getMsg(),Toast.LENGTH_LONG).show();*/
                if((carSourceNearByBean.getStatus() == 0)&&(carSourceNearByBean.getContent() != null))  {
                    adapter.setAdapter(carSourceNearByBean.getContent());
                }else{
                    Toast.makeText(activity,"已经到底部了",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
