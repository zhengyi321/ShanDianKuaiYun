package com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class WoDeYaoQingDetailActivity extends BaseActivity {

    public WoDeYaoQingDetailController woDeYaoQingDetailController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_wodeyaoqing_wodeyaoqingdetail_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        woDeYaoQingDetailController = new WoDeYaoQingDetailController(this);
    }
}
