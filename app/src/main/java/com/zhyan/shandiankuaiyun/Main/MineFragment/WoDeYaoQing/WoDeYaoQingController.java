package com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeYaoQing;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeYaoQing.WoDeTuiJianRen.WoDeTuiJianRenActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeYaoQing.WoDeYaoQingDetail.WoDeYaoQingDetailActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    public WoDeYaoQingController(Activity activity1) {
        activity = activity1;
        init();
    }
    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
