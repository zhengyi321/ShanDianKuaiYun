package com.shandian.lu.Main.MineFragment.WoDeHuoYuan;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/3.
 */

public class WoDeHuoYuanActivity extends BaseActivity {

    private WoDeHuoYuanController woDeHuoYuanController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_wodehuoyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        woDeHuoYuanController = new WoDeHuoYuanController(this);
    }
}
