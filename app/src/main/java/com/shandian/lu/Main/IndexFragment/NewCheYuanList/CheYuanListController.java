package com.shandian.lu.Main.IndexFragment.NewCheYuanList;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewCheYuanListBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.NearByDriver.NearByDriverActivity;
import com.shandian.lu.Main.IndexFragment.PeiHuoZhongXin.PeiHuoZhongXinActivity;
import com.shandian.lu.Main.IndexFragment.TeZhongWuLiu.TeZhongWuLiuActivity;
import com.shandian.lu.Main.IndexFragment.ZhuanXianWuLiu.ZhuanXianWuLiuActivity;
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
 * Created by Administrator on 2017/6/9.
 */

public class CheYuanListController extends BaseController {


    @BindView(R.id.xrv_new_cheyuanlist)
    XRecyclerView xrvNewCheYuanList;
    @BindView(R.id.tv_new_cheyuanlist_title)
    TextView tvNewCheYuanListTitle;
    @BindView(R.id.rly_new_cheyuanlist_back)
    RelativeLayout rlyNewCheYuanListBack;
    @OnClick(R.id.rly_new_cheyuanlist_back)
    public void rlyNewCheYuanListBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.rly_new_cheyuanlist_change)
    RelativeLayout rlyNewCheYuanListChange;
    @OnClick(R.id.rly_new_cheyuanlist_change)
    public void rlyNewCheYuanListChangeOnclick(){
        changeListActivity();
    }

    CheYuanListXRVAdapter cheYuanListXRVAdapter;
    int page = 1;
    private List<NewCheYuanListBean.NrBean.ListBean> cheYuanList,tempBeanList,adsBeanList,noAdsBeanList;

    private String typeName;
    public CheYuanListController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        getTypeName();
        rlyNewCheYuanListChange.setVisibility(View.INVISIBLE);
        getDataFromNet(""+page);
    }

    private void changeListActivity(){
        Intent intent;
        switch (typeName){
            case "1":
                tvNewCheYuanListTitle.setText("同城货运");
                 intent = new Intent(activity, PeiHuoZhongXinActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            case "2":
                tvNewCheYuanListTitle.setText("长途物流");
                intent = new Intent(activity, NearByDriverActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            case "3":
                tvNewCheYuanListTitle.setText("特种物流");
                 intent = new Intent(activity, TeZhongWuLiuActivity.class);
                activity.startActivity(intent);
                activity.finish();

                break;
            case "4":
                tvNewCheYuanListTitle.setText("专线物流");
                intent = new Intent(activity, ZhuanXianWuLiuActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
        }


    }

    private void getTypeName(){
        typeName = activity.getIntent().getStringExtra("typeName");
        if(typeName == null){
            return;
        }
        switch (typeName){
            case "1":
                tvNewCheYuanListTitle.setText("同城货运");
                break;
            case "2":
                tvNewCheYuanListTitle.setText("长途物流");
                break;
            case "3":
                tvNewCheYuanListTitle.setText("特种物流");
                break;
            case "4":
                tvNewCheYuanListTitle.setText("专线物流");
                break;
        }
    }

    private void initXRV(){
        cheYuanList = new ArrayList<>();
        tempBeanList = new ArrayList<>();
        adsBeanList = new ArrayList<>();
        noAdsBeanList = new ArrayList<>();
        cheYuanListXRVAdapter = new CheYuanListXRVAdapter(activity,cheYuanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvNewCheYuanList.setLayoutManager(linearLayoutManager);
        xrvNewCheYuanList.setAdapter(cheYuanListXRVAdapter);

    }

    private void getDataFromNet(String page){
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
        newCheHuoListNetWork.getCheListFromNet(typeName, currentLat, currentLon, page, new Observer<NewCheYuanListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewCheYuanListBean newCheYuanListBean) {
                if(newCheYuanListBean.getStatus().equals("0")){
                   /* int size = newCheYuanListBean.getNr().getList().size();
                    for(int i=0;i < size;i++){
                        if(newCheYuanListBean.getNr().getList().get(i).getGg().equals("0")){
                            noAdsBeanList.add(newCheYuanListBean.getNr().getList().get(i));
                        }
                        else if(newCheYuanListBean.getNr().getList().get(i).getGg().equals("1")){
                            adsBeanList.add(newCheYuanListBean.getNr().getList().get(i));
                        }


                        continue;
                    }
                    tempBeanList.clear();
                    tempBeanList.addAll(adsBeanList);
                    tempBeanList.addAll(noAdsBeanList);*/
                    cheYuanListXRVAdapter.setAdapter(newCheYuanListBean.getNr().getList());
                }
            }
        });
    }
}
