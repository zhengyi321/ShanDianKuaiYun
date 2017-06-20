package com.shandian.lu.Main.MineFragment.WoDeYaoQing;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.MyInvitationBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeTuiJianRen.WoDeTuiJianRenActivity;
import com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingDetail.WoDeYaoQingDetailActivity;
import com.shandian.lu.NetWork.MyInvitationNetWork;
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

    @BindView(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingyi)
    LinearLayout llyMainMineWodeYaoQingWoDeYaoQingYi;
    @OnClick(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingyi)
    public void llyMainMineWodeYaoQingWoDeYaoQingYiOnclick(){
        Intent intent = new Intent(activity, WoDeYaoQingDetailActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_wodeyaoqing_wodeyaoqinger)
    LinearLayout llyMainMineWodeYaoQingWoDeYaoQingER;
    @OnClick(R.id.lly_main_mine_wodeyaoqing_wodeyaoqinger)
    public void llyMainMineWodeYaoQingWoDeYaoQingEROnclick(){
        Intent intent = new Intent(activity, WoDeYaoQingDetailActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingsan)
    LinearLayout llyMainMineWodeYaoQingWoDeYaoQingSan;
    @OnClick(R.id.lly_main_mine_wodeyaoqing_wodeyaoqingsan)
    public void llyMainMineWodeYaoQingWoDeYaoQingSanOnclick(){
        Intent intent = new Intent(activity, WoDeYaoQingDetailActivity.class);
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
}
