package com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.DuiHuanJiLu.DuiHuanJiLuActivity;
import com.shandian.lu.NetWork.CompanyNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.AllGoodsBean;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/5.
 */

public class SuoYouShangPinController extends BaseController {

    @BindView(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin_back)
    RelativeLayout rlyMainMineJiFenShangChengSuoYouShangPinBack;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin_back)
    public void rlyMainMineJiFenShangChengSuoYouShangPinBack(){
        activity.finish();
    }

    @BindView(R.id.xrv_main_mine_jifenshangcheng_suoyoushangpin)
    XRecyclerView xrvMainMineJiFenShangChengSuoYouShangPin;
    SuoYouShangPinXRVGVAdapter adapter;


    @BindView(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin_duihuanjilu)
    RelativeLayout rlyMainMineJiFenShangChengSuoYouShangPinDuiHuanJiLu;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin_duihuanjilu)
    public void rlyMainMineJiFenShangChengSuoYouShangPinDuiHuanJiLuOnclick(){
        Intent intent = new Intent(activity, DuiHuanJiLuActivity.class);
        activity.startActivity(intent);
    }
    public SuoYouShangPinController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        initDataFromNet();
    }

    private void initXRV(){
        List<AllGoodsBean> stringList = new ArrayList<>();
        stringList.add(new AllGoodsBean());

        adapter = new SuoYouShangPinXRVGVAdapter(activity,stringList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvMainMineJiFenShangChengSuoYouShangPin.setLayoutManager(gridLayoutManager);
        xrvMainMineJiFenShangChengSuoYouShangPin.setAdapter(adapter);
    }


    private void initDataFromNet(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            return;
        }
        CompanyNetWork companyNetWork = new CompanyNetWork();
        companyNetWork.getJiFenShangChengAllGoodsFromNet(login_id, new Observer<List<AllGoodsBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<AllGoodsBean> allGoodsBeen) {
                adapter.setAdapter(allGoodsBeen);
            }
        });

    }
}
