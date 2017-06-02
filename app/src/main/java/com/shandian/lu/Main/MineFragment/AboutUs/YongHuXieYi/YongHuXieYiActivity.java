package com.shandian.lu.Main.MineFragment.AboutUs.YongHuXieYi;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class YongHuXieYiActivity extends BaseActivity {


    private YongHuXieYiController yongHuXieYiController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_aboutus_yonghuxieyi_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        yongHuXieYiController = new YongHuXieYiController(this);
    }
}
