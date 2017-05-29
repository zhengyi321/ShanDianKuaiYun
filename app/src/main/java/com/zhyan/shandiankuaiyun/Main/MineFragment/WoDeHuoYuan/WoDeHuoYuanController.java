package com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeHuoYuan;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeCheYuan.WoDeCheYuanXRVAdapter;
import com.zhyan.shandiankuaiyun.NetWork.UserNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

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
