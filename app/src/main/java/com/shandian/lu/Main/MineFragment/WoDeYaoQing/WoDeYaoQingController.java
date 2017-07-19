package com.shandian.lu.Main.MineFragment.WoDeYaoQing;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.MyInvitationBean;
import com.example.mynewslayoutlib.Bean.NewYaoQingBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeTuiJianRen.WoDeTuiJianRenActivity;
import com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingDetail.WoDeYaoQingDetailActivity;
import com.shandian.lu.NetWork.MyInvitationNetWork;
import com.shandian.lu.NetWork.WoDeYaoQingNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/4.
 */

public class WoDeYaoQingController extends BaseController {

    @BindView(R.id.lly_main_mine_wodeyaoqing_wodetuijianren)
    LinearLayout llyMainMineWoDeYaoQingWoDeTuiJianRen;
    @OnClick(R.id.lly_main_mine_wodeyaoqing_wodetuijianren)
    public void llyMainMineWoDeYaoQingWoDeTuiJianRenOnclick(){
        Intent intent = new Intent(activity, WoDeTuiJianRenActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.rly_main_mine_myinvitation_back)
    RelativeLayout rlyMainMineMyInvitationBack;
    @OnClick(R.id.rly_main_mine_myinvitation_back)
    public void rlyMainMineMyInvitationBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.tv_main_mine_wodeyaoqing_yaoqingyi_peoplenums)
    TextView tvMainMineWoDeYaoQingYaoQingYiPeopleNums;
    @BindView(R.id.tv_main_mine_wodeyaoqing_yaoqingyi_jifen)
    TextView tvMainMineWoDeYaoQingYaoQingYiJiFen;
    @BindView(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingyi)
    LinearLayout llyMainMineWodeYaoQingWoDeYaoQingYi;
    @OnClick(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingyi)
    public void llyMainMineWodeYaoQingWoDeYaoQingYiOnclick(){
        Intent intent = new Intent(activity, WoDeYaoQingDetailActivity.class);
        intent.putExtra("dj","1");
        activity.startActivity(intent);
    }


    @BindView(R.id.tv_main_mine_wodeyaoqing_yaoqinger_peoplenums)
    TextView tvMainMineWoDeYaoQingYaoQingErPeopleNums;
    @BindView(R.id.tv_main_mine_wodeyaoqing_yaoqinger_jifen)
    TextView tvMainMineWoDeYaoQingYaoQingErJiFen;
    @BindView(R.id.lly_main_mine_wodeyaoqing_wodeyaoqinger)
    LinearLayout llyMainMineWodeYaoQingWoDeYaoQingER;
    @OnClick(R.id.lly_main_mine_wodeyaoqing_wodeyaoqinger)
    public void llyMainMineWodeYaoQingWoDeYaoQingEROnclick(){
        Intent intent = new Intent(activity, WoDeYaoQingDetailActivity.class);
        intent.putExtra("dj","2");
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_main_mine_wodeyaoqing_yaoqingsan_peoplenums)
    TextView tvMainMineWoDeYaoQingYaoQingSanPeopleNums;
    @BindView(R.id.tv_main_mine_wodeyaoqing_yaoqingsan_jifen)
    TextView tvMainMineWoDeYaoQingYaoQingSanJiFen;
    @BindView(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingsan)
    LinearLayout llyMainMineWodeYaoQingWoDeYaoQingSan;
    @OnClick(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingsan)
    public void llyMainMineWodeYaoQingWoDeYaoQingSanOnclick(){
        Intent intent = new Intent(activity, WoDeYaoQingDetailActivity.class);
        intent.putExtra("dj","3");
        activity.startActivity(intent);
    }
    @BindView(R.id.riv_main_mine_myinvitation_headimg)
    RoundImageView rivMainMineMyInvitationHeadImg;
    @BindView(R.id.tv_main_mine_myinvitation_jf)
    TextView tvMainMineMyInvitationJF;

    public WoDeYaoQingController(Activity activity1) {
        activity = activity1;
        init();
    }
    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getDataFromNet();
        getYaoQingDataFromNet("1");
        getYaoQingDataFromNet("2");
        getYaoQingDataFromNet("3");
    }


    private void getDataFromNet(){

        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        MyInvitationNetWork myInvitationNetWork = new MyInvitationNetWork();
        myInvitationNetWork.getMyInvitationFromNet(loginId, new Observer<MyInvitationBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MyInvitationBean myInvitationBean) {
                ImageLoader.getInstance().displayImage(myInvitationBean.getContent().getImage(),rivMainMineMyInvitationHeadImg, ImageLoaderUtils.options1);
                tvMainMineMyInvitationJF.setText(myInvitationBean.getContent().getSumpoints());
            }
        });
    }

    private void getYaoQingDataFromNet(final String dj){
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
                initYaoQingDetail(newYaoQingBean,dj);
            }
        });
    }


    private void initYaoQingDetail(NewYaoQingBean newYaoQingBean,String dj){
        switch (dj){
            case "1":
                tvMainMineWoDeYaoQingYaoQingYiPeopleNums.setText(newYaoQingBean.getRenshu());
                tvMainMineWoDeYaoQingYaoQingYiJiFen.setText(newYaoQingBean.getJifen());
                break;
            case "2":
                tvMainMineWoDeYaoQingYaoQingErPeopleNums.setText(newYaoQingBean.getRenshu());
                tvMainMineWoDeYaoQingYaoQingErJiFen.setText(newYaoQingBean.getJifen());
                break;
            case "3":
                tvMainMineWoDeYaoQingYaoQingSanPeopleNums.setText(newYaoQingBean.getRenshu());
                tvMainMineWoDeYaoQingYaoQingSanJiFen.setText(newYaoQingBean.getJifen());
                break;
        }
    }
}
