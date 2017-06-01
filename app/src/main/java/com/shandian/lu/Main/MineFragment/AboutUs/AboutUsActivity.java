package com.shandian.lu.Main.MineFragment.AboutUs;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

/**
 * Created by az on 2017/5/3.
 */

public class AboutUsActivity extends BaseActivity{


    private AboutUsController aboutUsController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_aboutus_lly);
    }

    @Override
    protected void init() {
        initController();
    }

    private void initController(){
        aboutUsController = new AboutUsController(this);
    }
}
