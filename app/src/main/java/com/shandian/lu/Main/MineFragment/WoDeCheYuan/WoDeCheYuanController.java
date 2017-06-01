package com.shandian.lu.Main.MineFragment.WoDeCheYuan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;

import com.shandian.lu.Main.MineFragment.CommonFaBuCheYuan.FaBuCheYuanActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourceBean;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/3.
 */

public class WoDeCheYuanController extends BaseController{

    @BindView(R.id.rly_main_mine_wodecheyuan_back)
    RelativeLayout rlyMainMineWoDeCheYuanBack;
    @OnClick(R.id.rly_main_mine_wodecheyuan_back)
    public void rlyMainMineWoDeCheYuanBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.rly_main_mine_wodecheyuan_fabucheyuan)
    RelativeLayout rlyMainMineWoDeCheYuanFaBuCheYuan;
    @OnClick(R.id.rly_main_mine_wodecheyuan_fabucheyuan)
    public void rlyMainMineWoDeCheYuanFaBuCheYuanOnclick(){
        Intent intent = new Intent(activity, FaBuCheYuanActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.xrv_main_mine_wodecheyuan_content)
    XRecyclerView xRecyclerViewMainMineWoDeCheYuanContent;

    private WoDeCheYuanXRVAdapter adapter;
    public WoDeCheYuanController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        initGetCarSourceFromNet();
    }

    private void initXRV(){
        MyCarSourceBean stringList = new MyCarSourceBean();


        adapter = new WoDeCheYuanXRVAdapter(activity,stringList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerViewMainMineWoDeCheYuanContent.setAdapter(adapter);
        xRecyclerViewMainMineWoDeCheYuanContent.setLayoutManager(layoutManager);
        xRecyclerViewMainMineWoDeCheYuanContent.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerViewMainMineWoDeCheYuanContent.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xRecyclerViewMainMineWoDeCheYuanContent.loadMoreComplete();
            }
        });
    }
    private void initGetCarSourceFromNet(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.getMyCarSourceFromNet(login_id, new Observer<MyCarSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MyCarSourceBean myCarSourceBean) {
              /*  Toast.makeText(activity,myCarSourceBean.getMsg()+myCarSourceBean.getContent().get(0).getAddress(),Toast.LENGTH_LONG).show();*/
              if(myCarSourceBean.getStatus() == 0) {
                  adapter.setAdapter(myCarSourceBean);
              }
            }
        });
    }
}
