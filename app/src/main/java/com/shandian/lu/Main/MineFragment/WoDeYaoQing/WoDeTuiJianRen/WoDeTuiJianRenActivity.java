package com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeTuiJianRen;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingController;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class WoDeTuiJianRenActivity extends BaseActivity {

    private WoDeTuiJianRenController woDeTuiJianRenController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_wodeyaoqing_wodetuijianren_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        woDeTuiJianRenController = new WoDeTuiJianRenController(this);
    }
}
