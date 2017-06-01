package com.shandian.lu.Main.MineFragment.AboutUs.YiJianFanKui;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/3.
 */

public class YiJianFanKuiActivity extends BaseActivity {


    private YiJianFanKuiController yiJianFanKuiController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_aboutus_yijianfankui_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        yiJianFanKuiController = new YiJianFanKuiController(this);
    }
}
