package com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeYaoQing;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class WoDeYaoQingActivity extends BaseActivity {

    private WoDeYaoQingController woDeYaoQingController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_wodeyaoqing_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        woDeYaoQingController = new WoDeYaoQingController(this);
    }
}
