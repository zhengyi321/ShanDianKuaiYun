package com.shandian.lu.Main.IndexFragment.NewHuoYuanList;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewCheYuanListBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanListBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.NewCheYuanList.CheYuanListXRVAdapter;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
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

    HuoYuanListXRVAdapter huoYuanListXRVAdapter;
    int page = 1;
    private List<NewHuoYuanListBean.NrBean.ListBean> huoYuanList,tempBeanList,adsBeanList,noAdsBeanList;



    public HuoYuanListController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        rlyNewHuoYuanListChange.setVisibility(View.INVISIBLE);
        getDataFromNet(""+page,"0");
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

    }

    private void getDataFromNet(String page,String typeName){
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
        newCheHuoListNetWork.getHuoListFromNet(typeName, currentLat, currentLon, page, new Observer<NewHuoYuanListBean>() {//0为全部货源
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanListBean newHuoYuanListBean) {
                if(newHuoYuanListBean.getStatus().equals("0")){
                    int size = newHuoYuanListBean.getNr().getList().size();
                    for(int i=0;i < size;i++){
                        if(newHuoYuanListBean.getNr().getList().get(i).getGg().equals("0")){
                            noAdsBeanList.add(newHuoYuanListBean.getNr().getList().get(i));
                        }
                        else if(newHuoYuanListBean.getNr().getList().get(i).getGg().equals("1")){
                            adsBeanList.add(newHuoYuanListBean.getNr().getList().get(i));
                        }


                        continue;
                    }
                    tempBeanList.clear();
                    tempBeanList.addAll(adsBeanList);
                    tempBeanList.addAll(noAdsBeanList);
                    huoYuanListXRVAdapter.setAdapter(tempBeanList);
                }
            }
        });
    }
}
