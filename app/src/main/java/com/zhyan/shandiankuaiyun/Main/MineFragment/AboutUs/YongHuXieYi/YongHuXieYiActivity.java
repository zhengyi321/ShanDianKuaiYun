package com.zhyan.shandiankuaiyun.Main.MineFragment.AboutUs.YongHuXieYi;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

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
