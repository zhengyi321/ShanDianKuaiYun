package com.shandian.lu.Main.MineFragment.WoDeHuoYuan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;

import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.FaBuHuoYuanActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/3.
 */

public class WoDeHuoYuanController extends BaseController{


    @BindView(R.id.rly_main_mine_wodehuoyuan_back)
    RelativeLayout rlyMainMineWoDeHuoYuanBack;
    @OnClick(R.id.rly_main_mine_wodehuoyuan_back)
    public void rlyMainMineWoDeHuoYuanBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.rly_main_mine_wodehuoyuan_fabuxinxi)
    RelativeLayout rlyMainMineWoDHuoYuanFabuXinXi;
    @OnClick(R.id.rly_main_mine_wodehuoyuan_fabuxinxi)
    public void rlyMainMineWoDHuoYuanFabuXinXiOnclick(){
        Intent intent = new Intent(activity, FaBuHuoYuanActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.xrv_main_mine_wodehuoyuan_content)
    XRecyclerView xRecyclerViewMainMineWoDeHuoYuanContent;
    private MyHuoSourceBean stringList;
    private WoDeHuoYuanXRVAdapter adapter;
    public WoDeHuoYuanController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        initHuoYuanFromNet();
    }

    private void initXRV(){
         stringList = new MyHuoSourceBean();
        adapter = new WoDeHuoYuanXRVAdapter(activity,stringList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerViewMainMineWoDeHuoYuanContent.setAdapter(adapter);
        xRecyclerViewMainMineWoDeHuoYuanContent.setLayoutManager(layoutManager);
    }

    private void initHuoYuanFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();

        String login_Id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_Id == null){
            return;
        }

        UserNetWork userNetWork = new UserNetWork();
        userNetWork.getMyGoodsSourceFromNet(login_Id, new Observer<MyHuoSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MyHuoSourceBean myHuoSourceBean) {
                adapter.setAdapter(myHuoSourceBean);
            }
        });
    }
}
