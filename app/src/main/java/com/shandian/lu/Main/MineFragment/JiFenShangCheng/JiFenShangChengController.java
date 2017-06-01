package com.shandian.lu.Main.MineFragment.JiFenShangCheng;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.Main.MineFragment.JiFenShangCheng.JiFenTiXian.JiFenTiXianActivity;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.QianDao.QianDaoActivity;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.SuoYouShangPinActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.CompanyNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.JiFenShangChengBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/5.
 */

public class JiFenShangChengController extends BaseController {

    @BindView(R.id.tv_main_mine_jifenshangcheng_content_myjifen)
    TextView tvMainMineJiFenShangChengContentMyJiFen;
    @BindView(R.id.rly_main_mine_jifenshangcheng_back)
    RelativeLayout rlyMainMineJiFenShangChengBack;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_back)
    public void rlyMainMineJiFenShangChengBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.xrv_main_mine_jifenshangcheng)
    RecyclerView xrvMainMineJiFenShangCheng;

    @BindView(R.id.rly_main_mine_jifenshangcheng_qiandao)
    RelativeLayout rlyMainMinJiFenShangChengQianDao;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_qiandao)
    public void rlyMainMinJiFenShangChengQianDaoOnclick(){
        Intent intent = new Intent(activity, QianDaoActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.rly_main_mine_jifenshangcheng_jifenduihuan)
    RelativeLayout rlyMainMinJiFenShangChengJiFenDuiHuan;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_jifenduihuan)
    public void rlyMainMinJiFenShangChengJiFenDuiHuanOnclick(){
        Intent intent = new Intent(activity, SuoYouShangPinActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin)
    RelativeLayout rlyMainMineJiFenShangChengSuoYouShangPin;

    @OnClick(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin)
    public void rlyMainMineJiFenShangChengSuoYouShangPinOnclick(){
        Intent intent = new Intent(activity, SuoYouShangPinActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.rly_main_mine_jifenshangcheng_jifentixian)
    RelativeLayout rlyMainMineJiFenShangChengJiFenTiXian;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_jifentixian)
    public void rlyMainMineJiFenShangChengJiFenTiXianOnclick(){
        Intent intent = new Intent(activity, JiFenTiXianActivity.class);
        activity.startActivity(intent);
    }

    JiFenShangChengXRVGVAdapter adapter;
    public JiFenShangChengController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        initJiFenShangChengFromNet();
    }

    private void initXRV(){
        List<JiFenShangChengBean.ListBean> stringList = new ArrayList<>();
        stringList.add(new JiFenShangChengBean.ListBean());
        adapter = new JiFenShangChengXRVGVAdapter(activity,stringList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvMainMineJiFenShangCheng.setLayoutManager(gridLayoutManager);
        xrvMainMineJiFenShangCheng.setAdapter(adapter);
    }
    private void initJiFenShangChengFromNet(){

        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            return;
        }
        CompanyNetWork companyNetWork = new CompanyNetWork();
        companyNetWork.getJiFenShangChengFromNet(login_id, new Observer<JiFenShangChengBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JiFenShangChengBean jiFenShangChengBean) {
                adapter.setAdapter(jiFenShangChengBean.getList());
                tvMainMineJiFenShangChengContentMyJiFen.setText(jiFenShangChengBean.getJifen());
            }
        });
    }
}
