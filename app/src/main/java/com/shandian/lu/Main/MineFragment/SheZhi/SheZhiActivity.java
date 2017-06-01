package com.shandian.lu.Main.MineFragment.SheZhi;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

/**
 * Created by az on 2017/5/4.
 */

public class SheZhiActivity extends BaseActivity {

    private SheZhiController sheZhiController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_shezhi_lly);
    }

    @Override
    protected void init() {
        initController();
    }

    private void initController(){
        sheZhiController = new SheZhiController(this);
    }
}
