package com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingDetail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewYaoQingBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.WoDeYaoQingNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/7/19.
 */

public class WoDeYaoQingDetailController extends BaseController {

    @BindView(R.id.rly_main_mine_wodeyaoqing_wodeyaoqingdetail_back)
    RelativeLayout rlyMainMineWoDeYaoQingWoDeYaoQingDetailBack;
    @OnClick(R.id.rly_main_mine_wodeyaoqing_wodeyaoqingdetail_back)
    public void rlyMainMineWoDeYaoQingWoDeYaoQingDetailBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_main_mine_wodeyaoqing_wodeyaoqingdetail_peoplenums)
    TextView tvMainMineWoDeYaoQingWoDeYaoQingDetailPeopleNums;
    @BindView(R.id.tv_main_mine_wodeyaoqing_wodeyaoqingdetail_jifen)
    TextView tvMainMineWoDeYaoQingWoDeYaoQingDetailJiFen;
    @BindView(R.id.tv_main_mine_wodeyaoqing_wodeyaoqingdetail_yaoqing)
    TextView tvMainMineWoDeYaoQingWoDeYaoQingDetailYaoQing;
    @BindView(R.id.rv_main_mine_wodeyaoqing_wodeyaoqingdetail_record)
    RecyclerView rvMainMineWoDeYaoQingWoDeYaoQingDetailRecord;
    WoDeYaoQingDetailRVAdapter woDeYaoQingDetailRVAdapter;
    private List<NewYaoQingBean.NrBean> dataList;
    private String dj= "";
    public WoDeYaoQingDetailController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initRV();
        getDJ();
        getYaoQingDataFromNet();
    }

    private void initRV(){
        dataList = new ArrayList<>();
        woDeYaoQingDetailRVAdapter = new WoDeYaoQingDetailRVAdapter(activity,dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMainMineWoDeYaoQingWoDeYaoQingDetailRecord.setAdapter(woDeYaoQingDetailRVAdapter);
        rvMainMineWoDeYaoQingWoDeYaoQingDetailRecord.setLayoutManager(linearLayoutManager);
    }


    private void getDJ(){
        dj = activity.getIntent().getStringExtra("dj");
        if(dj == null){
            dj = "";
        }
        switch (dj){
            case "1":
                tvMainMineWoDeYaoQingWoDeYaoQingDetailYaoQing.setText("邀请一");
                break;
            case "2":
                tvMainMineWoDeYaoQingWoDeYaoQingDetailYaoQing.setText("邀请二");
                break;
            case "3":
                tvMainMineWoDeYaoQingWoDeYaoQingDetailYaoQing.setText("邀请三");
                break;
        }
    }

    private void getYaoQingDataFromNet(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        WoDeYaoQingNetWork woDeYaoQingNetWork = new WoDeYaoQingNetWork();
        woDeYaoQingNetWork.getYaoQingRenFromNet(loginId, dj, new Observer<NewYaoQingBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewYaoQingBean newYaoQingBean) {
                initYaoQingDetail(newYaoQingBean);
            }
        });
    }
    private void initYaoQingDetail(NewYaoQingBean newYaoQingBean){
        tvMainMineWoDeYaoQingWoDeYaoQingDetailPeopleNums.setText(newYaoQingBean.getRenshu());
        tvMainMineWoDeYaoQingWoDeYaoQingDetailJiFen.setText(newYaoQingBean.getJifen()+"积分");
        woDeYaoQingDetailRVAdapter.setAdapter(newYaoQingBean.getNr());
    }
}
