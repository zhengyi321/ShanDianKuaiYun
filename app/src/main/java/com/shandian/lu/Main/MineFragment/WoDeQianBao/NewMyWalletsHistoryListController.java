package com.shandian.lu.Main.MineFragment.WoDeQianBao;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewMyWalletHistoryListBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.WoDeQianBao.NewTiXian.NewMyWalletTiXianActivity;
import com.shandian.lu.NetWork.MyWalletNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewMyWalletsHistoryListController extends BaseController {


    private String totalMoney;
    @BindView(R.id.rly_new_mywallet_hitsorylist_back)
    RelativeLayout rlyNewMyWalletHistoryListBack;
    @OnClick(R.id.rly_new_mywallet_hitsorylist_back)
    public void rlyNewMyWalletHistoryListBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_new_mywallet_hitsorylist_total)
    TextView tvNewMyWalletHistoryListTotal;
    @BindView(R.id.tv_new_mywallet_hitsorylist_tixian)
    TextView tvNewMyWalletHistoryListTiXian;
    @OnClick(R.id.tv_new_mywallet_hitsorylist_tixian)
    public void tvNewMyWalletHistoryListTiXianOnclick(){
        if(totalMoney == null){
            totalMoney = "";
        }
        Intent intent = new Intent(activity, NewMyWalletTiXianActivity.class);
        intent.putExtra("total",totalMoney);
        activity.startActivity(intent);
    }
    @BindView(R.id.rv_new_mywallet_hitsorylist_detail)
    RecyclerView rvNewMyWalletHistoryListDetail;
    private NewMyWalletHistoryListRVAdapter adapter;
    private List<NewMyWalletHistoryListBean.NrBean> dataList;
    public NewMyWalletsHistoryListController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initRV();
        getDataFromNet();
    }
    private void initRV(){
        dataList = new ArrayList<>();
        adapter = new NewMyWalletHistoryListRVAdapter(activity,dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewMyWalletHistoryListDetail.setAdapter(adapter);
        rvNewMyWalletHistoryListDetail.setLayoutManager(linearLayoutManager);
    }

    private void getDataFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)&&(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        MyWalletNetWork myWalletNetWork = new MyWalletNetWork();
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("id",loginId);
        myWalletNetWork.getMyWalletHistoryListFromNet(paramMap, new Observer<NewMyWalletHistoryListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewMyWalletHistoryListBean newMyWalletHistoryListBean) {
                if(newMyWalletHistoryListBean.getStatus().equals("0")){
                    adapter.setAdapterList(newMyWalletHistoryListBean.getNr());
                    totalMoney = newMyWalletHistoryListBean.getZhjine();
                    if(totalMoney == null){
                        totalMoney = "";
                    }
                    tvNewMyWalletHistoryListTotal.setText("¥ "+totalMoney+"元");

                }
            }
        });
    }
    public void onResume(){
        getDataFromNet();
    }
}
