package com.shandian.lu.Main.IndexFragment.NewHuoYuanList;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewHuoYuanListBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.ProgressStyle;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/10.
 */

public class HuoYuanListController extends BaseController {


    @BindView(R.id.xrv_new_huoyuanlist)
    XRecyclerView xrvNewHuoYuanList;
    @BindView(R.id.tv_new_huoyuanlist_title)
    TextView tvNewHuoYuanListTitle;
    @BindView(R.id.rly_new_huoyuanlist_back)
    RelativeLayout rlyNewHuoYuanListBack;
    @OnClick(R.id.rly_new_huoyuanlist_back)
    public void rlyNewHuoYuanListBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.rly_new_huoyuanlist_change)
    RelativeLayout rlyNewHuoYuanListChange;
    @OnClick(R.id.rly_new_huoyuanlist_change)
    public void rlyNewHuoYuanListChangeOnclick(){

    }

 /*   @BindView(R.id.rly_new_huoyuanlist_baddr)
    RelativeLayout rlyNewHuoYuanListBAddr;
    @OnClick(R.id.rly_new_huoyuanlist_baddr)
    public void rlyNewHuoYuanListBAddrOnclick(){

    }

    @BindView(R.id.rly_new_huoyuanlist_eaddr)
    RelativeLayout rlyNewHuoYuanListEAddr;
    @OnClick(R.id.rly_new_huoyuanlist_eaddr)
    public void rlyNewHuoYuanListEAddrOnclick(){

    }*/

    @BindView(R.id.pb_new_huoyuanlist)
    ProgressBar pbNewHuoYuanList;
    HuoYuanListXRVAdapter huoYuanListXRVAdapter;
    int page = 1;
    private int refreshTime = 0;
    private int times = 0;
    public List<NewHuoYuanListBean.NrBean.ListBean> huoYuanList,tempBeanList,adsBeanList,noAdsBeanList;



    public HuoYuanListController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        rlyNewHuoYuanListChange.setVisibility(View.INVISIBLE);
        getDataFromNet("1","0");
    }

    private void initXRV(){
        huoYuanList = new ArrayList<>();
        tempBeanList = new ArrayList<>();
        adsBeanList = new ArrayList<>();
        noAdsBeanList = new ArrayList<>();
        huoYuanList.add(new NewHuoYuanListBean.NrBean.ListBean());
        huoYuanList.add(new NewHuoYuanListBean.NrBean.ListBean());
        huoYuanList.add(new NewHuoYuanListBean.NrBean.ListBean());
        huoYuanList.add(new NewHuoYuanListBean.NrBean.ListBean());
        huoYuanListXRVAdapter = new HuoYuanListXRVAdapter(activity,huoYuanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvNewHuoYuanList.setLayoutManager(linearLayoutManager);
        xrvNewHuoYuanList.setAdapter(huoYuanListXRVAdapter);
        xrvNewHuoYuanList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrvNewHuoYuanList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrvNewHuoYuanList.setArrowImageView(R.drawable.iconfont_downgrey);

        /*View header = LayoutInflater.from(activity).inflate(R.layout.recyclerview_header, (ViewGroup)activity.findViewById(android.R.id.content),false);
        xrvNewHuoYuanList.addHeaderView(header);*/

        xrvNewHuoYuanList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        page=1;
                        getDataFromNet(page+"","0");

                        xrvNewHuoYuanList.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            page++;
                            getDataFromNet(page+"","0");
                            xrvNewHuoYuanList.loadMoreComplete();

                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            page++;
                            getDataFromNet(page+"","0");
                            xrvNewHuoYuanList.setNoMore(true);

                        }
                    }, 1000);
                }
                times ++;
            }
        });

    }

    public void getDataFromNet(String page,String typeName) {
        pbNewHuoYuanList.setVisibility(View.VISIBLE);
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentLat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        if (currentLat == null) {
            currentLat = "";
        }

        String currentLon = xcCacheManager.readCache(xcCacheSaveName.currentlon);
        if (currentLon == null) {
            currentLon = "";
        }
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getHuoListFromNet(typeName, currentLat, currentLon, page, new Observer<NewHuoYuanListBean>() {//0为全部货源
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanListBean newHuoYuanListBean) {
                pbNewHuoYuanList.setVisibility(View.GONE);
                if (newHuoYuanListBean.getStatus().equals("0")) {
                 /*   int size = newHuoYuanListBean.getNr().getList().size();
                    int count = tempBeanList.size();
                    noAdsBeanList.clear();
                    adsBeanList.clear();
                    for (int i = 0; i < count; i++) {
                        if (tempBeanList.get(i).getGg().equals("0")) {
                            noAdsBeanList.add(tempBeanList.get(i));
                        } else if (tempBeanList.get(i).getGg().equals("1")) {
                            adsBeanList.add(tempBeanList.get(i));
                        }
                    }
                    int adsCount = adsBeanList.size();
                    int noAdsCount = noAdsBeanList.size();
                    for (int i = 0; i < size; i++) {
                        String ggIds = newHuoYuanListBean.getNr().getList().get(i).getId();
                        if (newHuoYuanListBean.getNr().getList().get(i).equals("1")) {
                            adsBeanList.add(newHuoYuanListBean.getNr().getList().get(i));
                        } else if (newHuoYuanListBean.getNr().getList().get(i).getGg().equals("0")) {
                            noAdsBeanList.add(newHuoYuanListBean.getNr().getList().get(i));
                        }
                        for (int j = 0; j < adsCount; j++) {
                            String adsIds = adsBeanList.get(j).getId();
                            if (adsIds.equals(ggIds)) {
                                if (newHuoYuanListBean.getNr().getList().get(i).equals("1")) {
                                    adsBeanList.remove(newHuoYuanListBean.getNr().getList().get(i));
                                }
                            }
                        }
                        for (int k = 0; k < noAdsCount; k++) {
                            String noAdsIds = noAdsBeanList.get(k).getId();
                            if (ggIds.equals(noAdsIds)) {
                                if (newHuoYuanListBean.getNr().getList().get(i).getGg().equals("0")) {
                                    noAdsBeanList.remove(newHuoYuanListBean.getNr().getList().get(i));
                                }
                            }
                        }

                        continue;
                    }
                    tempBeanList.clear();
                    tempBeanList.addAll(adsBeanList);
                    tempBeanList.addAll(noAdsBeanList);*/
                    huoYuanListXRVAdapter.setAdapter(newHuoYuanListBean.getNr().getList());
                }
            }
        });
    }

    public void getData2FromNet(String page,String typeName,String bP,String bC,String bA,String eP,String eC,String eA){
        pbNewHuoYuanList.setVisibility(View.VISIBLE);
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentLat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        if(currentLat == null){
            currentLat = "";
        }

        String currentLon = xcCacheManager.readCache(xcCacheSaveName.currentlon);
        if(currentLon == null){
            currentLon = "";
        }
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getHuoList2FromNet(typeName, currentLat, currentLon, page,bP,bC,bA,eP,eC,eA, new Observer<NewHuoYuanListBean>() {//0为全部货源
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanListBean newHuoYuanListBean) {
                pbNewHuoYuanList.setVisibility(View.GONE);
                /*if(newHuoYuanListBean.getStatus().equals("0")){
                    int size = newHuoYuanListBean.getNr().getList().size();
                    int count = tempBeanList.size();
                    noAdsBeanList.clear();
                    adsBeanList.clear();
                    for(int i=0;i<count;i++){
                        if(tempBeanList.get(i).getGg().equals("0")){
                            noAdsBeanList.add(tempBeanList.get(i));
                        }
                        else if(tempBeanList.get(i).getGg().equals("1")){
                            adsBeanList.add(tempBeanList.get(i));
                        }
                    }
                    int adsCount = adsBeanList.size();
                    int noAdsCount = noAdsBeanList.size();
                    for(int i=0;i < size;i++){
                        String ggIds = newHuoYuanListBean.getNr().getList().get(i).getId();
                        if(newHuoYuanListBean.getNr().getList().get(i).equals("1")) {
                            adsBeanList.add(newHuoYuanListBean.getNr().getList().get(i));
                        }
                        else if(newHuoYuanListBean.getNr().getList().get(i).getGg().equals("0")) {
                            noAdsBeanList.add(newHuoYuanListBean.getNr().getList().get(i));
                        }
                        for(int j = 0;j<adsCount;j++){
                            String adsIds= adsBeanList.get(j).getId();
                            if(adsIds.equals(ggIds)){
                                if(newHuoYuanListBean.getNr().getList().get(i).equals("1")) {
                                    adsBeanList.remove(newHuoYuanListBean.getNr().getList().get(i));
                                }
                            }
                        }
                        for(int k = 0;k<noAdsCount;k++){
                            String noAdsIds = noAdsBeanList.get(k).getId();
                            if(ggIds.equals(noAdsIds)){
                                if(newHuoYuanListBean.getNr().getList().get(i).getGg().equals("0")) {
                                    noAdsBeanList.remove(newHuoYuanListBean.getNr().getList().get(i));
                                }
                            }
                        }

                        continue;
                    }*//*
                    tempBeanList.clear();
                    tempBeanList.addAll(adsBeanList);
                    tempBeanList.addAll(noAdsBeanList);*/
                    tempBeanList.addAll(newHuoYuanListBean.getNr().getList());
                    huoYuanListXRVAdapter.setAdapter(tempBeanList);

            }
        });



    }
}
