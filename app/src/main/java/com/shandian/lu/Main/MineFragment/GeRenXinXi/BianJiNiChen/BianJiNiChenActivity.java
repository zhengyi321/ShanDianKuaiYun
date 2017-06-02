package com.shandian.lu.Main.MineFragment.GeRenXinXi.BianJiNiChen;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class BianJiNiChenActivity extends BaseActivity{


    private BianJiNiChenController bianJiNiChenController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_bianjinichen_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        bianJiNiChenController = new BianJiNiChenController(this);
    }
}
